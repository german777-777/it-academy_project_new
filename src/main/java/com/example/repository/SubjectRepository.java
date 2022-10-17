package com.example.repository;

import com.example.model.subject.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END FROM Subject s WHERE s.id = ?1")
    Boolean ifExistsById(Long id);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END FROM Subject s WHERE s.name = ?1")
    Boolean ifExistsByName(String name);

    Optional<Subject> findByName(String name);
}