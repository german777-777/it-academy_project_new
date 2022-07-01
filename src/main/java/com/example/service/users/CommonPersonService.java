package com.example.service.users;

import com.example.exceptions.NotFoundEntityException;
import com.example.exceptions.UpdateEntityException;
import com.example.model.group.Group;
import com.example.model.users.Student;
import com.example.model.users.Teacher;
import com.example.repository.GroupRepository;
import com.example.repository.users.PersonRepository;
import com.example.repository.users.roles.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonPersonService extends AbstractPersonService {
    private final PersonRepository personRepository;
    private final GroupRepository groupRepository;

    public CommonPersonService(PersonRepository personRepository, RoleRepository roleRepository, GroupRepository groupRepository, PasswordEncoder passwordEncoder) {
        super(personRepository, roleRepository, passwordEncoder);
        this.personRepository = personRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public boolean addStudentToGroup(Student student, Group group) {
        if (student == null || group == null) {
            throw new UpdateEntityException("Некорректные данные: студент или группа не найдены!");
        }

        if (group.getStudents().contains(student)) {
            throw new UpdateEntityException("Студент уже присутствует в этой группе!");
        }

        group.addStudent(student);

        groupRepository.save(group);

        return group.getId() != 0;
    }

    @Override
    public boolean deleteStudentFromGroup(Student student, Group group) {
        if (student == null || group == null) {
            throw new UpdateEntityException("Некорректные данные: студент или группа не найдены!");
        }

        if (!group.getStudents().contains(student)) {
            throw new UpdateEntityException("Студент не присутствует в этой группе!");
        }

        group.removeStudent(student);

        groupRepository.save(group);

        return group.getId() != 0;
    }

    @Override
    public Student findStudentById(Long id) {
        return personRepository.findStudentById(id).orElseThrow(() -> new NotFoundEntityException("Студент не найден по ID!"));
    }

    @Override
    public List<Student> findAllStudents() {
        return personRepository.findAllStudents();
    }

    @Override
    public boolean addTeacherToGroup(Teacher teacher, Group group) {
        if (teacher == null || group == null) {
            throw new UpdateEntityException("Некорректные данные: учитель или группа не найдены!");
        }

        if (group.getTeachers().contains(teacher)) {
            throw new UpdateEntityException("Учитель уже присутствует в этой группе!");
        }

        group.addTeacher(teacher);

        groupRepository.save(group);

        return group.getId() != 0;
    }

    @Override
    public boolean deleteTeacherFromGroup(Teacher teacher, Group group) {
        if (teacher == null || group == null) {
            throw new UpdateEntityException("Некорректные данные: учитель или группа не найдены!");
        }

        if (!group.getTeachers().contains(teacher)) {
            throw new UpdateEntityException("Учитель не присутствует в этой группе!");
        }

        group.removeTeacher(teacher);

        groupRepository.save(group);

        return group.getId() != 0;
    }

    @Override
    public Teacher findTeacherById(Long id) {
        return personRepository.findTeacherById(id).orElseThrow(() -> new NotFoundEntityException("Учитель не найден по ID!"));
    }

    @Override
    public List<Teacher> findAllTeachers() {
        return personRepository.findAllTeachers();
    }
}
