package com.example.facade.group;

import com.example.annotation.Facade;
import com.example.annotation.Validate;
import com.example.dto.group.GroupRequestCreateDto;
import com.example.dto.group.GroupRequestUpdateDto;
import com.example.dto.group.GroupResponseDto;
import com.example.mapper.group.GroupMapper;
import com.example.service.group.GroupService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Facade
@RequiredArgsConstructor
public class CommonGroupFacade implements GroupFacade {

    private final GroupService groupService;
    private final GroupMapper mapper;

    @Override
    @Validate
    public void saveGroup(GroupRequestCreateDto groupRequestCreateDto) {

    }

    @Override
    @Validate
    public GroupResponseDto getGroupById(Long id) {
        return null;
    }

    @Override
    @Validate
    public List<GroupResponseDto> getAllGroups() {
        return null;
    }

    @Override
    @Validate
    public void updateGroup(GroupRequestUpdateDto groupRequestUpdateDto) {

    }

    @Override
    public void delete(Long id) {

    }
}
