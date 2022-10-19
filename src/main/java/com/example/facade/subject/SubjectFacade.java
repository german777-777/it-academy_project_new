package com.example.facade.subject;

import com.example.dto.rest.subject.SubjectRequestCreateDto;
import com.example.dto.rest.subject.SubjectRequestUpdateDto;
import com.example.dto.rest.subject.SubjectResponseDto;

import java.util.List;

public interface SubjectFacade {
    void saveSubject(SubjectRequestCreateDto subjectRequestCreateDto);
    
    SubjectResponseDto getSubjectById(Long id);

    SubjectResponseDto getSubjectByName(String name);
    
    List<SubjectResponseDto> getAllSubjects();
    
    void updateSubject(SubjectRequestUpdateDto subjectRequestUpdateDto);
    
    void deleteSubject(Long id);
}
