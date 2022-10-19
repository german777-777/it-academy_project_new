package com.example.facade.group;

import com.example.annotation.Facade;
import com.example.annotation.Validate;
import com.example.dto.group.GroupRequestCreateDto;
import com.example.dto.group.GroupRequestUpdateDto;
import com.example.dto.group.GroupResponseDto;
import com.example.dto.subject.SubjectResponseDto;
import com.example.dto.user.person.PersonResponseDto;
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
    public GroupResponseDto getGroupById(Long id) {
        return groupMapper.toDto(groupService.findById(id));
    }

    @Override
    public List<PersonResponseDto> getStudentsByGroupId(Long groupId) {
        return groupMapper.toStudentsDtos(groupService.findAllStudentsByGroupId(groupId));
    }

    @Override
    public List<PersonResponseDto> getTeachersByGroupId(Long groupId) {
        return groupMapper.toTeachersDtos(groupService.findAllTeachersByGroupId(groupId));
    }

    @Override
    public List<SubjectResponseDto> getSubjectsByGroupId(Long groupId) {
        return groupMapper.toSubjectsDtos(groupService.findAllSubjectsByGroupId(groupId));
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
