package com.example.service.users;

import com.example.model.users.Person;
import com.example.model.users.Student;
import com.example.model.users.Teacher;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface PersonService<T extends Person> extends UserDetailsService {
    boolean save(T person);

    Student findStudentById(Long id);
    Teacher findTeacherById(Long id);

    T findByLoginAndPassword(String login, String password);

    List<Student> findAllStudents();
    List<Teacher> findAllTeachers();

    boolean update(T oldPerson, T newPerson);
    boolean delete(Long id);
}
