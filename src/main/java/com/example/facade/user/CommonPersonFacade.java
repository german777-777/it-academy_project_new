package com.example.facade.user;

import com.example.annotation.Facade;
import com.example.annotation.Validate;
import com.example.dto.user.person.PersonRequestUpdateDto;
import com.example.dto.user.person.PersonResponseDto;
import com.example.dto.user.system.PersonRequestCreateDto;
import com.example.mapper.user.PersonMapper;
import com.example.model.users.Person;
import com.example.model.users.Student;
import com.example.model.users.Teacher;
import com.example.service.users.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@Facade
@RequiredArgsConstructor
public class CommonPersonFacade implements PersonFacade {

    private final PersonService personService;
    private final PersonMapper personMapper;

    @Validate
    @Override
    public void savePerson(PersonRequestCreateDto personRequestCreateDto) {
        personService.save(personMapper.toEntity(personRequestCreateDto));
    }

    @Override
    @Validate
    public PersonResponseDto getPersonById(Long id) {
        return personMapper.toDto(personService.findById(id));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personService.findByLogin(username);
        return personMapper.toUserDetails(person.getLogin(), person.getPassword(), person.getRoles());
    }

    @Override
    @Validate
    public List<PersonResponseDto> getAllStudents() {
        return personMapper.toListDtos(personService.findAll().stream()
                .filter(person -> person.getClass().equals(Student.class))
                .toList());
    }

    @Override
    @Validate
    public List<PersonResponseDto> getAllTeachers() {
        return personMapper.toListDtos(personService.findAll().stream()
                .filter(person -> person.getClass().equals(Teacher.class))
                .toList());
    }

    @Override
    @Validate
    public void updatePerson(PersonRequestUpdateDto personRequestUpdateDto) {
        personService.update(personMapper.toEntity(personRequestUpdateDto));
    }

    @Override
    public void deletePerson(Long id) {
        personService.delete(id);
    }
}
