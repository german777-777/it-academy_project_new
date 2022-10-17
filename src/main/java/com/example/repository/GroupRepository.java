package com.example.repository;

import com.example.model.group.Group;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
    @EntityGraph(
            type = EntityGraph.EntityGraphType.LOAD,
            value = "group-with-teachers-students-subjects"
    )
    @Query(value = "SELECT g FROM Group g WHERE g.id = ?1")
    Optional<Group> findById(Long id);

    @EntityGraph(
            type = EntityGraph.EntityGraphType.LOAD,
            attributePaths = {"teachers", "students", "subjects"}
    )
    @Query(value = "SELECT g FROM Group g WHERE g.id = ?1")
    Optional<Group> findByName(String name);

    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN TRUE ELSE FALSE END FROM Group g WHERE g.id = ?1")
    Boolean ifExistsById(Long id);

    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN TRUE ELSE FALSE END FROM Group g WHERE g.name = ?1")
    Boolean ifExistsByName(String name);
}