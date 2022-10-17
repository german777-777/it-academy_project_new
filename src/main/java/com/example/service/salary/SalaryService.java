package com.example.service.salary;

import com.example.model.salary.Salary;

import java.util.List;

public interface SalaryService {
    void save(Long teacherId, Salary salary);
    Salary findById(Long id);
    List<Salary> findAll();
    List<Salary> findByTeacherId(Long teacherId);
    void update(Long teacherId, Salary salary);
    boolean delete(Long id);
}
