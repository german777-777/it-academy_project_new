package com.example.mapper.subject;

import com.example.dto.subject.SubjectRequestCreateDto;
import com.example.dto.subject.SubjectRequestUpdateDto;
import com.example.dto.subject.SubjectResponseDto;
import com.example.model.subject.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    @Mapping(source = "name", target = "name")
    Subject toEntity(SubjectRequestCreateDto subjectRequestCreateDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    Subject toEntity(SubjectRequestUpdateDto subjectRequestUpdateDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    SubjectResponseDto toDto(Subject subject);

    List<SubjectResponseDto> toListDtos(List<Subject> subjects);
}
