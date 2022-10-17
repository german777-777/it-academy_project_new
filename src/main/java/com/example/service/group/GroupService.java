package com.example.service.group;

import com.example.model.group.Group;

public interface GroupService {
    Group save(Group group);
    Group findById(Long id);
    Group findByName(String name);
    Group update(Group group);
    boolean delete(Long id);
}
