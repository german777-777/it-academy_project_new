package com.example.service.role;

import com.example.model.users.roles.Role;

public interface RoleService {
    Role findByName(String name);
}
