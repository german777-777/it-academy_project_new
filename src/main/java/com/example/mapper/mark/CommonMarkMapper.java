package com.example.mapper.mark;

import com.example.annotation.Mapper;
import com.example.dto.mark.MarkRequestCreateDto;
import com.example.dto.mark.MarkRequestUpdateDto;
import com.example.dto.mark.MarkResponseDto;
import com.example.model.mark.Mark;
import com.example.service.subjects.SubjectService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Mapper
@RequiredArgsConstructor
public class CommonMarkMapper implements MarkMapper {

    private final SubjectService subjectService;

    @Override
    public Mark toEntity(MarkRequestCreateDto markRequestCreateDto) {
        Mark mark = new Mark();
        mark.setSubject(subjectService.findById(markRequestCreateDto.subjectId()));
        mark.setDateOfIssue(markRequestCreateDto.dateOfIssue());
        mark.setCount(markRequestCreateDto.count());
        return mark;
    }

    @Override
    public Mark toEntity(MarkRequestUpdateDto markRequestUpdateDto) {
        Mark mark = new Mark();
        mark.setId(markRequestUpdateDto.id());
        mark.setSubject(subjectService.findById(markRequestUpdateDto.subjectId()));
        mark.setDateOfIssue(markRequestUpdateDto.dateOfIssue());
        mark.setCount(markRequestUpdateDto.count());
        return null;
    }

    @Override
    public MarkResponseDto toDto(Mark mark) {
        return new MarkResponseDto(mark.getId(), mark.getSubject().getName(), mark.getDateOfIssue(), mark.getCount());
    }

    @Override
    public List<MarkResponseDto> toListDtos(List<Mark> marks) {
        return marks.stream()
                .map(this::toDto)
                .toList();
    }
}
