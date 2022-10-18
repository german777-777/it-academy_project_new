package com.example.service.group;

import com.example.model.group.Group;
import com.example.model.subject.Subject;
import com.example.model.users.Student;
import com.example.model.users.Teacher;

import java.util.List;

public interface GroupService {
    Group save(Group group);
    Group findById(Long id);
    Group findByName(String name);
    List<Student> findAllStudentsByGroupId(Long groupId);
    List<Teacher> findAllTeachersByGroupId(Long groupId);
    List<Subject> findAllSubjectsByGroupId(Long groupId);
    List<Group> findAll();
    Group update(Group group);
    boolean delete(Long id);
}
