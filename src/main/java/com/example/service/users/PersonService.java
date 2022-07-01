package com.example.service.users;

import com.example.model.users.Person;
import com.example.service.users.student.StudentService;
import com.example.service.users.teacher.TeacherService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface PersonService extends TeacherService, StudentService, UserDetailsService {
    boolean save(Person person);

    Person findByLoginAndPassword(String login, String password);

    boolean update(Person oldPerson, Person newPerson);

    boolean delete(Long id);
}
