package com.example.service.group;

import com.example.exceptions.CreateEntityException;
import com.example.exceptions.DeleteEntityException;
import com.example.exceptions.NotFoundEntityException;
import com.example.exceptions.UpdateEntityException;
import com.example.model.group.Group;
import com.example.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommonGroupService implements GroupService {

    private final GroupRepository groupRepository;

    @Override
    public boolean save(Group group) {
        String name = group.getName();

        if (name == null) {
            throw new CreateEntityException("Некорректные данные!");
        }

        if (groupRepository.ifExistsByName(name)) {
            throw new CreateEntityException("Группа с введённым названием уже существует!");
        }

        groupRepository.save(group);

        return group.getId() != 0;
    }

    @Override
    public Group findById(Long id) {
        return groupRepository.findById(id).orElseThrow(() -> new NotFoundEntityException("Группа не найдена по ID!"));
    }

    @Override
    public Group findByName(String name) {
        return groupRepository.findByName(name).orElseThrow(() -> new NotFoundEntityException("Группа не найдена по названию!"));
    }

    @Override
    public boolean update(Group oldGroup, Group newGroup) {
        if (oldGroup == null) {
            throw new UpdateEntityException("Некорректные данные: старая группа не найдена!");
        }

        String newGroupName = newGroup.getName();

        if (groupRepository.ifExistsByName(newGroupName)) {
            throw new UpdateEntityException("Группа с введёнными данными уже существует!");
        }

        if (newGroupName != null) {
            oldGroup.setName(newGroupName);
        }

        groupRepository.save(oldGroup);

        return oldGroup.getId() != 0;
    }

    @Override
    public boolean delete(Long id) {
        groupRepository.findById(id).orElseThrow(() -> new DeleteEntityException("Группа не найдена по ID: удаление невозможно!"));
        groupRepository.deleteById(id);
        return true;
    }
}
