package com.example.repository.users.roles;

import com.example.model.users.roles.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}