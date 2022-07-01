package com.example.repository;

import com.example.model.subject.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    @Query("select case when count(s) > 0 then true else false end from Subject s where s.name = ?1")
    Boolean ifExistsByName(String name);

    Optional<Subject> findByName(String name);
}