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
    @Query("SELECT CASE WHEN count(p) > 0 THEN TRUE ELSE FALSE END FROM Person p WHERE p.credentials.login = ?1 OR p.credentials.password = ?2")
    Boolean ifExistsByLoginOrPassword(String login, String password);

    @Query("SELECT p FROM Person p WHERE p.credentials.login = ?1")
    Optional<Person> findByLogin(String login);

    @Query("SELECT p FROM Person p WHERE p.credentials.login = ?1 AND p.credentials.password = ?2")
    Optional<Person> findByLoginAndPassword(String login, String password);

}
