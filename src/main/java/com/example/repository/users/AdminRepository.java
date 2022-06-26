package com.example.repository.users;

import com.example.model.users.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Query("select case when count(a) > 0 then true else false end from Admin a where a.credentials.login = ?1 or a.credentials.password = ?2")
    Boolean ifExistsByLoginOrPassword(String login, String password);

    @Query("select a from Admin a where a.credentials.login = ?1")
    Optional<Admin> findByLogin(String login);

    @Query("select a from Admin a where a.credentials.login = ?1 and a.credentials.password = ?2")
    Optional<Admin> findByLoginAndPassword(String login, String password);

}