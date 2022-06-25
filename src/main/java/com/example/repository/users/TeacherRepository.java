package com.example.repository.users;

import com.example.model.users.Teacher;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Override
    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, value = "teacher-with-salaries")
    Optional<Teacher> findById(Long id);

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, value = "teacher-with-salaries")
    Optional<Teacher> findByCredentialsLoginAndCredentialsPassword(String login, String password);

    @Override
    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, value = "teacher-with-salaries")
    List<Teacher> findAll();

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, value = "teacher-with-salaries")
    Optional<Teacher> findByFirstNameAndLastNameAndPatronymic(String firstName, String lastName, String patronymic);
}