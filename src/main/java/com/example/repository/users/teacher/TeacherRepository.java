package com.example.repository.users.teacher;

import com.example.model.users.Teacher;
import com.example.repository.users.PersonRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends PersonRepository {

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, value = "teacher-with-salaries")
    @Query("SELECT t FROM Teacher t WHERE t.id = ?1")
    Optional<Teacher> findTeacherById(Long id);

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, value = "teacher-with-salaries")
    @Query("SELECT t FROM Teacher t")
    List<Teacher> findAllTeachers();
}
