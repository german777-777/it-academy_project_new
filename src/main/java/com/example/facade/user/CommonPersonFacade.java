package com.example.facade.user;

import com.example.annotation.Facade;
import com.example.annotation.Validate;
import com.example.dto.user.person.PersonRequestUpdateDto;
import com.example.dto.user.person.PersonResponseDto;
import com.example.dto.user.system.PersonRequestCreateDto;
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

@Facade
@RequiredArgsConstructor
public class CommonPersonFacade implements PersonFacade {
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
        Person person = personService.findById(personRequestUpdateDto.getId());

        checkPersonUpdateRequestParameters(personRequestUpdateDto, person);

        personService.update(person);
    }

    private void checkPersonUpdateRequestParameters(PersonRequestUpdateDto personRequestUpdateDto, Person person) {
        if (personRequestUpdateDto.getFirstName() == null
                && personRequestUpdateDto.getLastName() == null
                && personRequestUpdateDto.getPatronymic() == null
                && personRequestUpdateDto.getBirthDate() == null
                && personRequestUpdateDto.getLogin() == null
                && personRequestUpdateDto.getPassword() == null
        ) {
            throw new ValidationException(NOT_VALID_DATA_MESSAGE);
        }

        if (personRequestUpdateDto.getFirstName() != null) {
            person.setFirstName(personRequestUpdateDto.getFirstName());
        }

        if (personRequestUpdateDto.getLastName() != null) {
            person.setLastName(personRequestUpdateDto.getLastName());
        }

        if (personRequestUpdateDto.getPatronymic() != null) {
            person.setPatronymic(personRequestUpdateDto.getPatronymic());
        }

        if (personRequestUpdateDto.getBirthDate() != null) {
            person.setBirthDate(personRequestUpdateDto.getBirthDate());
        }

        if (personRequestUpdateDto.getLogin() != null) {
            person.setLogin(personRequestUpdateDto.getLogin());
        }

        if (personRequestUpdateDto.getPassword() != null) {
            person.setPassword(personRequestUpdateDto.getPassword());
        }
    }

    @Override
    public void deletePerson(Long id) {
        personService.delete(id);
    }
}
