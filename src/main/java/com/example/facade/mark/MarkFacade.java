package com.example.facade.mark;

import com.example.dto.mark.MarkRequestCreateDto;
import com.example.dto.mark.MarkRequestUpdateDto;
import com.example.dto.mark.MarkResponseDto;

import java.util.List;

public interface MarkFacade {
    void saveMark(MarkRequestCreateDto markRequestCreateDto);

    MarkResponseDto getMarkById(Long id);

    List<MarkResponseDto> getAllMarks();

    List<MarkResponseDto> getAllMarksByStudentId(Long studentId);

    void updateMarks(MarkRequestUpdateDto markRequestUpdateDto);

    void deleteMark(Long id);
}
