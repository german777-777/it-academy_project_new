package com.example.mapper.subject;

import com.example.dto.rest.subject.SubjectRequestCreateDto;
import com.example.dto.rest.subject.SubjectRequestUpdateDto;
import com.example.dto.rest.subject.SubjectResponseDto;
import com.example.model.subject.Subject;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    Subject toEntity(SubjectRequestCreateDto subjectRequestCreateDto);

    Subject toEntity(SubjectRequestUpdateDto subjectRequestUpdateDto);

    SubjectResponseDto toDto(Subject subject);

    List<SubjectResponseDto> toListDtos(List<Subject> subjects);
}
