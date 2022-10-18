package com.example.repository;

import com.example.model.mark.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MarkRepository extends JpaRepository<Mark, Long> {
    /**
     * @param id  mark identifier
     * @return {@link java.lang.Boolean} type, which indicates that mark exists or not
     */
    @Query(value = "SELECT CASE WHEN COUNT(m) > 0 THEN TRUE ELSE FALSE END FROM Mark m WHERE m.id = ?1")
    Boolean ifExistsById(Long id);
}