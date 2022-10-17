package com.example.repository;

import com.example.model.salary.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SalaryRepository extends JpaRepository<Salary, Long> {
    @Query(value = "SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END FROM Salary s WHERE s.id = ?1")
    Boolean ifExistsById(Long id);
}