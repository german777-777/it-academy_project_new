package com.example.repository.users;

import com.example.model.users.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT CASE WHEN count(p) > 0 THEN TRUE ELSE FALSE END FROM Person p WHERE p.id = ?1")
    Boolean ifExistsById(Long id);
    @Query("SELECT CASE WHEN count(p) > 0 THEN TRUE ELSE FALSE END FROM Person p WHERE p.credentials.login = ?1")
    Boolean ifExistsByLogin(String login);

    @Query("SELECT p FROM Person p WHERE p.credentials.login = ?1")
    Optional<Person> findByLogin(String login);
}
