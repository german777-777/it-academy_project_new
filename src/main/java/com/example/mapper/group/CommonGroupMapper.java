package com.example.mapper.group;

import com.example.annotation.Mapper;
import com.example.dto.group.GroupRequestCreateDto;
import com.example.dto.group.GroupRequestUpdateDto;
import com.example.dto.group.response.GroupResponseOptionalDto;
import com.example.dto.group.response.GroupResponseStudentsDto;
import com.example.dto.group.response.GroupResponseSubjectsDto;
import com.example.dto.group.response.GroupResponseTeachersDto;
import com.example.model.group.Group;

import java.util.List;

@Mapper
public class CommonGroupMapper implements GroupMapper {
    @Override
    public Group toEntity(GroupRequestCreateDto groupRequestCreateDto) {
        Group group = new Group();
        group.setName(groupRequestCreateDto.name());
        return group;
    }

    @Override
    public Group toEntity(GroupRequestUpdateDto groupRequestUpdateDto) {
        Group group = new Group();
        group.setId(groupRequestUpdateDto.id());
        group.setName(groupRequestUpdateDto.name());
        return group;
    }

    @Override
    public GroupResponseOptionalDto toOptionalDto(Group group) {
        return new GroupResponseOptionalDto(group.getId(), group.getName());
    }

    @Override
    public GroupResponseStudentsDto toStudentsDto(Group group) {
        return new GroupResponseStudentsDto(group.getId(), group.getName(), group.getStudents());
    }

    @Override
    public GroupResponseTeachersDto toTeachersDto(Group group) {
        return new GroupResponseTeachersDto(group.getId(), group.getName(), group.getTeachers());
    }

    @Override
    public GroupResponseSubjectsDto toSubjectsDto(Group group) {
        return new GroupResponseSubjectsDto(group.getId(), group.getName(), group.getSubjects());
    }

    @Override
    public List<GroupResponseOptionalDto> toListDtos(List<Group> groups) {
        return groups.stream()
                .map(this::toOptionalDto)
                .toList();
    }
}
