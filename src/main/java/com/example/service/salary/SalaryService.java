package com.example.service.salary;

import com.example.model.salary.Salary;
import com.example.model.users.Teacher;

import java.util.List;

public interface SalaryService {
    boolean save(Salary salary, Teacher teacher);

    Salary findById(Long id);
    List<Salary> findSalariesByTeacherId(Long teacherId);

    boolean update(Salary oldSalary, Salary newSalary, Teacher teacher);
    boolean delete(Long id);
}
