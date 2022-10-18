package com.example.facade.group;

import com.example.annotation.Facade;
import com.example.annotation.Validate;
import com.example.dto.group.GroupRequestCreateDto;
import com.example.dto.group.GroupRequestUpdateDto;
import com.example.dto.group.response.GroupResponseOptionalDto;
import com.example.dto.group.response.GroupResponseStudentsDto;
import com.example.dto.group.response.GroupResponseSubjectsDto;
import com.example.dto.group.response.GroupResponseTeachersDto;
import com.example.mapper.group.GroupMapper;
import com.example.service.group.GroupService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Facade
@RequiredArgsConstructor
public class CommonGroupFacade implements GroupFacade {

    private final GroupService groupService;
    private final GroupMapper groupMapper;

    @Override
    @Validate
    public void saveGroup(GroupRequestCreateDto groupRequestCreateDto) {
        groupService.save(groupMapper.toEntity(groupRequestCreateDto));
    }

    @Override
    @Validate
    public GroupResponseOptionalDto getGroupById(Long id) {
        return groupMapper.toOptionalDto(groupService.findById(id));
    }

    @Override
    public GroupResponseStudentsDto getStudentsByGroupId(Long groupId) {
        return groupMapper.toStudentsDto(groupService.findById(groupId));
    }

    @Override
    public GroupResponseTeachersDto getTeachersByGroupId(Long groupId) {
        return groupMapper.toTeachersDto(groupService.findById(groupId));
    }

    @Override
    public GroupResponseSubjectsDto getSubjectsByGroupId(Long groupId) {
        return groupMapper.toSubjectsDto(groupService.findById(groupId));
    }

    @Override
    @Validate
    public List<GroupResponseOptionalDto> getAllGroups() {
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
