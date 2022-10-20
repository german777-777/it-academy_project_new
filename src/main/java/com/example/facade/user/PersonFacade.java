package com.example.facade.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface PersonFacade<RequestCreateDto, RequestUpdateDto, ResponseDto> extends UserDetailsService {
    void savePerson(RequestCreateDto personRequestCreateDto);

    ResponseDto getPersonById(Long id);

    List<ResponseDto> getStudentsByGroupId(Long groupId);

    List<ResponseDto> getTeachersByGroupId(Long groupId);

    List<ResponseDto> getAllStudents();

    List<ResponseDto> getAllTeachers();

    void updatePerson(RequestUpdateDto personRequestUpdateDto);

    void deletePerson(Long id);
}
