package com.example.service.subjects;

import com.example.model.subject.Subject;

import java.util.List;

public interface SubjectService {
    boolean save(Subject subject);

    Subject findById(Long id);
    Subject findByName(String name);
    List<Subject> findAll();

    boolean update(Subject oldSubject, Subject newSubject);
    boolean delete(Long id);
}
