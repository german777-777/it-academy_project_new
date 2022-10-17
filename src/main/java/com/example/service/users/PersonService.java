package com.example.service.users;

import com.example.model.users.Person;

import java.util.List;

public interface PersonService {
    Person save(Person person);
    Person findById(Long id);
    Person findByLogin(String login);
    List<Person> findAll();
    Person update(Person person);
    boolean delete(Long id);
}
