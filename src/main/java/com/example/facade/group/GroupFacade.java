package com.example.facade.group;

import com.example.dto.rest.group.GroupRequestCreateDto;
import com.example.dto.rest.group.GroupRequestUpdateDto;
import com.example.dto.rest.group.GroupResponseDto;
import com.example.dto.rest.subject.SubjectResponseDto;
import com.example.dto.rest.user.person.PersonResponseDto;

import java.util.List;

public interface GroupFacade {
    void saveGroup(GroupRequestCreateDto groupRequestCreateDto);

    GroupResponseDto getGroupById(Long id);

    List<PersonResponseDto> getStudentsByGroupId(Long groupId);

    List<PersonResponseDto> getTeachersByGroupId(Long groupId);

    List<SubjectResponseDto> getSubjectsByGroupId(Long groupId);

    List<GroupResponseDto> getAllGroups();

    void updateGroup(GroupRequestUpdateDto groupRequestUpdateDto);

    void delete(Long id);
}
