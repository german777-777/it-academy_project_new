package com.example.service.role;

import com.example.exceptions.NotFoundEntityException;
import com.example.model.users.roles.Role;
import com.example.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.util.constant.Constants.BY_NAME_MESSAGE;

@Service
@RequiredArgsConstructor
public class CommonRoleService implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new NotFoundEntityException(BY_NAME_MESSAGE));
    }
}
