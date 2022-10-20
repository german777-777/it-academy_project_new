package com.example.facade.group;

import com.example.annotation.Facade;
import com.example.annotation.Validate;
import com.example.dto.rest.group.GroupRequestCreateDto;
import com.example.dto.rest.group.GroupRequestUpdateDto;
import com.example.dto.rest.group.GroupResponseDto;
import com.example.mapper.group.GroupMapper;
import com.example.model.group.Group;
import com.example.service.group.GroupService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Facade(value = "restGroupFacade")
@RequiredArgsConstructor
public class CommonRestGroupFacade implements GroupFacade<GroupRequestCreateDto, GroupRequestUpdateDto, GroupResponseDto> {

    private final GroupService groupService;
    private final GroupMapper groupMapper;

    @Override
    @Validate
    public void saveGroup(GroupRequestCreateDto groupRequestCreateDto) {
        groupService.save(groupMapper.toEntity(groupRequestCreateDto));
    }

    @Override
    public GroupResponseDto getGroupById(Long id) {
        return groupMapper.toDto(groupService.findById(id));
    }

    @Override
    public List<GroupResponseDto> getAllGroups() {
        return groupMapper.toListDtos(groupService.findAll());
    }

    @Override
    @Validate
    public void updateGroup(GroupRequestUpdateDto groupRequestUpdateDto) {
        groupService.update(groupMapper.toEntity(groupRequestUpdateDto));
    }

    @Override
    public void delete(Long id) {
        groupService.delete(id);
    }
}
