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
    @Query(value = "select g from Group g where g.id = ?1")
    Optional<Group> findById(Long id);

    @EntityGraph(
            type = EntityGraph.EntityGraphType.LOAD,
            attributePaths = {"teachers", "students", "subjects"}
    )
    @Query(value = "select g from Group g where g.name = ?1")
    Optional<Group> findByName(String name);

    @Query("select case when count(g) > 0 then true else false end from Group g where g.name = ?1")
    Boolean ifExistsByName(String name);
}