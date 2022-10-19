package com.example.facade.user;

import com.example.dto.rest.user.person.PersonRequestUpdateDto;
import com.example.dto.rest.user.person.PersonResponseDto;
import com.example.dto.rest.user.system.PersonRequestCreateDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface PersonFacade extends UserDetailsService {
    void savePerson(PersonRequestCreateDto personRequestCreateDto);

    PersonResponseDto getPersonById(Long id);

    List<PersonResponseDto> getAllStudents();

    List<PersonResponseDto> getAllTeachers();

    void updatePerson(PersonRequestUpdateDto personRequestUpdateDto);

    void deletePerson(Long id);
}
