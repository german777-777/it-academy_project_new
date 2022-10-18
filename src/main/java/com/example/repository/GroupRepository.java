package com.example.repository;

import com.example.model.group.Group;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
    /**
     * Method for finding group with its subtypes ({@link java.util.List} of students, {@link java.util.List} of teachers, {@link java.util.List} subjects)
     * @param id group identifier
     * @return {@link java.util.Optional<com.example.model.group.Group>} -- group-wrapper, for safe checking group on higher level
     */
    @EntityGraph(
            type = EntityGraph.EntityGraphType.LOAD,
            value = "group-with-teachers-students-subjects"
    )
    @Query(value = "SELECT g FROM Group g WHERE g.id = ?1")
    Optional<Group> findById(Long id);

    /**
     * Method for finding group with its subtypes ({@link java.util.List} of students, {@link java.util.List} of teachers, {@link java.util.List} subjects)
     * @param name group name
     * @return {@link java.util.Optional<com.example.model.group.Group>} -- group-wrapper, for safe checking group on higher level
     */
    @EntityGraph(
            type = EntityGraph.EntityGraphType.LOAD,
            attributePaths = {"teachers", "students", "subjects"}
    )
    @Query(value = "SELECT g FROM Group g WHERE g.name = ?1")
    Optional<Group> findByName(String name);

    /**
     * @param id  group identifier
     * @return {@link java.lang.Boolean} type, which indicates that group exists or not
     */
    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN TRUE ELSE FALSE END FROM Group g WHERE g.id = ?1")
    Boolean ifExistsById(Long id);

    /**
     * @param name  group name
     * @return {@link java.lang.Boolean} type, which indicates that group exists or not
     */
    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN TRUE ELSE FALSE END FROM Group g WHERE g.name = ?1")
    Boolean ifExistsByName(String name);
}