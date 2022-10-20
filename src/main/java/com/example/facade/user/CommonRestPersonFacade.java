package com.example.facade.user;

import com.example.annotation.Facade;
import com.example.annotation.Validate;
import com.example.dto.rest.user.person.PersonRequestUpdateDto;
import com.example.dto.rest.user.person.PersonResponseDto;
import com.example.dto.rest.user.system.PersonRequestCreateDto;
import com.example.exceptions.ValidationException;
import com.example.mapper.user.PersonMapper;
import com.example.model.users.Person;
import com.example.service.group.GroupService;
import com.example.service.users.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

import static com.example.util.constant.Constants.NOT_VALID_DATA_MESSAGE;

@Facade(value = "restPersonFacade")
@RequiredArgsConstructor
public class CommonRestPersonFacade implements PersonFacade<PersonRequestCreateDto, PersonRequestUpdateDto, PersonResponseDto> {
    private final PersonService personService;
    private final GroupService groupService;
    private final PersonMapper personMapper;

    @Override
    @Validate
    public void savePerson(PersonRequestCreateDto personRequestCreateDto) {
        personService.save(personMapper.toEntity(personRequestCreateDto));
    }

    @Override
    public PersonResponseDto getPersonById(Long id) {
        return personMapper.toDto(personService.findById(id));
    }

    @Override
    public List<PersonResponseDto> getStudentsByGroupId(Long groupId) {
        return personMapper.toListDtos(groupService.findById(groupId).getStudents());
    }

    @Override
    public List<PersonResponseDto> getTeachersByGroupId(Long groupId) {
        return personMapper.toListDtos(groupService.findById(groupId).getTeachers());
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Person person = personService.findByLogin(login);
        return personMapper.toUserDetails(person.getLogin(), person.getPassword(), person.getRoles());
    }

    @Override
    public List<PersonResponseDto> getAllStudents() {
        return personMapper.toListDtos(personService.findAllStudents());
    }

    @Override
    public List<PersonResponseDto> getAllTeachers() {
        return personMapper.toListDtos(personService.findAllTeachers());
    }

    @Override
    @Validate
    public void updatePerson(PersonRequestUpdateDto personRequestUpdateDto) {
        Person person = personService.findById(personRequestUpdateDto.id());

        checkPersonUpdateRequestParameters(personRequestUpdateDto, person);

        personService.update(person);
    }

    private void checkPersonUpdateRequestParameters(PersonRequestUpdateDto personRequestUpdateDto, Person person) {
        if (personRequestUpdateDto.firstName() == null
                && personRequestUpdateDto.lastName() == null
                && personRequestUpdateDto.patronymic() == null
                && personRequestUpdateDto.birthDate() == null
                && personRequestUpdateDto.login() == null
                && personRequestUpdateDto.password() == null
        ) {
            throw new ValidationException(NOT_VALID_DATA_MESSAGE);
        }

        if (personRequestUpdateDto.firstName() != null) {
            person.setFirstName(personRequestUpdateDto.firstName());
        }

        if (personRequestUpdateDto.lastName() != null) {
            person.setLastName(personRequestUpdateDto.lastName());
        }

        if (personRequestUpdateDto.patronymic() != null) {
            person.setPatronymic(personRequestUpdateDto.patronymic());
        }

        if (personRequestUpdateDto.birthDate() != null) {
            person.setBirthDate(personRequestUpdateDto.birthDate());
        }

        if (personRequestUpdateDto.login() != null) {
            person.setLogin(personRequestUpdateDto.login());
        }

        if (personRequestUpdateDto.password() != null) {
            person.setPassword(personRequestUpdateDto.password());
        }
    }

    @Override
    public void deletePerson(Long id) {
        personService.delete(id);
    }
}
