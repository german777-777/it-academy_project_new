package com.example.repository.users;

import com.example.model.users.Person;
import com.example.model.users.Student;
import com.example.model.users.Teacher;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("select case when count(p) > 0 then true else false end from Person p where p.credentials.login = ?1 or p.credentials.password = ?2")
    Boolean ifExistsByLoginOrPassword(String login, String password);

    @Query("select p from Person p where p.credentials.login = ?1")
    Optional<Person> findByLogin(String login);

    @Query("select p from Person p where p.credentials.login = ?1 and p.credentials.password = ?2")
    Optional<Person> findByLoginAndPassword(String login, String password);

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, value = "student-with-marks")
    @Query("select s from Student s where s.id = ?1")
    Optional<Student> findStudentById(Long id);

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, value = "teacher-with-salaries")
    @Query("select t from Teacher t where t.id = ?1")
    Optional<Teacher> findTeacherById(Long id);

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, value = "student-with-marks")
    @Query("from Student")
    List<Student> findAllStudents();

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, value = "teacher-with-salaries")
    @Query("from Teacher")
    List<Teacher> findAllTeachers();
}
