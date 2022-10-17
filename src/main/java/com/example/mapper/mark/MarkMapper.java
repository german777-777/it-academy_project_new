package com.example.mapper.mark;

import com.example.dto.mark.MarkRequestCreateDto;
import com.example.dto.mark.MarkRequestUpdateDto;
import com.example.dto.mark.MarkResponseDto;
import com.example.model.mark.Mark;

import java.util.List;

public interface MarkMapper {

    Mark toEntity(MarkRequestCreateDto markRequestCreateDto);

    Mark toEntity(MarkRequestUpdateDto markRequestUpdateDto);

    MarkResponseDto toDto(Mark mark);

    List<MarkResponseDto> toListDtos(List<Mark> marks);

}
