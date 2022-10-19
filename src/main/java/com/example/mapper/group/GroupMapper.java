package com.example.mapper.group;

import com.example.dto.rest.group.GroupRequestCreateDto;
import com.example.dto.rest.group.GroupRequestUpdateDto;
import com.example.dto.rest.group.GroupResponseDto;
import com.example.dto.rest.subject.SubjectResponseDto;
import com.example.dto.rest.user.person.PersonResponseDto;
import com.example.mapper.subject.SubjectMapper;
import com.example.mapper.user.PersonMapper;
import com.example.model.group.Group;
import com.example.model.subject.Subject;
import com.example.model.users.Student;
import com.example.model.users.Teacher;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class GroupMapper {

    protected PersonMapper personMapper;
    protected SubjectMapper subjectMapper;

    public abstract Group toEntity(GroupRequestCreateDto groupRequestCreateDto);

    public abstract Group toEntity(GroupRequestUpdateDto groupRequestUpdateDto);

    public abstract GroupResponseDto toDto(Group group);

    public abstract List<GroupResponseDto> toListDtos(List<Group> groups);

    public List<PersonResponseDto> toStudentsDtos(List<Student> students) {
        return personMapper.toListDtos(students);
    }

    public List<PersonResponseDto> toTeachersDtos(List<Teacher> teachers) {
        return personMapper.toListDtos(teachers);
    }

    public List<SubjectResponseDto> toSubjectsDtos(List<Subject> subjects) {
        return subjectMapper.toListDtos(subjects);
    }
}
