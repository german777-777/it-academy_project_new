package com.example.facade.mark;

import com.example.annotation.Facade;
import com.example.annotation.Validate;
import com.example.dto.rest.mark.MarkRequestCreateDto;
import com.example.dto.rest.mark.MarkRequestUpdateDto;
import com.example.dto.rest.mark.MarkResponseDto;
import com.example.exceptions.ValidationException;
import com.example.mapper.mark.MarkMapper;
import com.example.model.mark.Mark;
import com.example.service.marks.MarkService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.util.constant.Constants.NOT_VALID_DATA_MESSAGE;

@Facade(value = "restMarkFacade")
@RequiredArgsConstructor
public class CommonRestMarkFacade implements MarkFacade {

    private final MarkService markService;
    private final MarkMapper markMapper;

    @Override
    @Validate
    public void saveMark(MarkRequestCreateDto markRequestCreateDto) {
        markService.save(markRequestCreateDto.studentId(), markMapper.toEntity(markRequestCreateDto));
    }

    @Override
    public MarkResponseDto getMarkById(Long id) {
        return markMapper.toDto(markService.findById(id));
    }

    @Override
    public List<MarkResponseDto> getAllMarks() {
        return markMapper.toListDtos(markService.findAll());
    }

    @Override
    public List<MarkResponseDto> getAllMarksByStudentId(Long studentId) {
        return markMapper.toListDtos(markService.findByStudentId(studentId));
    }

    @Override
    @Validate
    public void updateMarks(MarkRequestUpdateDto markRequestUpdateDto) {
        Mark mark = markService.findById(markRequestUpdateDto.id());

        checkMarkUpdateRequestParameters(markRequestUpdateDto, mark);

        markService.save(markRequestUpdateDto.studentId(), mark);
    }

    @Override
    public void deleteMark(Long id) {
        markService.delete(id);
    }

    private void checkMarkUpdateRequestParameters(MarkRequestUpdateDto markRequestUpdateDto, Mark mark) {
        if (markRequestUpdateDto.dateOfIssue() == null && markRequestUpdateDto.count() == null) {
            throw new ValidationException(NOT_VALID_DATA_MESSAGE);
        }

        if (markRequestUpdateDto.dateOfIssue() != null) {
            mark.setDateOfIssue(markRequestUpdateDto.dateOfIssue());
        }

        if (markRequestUpdateDto.count() != null) {
            mark.setCount(markRequestUpdateDto.count());
        }
    }
}
