package com.example.service.users;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface PersonService<T> extends UserDetailsService {
    boolean save(T person);
    T findById(Long id);
    T findByLoginAndPassword(String login, String password);
    List<T> findAll();
    boolean update(T oldPerson, T newPerson);
    boolean delete(Long id);
}
