package com.example.repository.users.roles.authorities;

import com.example.model.users.roles.authority.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}