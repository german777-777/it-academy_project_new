package com.example.facade.group;

import com.example.dto.group.GroupRequestCreateDto;
import com.example.dto.group.GroupRequestUpdateDto;
import com.example.dto.group.response.GroupResponseOptionalDto;
import com.example.dto.group.response.GroupResponseStudentsDto;
import com.example.dto.group.response.GroupResponseSubjectsDto;
import com.example.dto.group.response.GroupResponseTeachersDto;

import java.util.List;

public interface GroupFacade {
    void saveGroup(GroupRequestCreateDto groupRequestCreateDto);

    GroupResponseOptionalDto getGroupById(Long id);

    GroupResponseStudentsDto getStudentsByGroupId(Long groupId);

    GroupResponseTeachersDto getTeachersByGroupId(Long groupId);

    GroupResponseSubjectsDto getSubjectsByGroupId(Long groupId);

    List<GroupResponseOptionalDto> getAllGroups();

    void updateGroup(GroupRequestUpdateDto groupRequestUpdateDto);

    void delete(Long id);
}
