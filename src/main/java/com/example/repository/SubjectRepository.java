package com.example.repository;

import com.example.model.subject.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    /**
     * @param id  subject identifier
     * @return {@link java.lang.Boolean} type, which indicates that group exists or not
     */
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END FROM Subject s WHERE s.id = ?1")
    Boolean ifExistsById(Long id);

    /**
     * @param name  subject name
     * @return {@link java.lang.Boolean} type, which indicates that subject exists or not
     */
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END FROM Subject s WHERE s.name = ?1")
    Boolean ifExistsByName(String name);

    /**
     * @param name  subject name
     * @return {@link java.util.Optional<com.example.model.subject.Subject>} -- subject-wrapper, for safe checking subject on higher level
     */
    Optional<Subject> findByName(String name);
}