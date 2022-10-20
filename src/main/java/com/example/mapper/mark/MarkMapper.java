package com.example.mapper.mark;

import com.example.dto.mark.MarkRequestCreateDto;
import com.example.dto.mark.MarkResponseDto;
import com.example.model.mark.Mark;
import com.example.service.subjects.SubjectService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class MarkMapper {
    @Autowired
    protected SubjectService subjectService;

    @Mapping(target = "subject", expression = "java(subjectService.findById(markRequestCreateDto.getSubjectId()))")
    public abstract Mark toEntity(MarkRequestCreateDto markRequestCreateDto);

    @Mapping(target = "subjectName", expression = "java(mark.getSubject().getName())")
    public abstract MarkResponseDto toDto(Mark mark);

    public abstract List<MarkResponseDto> toListDtos(List<Mark> marks);


}
