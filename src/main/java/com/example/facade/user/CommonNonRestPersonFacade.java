package com.example.facade.user;

import com.example.annotation.Facade;
import com.example.dto.non_rest.user.person.PersonRequestUpdateDto;
import com.example.dto.non_rest.user.person.PersonResponseDto;
import com.example.dto.non_rest.user.system.PersonRequestCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@Facade(value = "nonRestPersonFacade")
@RequiredArgsConstructor
public class CommonNonRestPersonFacade implements PersonFacade<PersonRequestCreateDto, PersonRequestUpdateDto, PersonResponseDto> {
    @Override
    public void savePerson(PersonRequestCreateDto personRequestCreateDto) {

    }

    @Override
    public PersonResponseDto getPersonById(Long id) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public List<PersonResponseDto> getStudentsByGroupId(Long groupId) {
        return null;
    }

    @Override
    public List<PersonResponseDto> getTeachersByGroupId(Long groupId) {
        return null;
    }

    @Override
    public List<PersonResponseDto> getAllStudents() {
        return null;
    }

    @Override
    public List<PersonResponseDto> getAllTeachers() {
        return null;
    }

    @Override
    public void updatePerson(PersonRequestUpdateDto personRequestUpdateDto) {

    }

    @Override
    public void deletePerson(Long id) {

    }
}
