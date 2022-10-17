package com.example.repository;

import com.example.model.mark.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MarkRepository extends JpaRepository<Mark, Long> {
    @Query(value = "SELECT CASE WHEN COUNT(m) > 0 THEN TRUE ELSE FALSE END FROM Mark m WHERE m.id = ?1")
    Boolean ifExistsById(Long id);
}