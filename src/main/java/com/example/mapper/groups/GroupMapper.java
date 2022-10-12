package com.example.mapper.groups;

import com.example.dto.group.GroupRequestDto;
import com.example.model.group.Group;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    Group toEntity(GroupRequestDto groupRequestDto);
}
