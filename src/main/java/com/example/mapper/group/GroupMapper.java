package com.example.mapper.group;

import com.example.dto.group.GroupRequestCreateDto;
import com.example.dto.group.GroupRequestUpdateDto;
import com.example.dto.group.GroupResponseDto;
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
