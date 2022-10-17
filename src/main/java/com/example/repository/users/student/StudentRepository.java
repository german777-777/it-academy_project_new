package com.example.repository.users.student;

import com.example.model.users.Student;
import com.example.repository.users.PersonRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends PersonRepository {
    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, value = "student-with-marks")
    @Query("SELECT s FROM Student s WHERE s.id = ?1")
    Optional<Student> findStudentById(Long id);

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, value = "student-with-marks")
    @Query("SELECT s FROM Student s")
    List<Student> findAllStudents();
}
