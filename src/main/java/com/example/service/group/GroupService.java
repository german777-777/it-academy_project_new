package com.example.service.group;

import com.example.model.group.Group;

public interface GroupService {
    boolean save(Group group);

    Group findById(Long id);
    Group findByName(String name);

    boolean update(Group oldGroup, Group newGroup);
    boolean delete(Long id);
}
