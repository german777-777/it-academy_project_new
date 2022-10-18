package com.example.service.group;

import com.example.exceptions.CreateEntityException;
import com.example.exceptions.DeleteEntityException;
import com.example.exceptions.NotFoundEntityException;
import com.example.model.group.Group;
import com.example.model.subject.Subject;
import com.example.model.users.Student;
import com.example.model.users.Teacher;
import com.example.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.springframework.transaction.annotation.Isolation.REPEATABLE_READ;

@Service
@RequiredArgsConstructor
public class CommonGroupService implements GroupService {

    private final GroupRepository groupRepository;

    @Override
    @Transactional
    public Group save(Group group) {
        if (TRUE.equals(groupRepository.ifExistsByName(group.getName()))) {
            throw new CreateEntityException(" cause already existing by this name");
        }
        return groupRepository.save(group);
    }

    @Override
    public Group findById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException(" by id"));
    }

    @Override
    public Group findByName(String name) {
        return groupRepository.findByName(name)
                .orElseThrow(() -> new NotFoundEntityException(" by name"));
    }

    @Override
    public List<Student> findAllStudentsByGroupId(Long groupId) {
        return findById(groupId).getStudents();
    }

    @Override
    public List<Teacher> findAllTeachersByGroupId(Long groupId) {
        return findById(groupId).getTeachers();
    }

    @Override
    public List<Subject> findAllSubjectsByGroupId(Long groupId) {
        return findById(groupId).getSubjects();
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    @Transactional(isolation = REPEATABLE_READ)
    public Group update(Group group) {
        if (TRUE.equals(groupRepository.ifExistsByName(group.getName()))) {
            throw new CreateEntityException(" cause already existing by this name");
        }
        return groupRepository.save(group);
    }

    @Override
    public boolean delete(Long id) {
        if (FALSE.equals(groupRepository.ifExistsById(id))) {
            throw new DeleteEntityException(" cause not exists by this id");
        }
        groupRepository.deleteById(id);
        return true;
    }
}
