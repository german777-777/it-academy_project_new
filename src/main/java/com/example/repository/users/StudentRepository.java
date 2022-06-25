package com.example.repository.users;

import com.example.model.users.Student;
import com.example.model.users.credentials.Credentials;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Override
    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, value = "student-with-marks")
    Optional<Student> findById(Long id);

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, value = "student-with-marks")
    Optional<Student> findByCredentialsLoginAndCredentialsPassword(String login, String password);

    @Override
    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, value = "student-with-marks")
    List<Student> findAll();

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, value = "student-with-marks")
    Optional<Student> findByFirstNameAndLastNameAndPatronymic(String firstName, String lastName, String patronymic);
}