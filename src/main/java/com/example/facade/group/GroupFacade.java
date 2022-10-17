package com.example.facade.group;

import com.example.dto.group.GroupRequestCreateDto;
import com.example.dto.group.GroupRequestUpdateDto;
import com.example.dto.group.GroupResponseDto;

import java.util.List;

public interface GroupFacade {
    void saveGroup(GroupRequestCreateDto groupRequestCreateDto);

    GroupResponseDto getGroupById(Long id);

    List<GroupResponseDto> getAllGroups();

    void updateGroup(GroupRequestUpdateDto groupRequestUpdateDto);

    void delete(Long id);
}
