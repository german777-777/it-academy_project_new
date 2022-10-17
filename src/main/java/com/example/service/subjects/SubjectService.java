package com.example.service.subjects;

import com.example.model.subject.Subject;

import java.util.List;

public interface SubjectService {
    Subject save(Subject subject);
    Subject findById(Long id);
    Subject findByName(String name);
    List<Subject> findAll();
    Subject update(Subject subject);
    boolean delete(Long id);
}
