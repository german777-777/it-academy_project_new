package com.example.service.users.student;

import com.example.model.group.Group;
import com.example.model.users.Student;

import java.util.List;

public interface StudentService {
    boolean addStudentToGroup(Student student, Group group);

    Student findStudentById(Long id);
    List<Student> findAllStudents();

    boolean deleteStudentFromGroup(Student student, Group group);
}
