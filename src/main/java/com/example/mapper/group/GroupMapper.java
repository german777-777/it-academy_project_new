package com.example.mapper.group;

import com.example.dto.rest.group.GroupRequestCreateDto;
import com.example.dto.rest.group.GroupRequestUpdateDto;
import com.example.dto.rest.group.GroupResponseDto;
import com.example.model.group.Group;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    Group toEntity(GroupRequestCreateDto groupRequestCreateDto);

    Group toEntity(GroupRequestUpdateDto groupRequestUpdateDto);

    GroupResponseDto toDto(Group group);

    List<GroupResponseDto> toListDtos(List<Group> groups);
}
