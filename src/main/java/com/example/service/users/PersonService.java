package com.example.service.users;

import com.example.model.users.Person;
import com.example.model.users.Student;
import com.example.model.users.Teacher;

import java.util.List;

public interface PersonService {
    Person save(Person person);
    Person findById(Long id);
    Person findByLogin(String login);

    Student findStudentById(Long studentId);
    Teacher findTeacherById(Long teacherId);

    List<Student> findAllStudents();
    List<Teacher> findAllTeachers();

    List<Person> findAll();
    Person update(Person person);
    boolean delete(Long id);
}
