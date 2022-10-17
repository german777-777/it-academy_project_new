package com.example.facade.mark;

import com.example.annotation.Facade;
import com.example.annotation.Validate;
import com.example.dto.mark.MarkRequestCreateDto;
import com.example.dto.mark.MarkRequestUpdateDto;
import com.example.dto.mark.MarkResponseDto;
import com.example.mapper.mark.MarkMapper;
import com.example.service.marks.MarkService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Facade
@RequiredArgsConstructor
public class CommonMarkFacade implements MarkFacade {

    private final MarkService markService;
    private final MarkMapper markMapper;

    @Override
    @Validate
    public void saveMark(MarkRequestCreateDto markRequestCreateDto) {
        markService.save(markRequestCreateDto.studentId(), markMapper.toEntity(markRequestCreateDto));
    }

    @Override
    @Validate
    public MarkResponseDto getMarkById(Long id) {
        return markMapper.toDto(markService.findById(id));
    }

    @Override
    @Validate
    public List<MarkResponseDto> getAllMarks() {
        return markMapper.toListDtos(markService.findAll());
    }

    @Override
    @Validate
    public List<MarkResponseDto> getAllMarksByStudentId(Long studentId) {
        return markMapper.toListDtos(markService.findByStudentId(studentId));
    }

    @Override
    @Validate
    public void updateMarks(MarkRequestUpdateDto markRequestUpdateDto) {
        markService.save(markRequestUpdateDto.studentId(), markMapper.toEntity(markRequestUpdateDto));
    }

    @Override
    public void deleteMark(Long id) {
        markService.delete(id);
    }
}
