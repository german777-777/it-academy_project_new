package com.example.facade.subject;

import com.example.annotation.Facade;
import com.example.annotation.Validate;
import com.example.dto.subject.SubjectRequestCreateDto;
import com.example.dto.subject.SubjectRequestUpdateDto;
import com.example.dto.subject.SubjectResponseDto;
import com.example.mapper.subject.SubjectMapper;
import com.example.service.subjects.SubjectService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Facade
@RequiredArgsConstructor
public class CommonSubjectFacade implements SubjectFacade {

    private final SubjectService subjectService;
    private final SubjectMapper subjectMapper;

    @Override
    @Validate
    public void saveSubject(SubjectRequestCreateDto subjectRequestCreateDto) {
        subjectService.save(subjectMapper.toEntity(subjectRequestCreateDto));
    }

    @Override
    public SubjectResponseDto getSubjectById(Long id) {
        return subjectMapper.toDto(subjectService.findById(id));
    }

    @Override
    public SubjectResponseDto getSubjectByName(String name) {
        return subjectMapper.toDto(subjectService.findByName(name));
    }

    @Override
    public List<SubjectResponseDto> getAllSubjects() {
        return subjectMapper.toListDtos(subjectService.findAll());
    }

    @Override
    @Validate
    public void updateSubject(SubjectRequestUpdateDto subjectRequestUpdateDto) {
        subjectService.update(subjectMapper.toEntity(subjectRequestUpdateDto));
    }

    @Override
    public void deleteSubject(Long id) {
        subjectService.delete(id);
    }
}
