package com.example.mapper.group;

import com.example.dto.group.GroupRequestCreateDto;
import com.example.dto.group.GroupRequestUpdateDto;
import com.example.dto.group.response.GroupResponseOptionalDto;
import com.example.dto.group.response.GroupResponseStudentsDto;
import com.example.dto.group.response.GroupResponseSubjectsDto;
import com.example.dto.group.response.GroupResponseTeachersDto;
import com.example.model.group.Group;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    Group toEntity(GroupRequestCreateDto groupRequestCreateDto);

    Group toEntity(GroupRequestUpdateDto groupRequestUpdateDto);

    GroupResponseOptionalDto toOptionalDto(Group group);

    GroupResponseStudentsDto toStudentsDto(Group group);

    GroupResponseTeachersDto toTeachersDto(Group group);

    GroupResponseSubjectsDto toSubjectsDto(Group group);

    List<GroupResponseOptionalDto> toListOptionalDtos(List<Group> groups);
}
