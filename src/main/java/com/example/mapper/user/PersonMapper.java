package com.example.mapper.user;

import com.example.dto.user.person.PersonRequestUpdateDto;
import com.example.dto.user.person.PersonResponseDto;
import com.example.dto.user.system.PersonRequestCreateDto;
import com.example.exceptions.CreateEntityException;
import com.example.model.users.Person;
import com.example.model.users.Student;
import com.example.model.users.Teacher;
import com.example.model.users.credentials.Credentials;
import com.example.model.users.roles.Role;
import com.example.service.users.PersonService;
import org.mapstruct.Mapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PersonMapper {
    protected PersonService personService;

    public Person toEntity(PersonRequestCreateDto personRequestCreateDto) {
        Person person = switch (personRequestCreateDto.role()) {
            case "Student" -> new Student();
            case "Teacher" -> new Teacher();
            default -> throw new CreateEntityException(" cause not correct role");
        };

        person.setFirstName(personRequestCreateDto.firstName());
        person.setLastName(personRequestCreateDto.lastName());
        person.setPatronymic(personRequestCreateDto.patronymic());
        person.setBirthDate(personRequestCreateDto.birthDate());
        person.setCredentials(new Credentials(personRequestCreateDto.login(), personRequestCreateDto.password()));

        return person;
    }

    public Person toEntity(PersonRequestUpdateDto personRequestUpdateDto) {
        Person person = personService.findById(personRequestUpdateDto.id());

        person.setFirstName(personRequestUpdateDto.firstName());
        person.setLastName(personRequestUpdateDto.lastName());
        person.setPatronymic(personRequestUpdateDto.patronymic());
        person.setBirthDate(personRequestUpdateDto.birthDate());
        person.setCredentials(new Credentials(personRequestUpdateDto.login(), personRequestUpdateDto.password()));

        return person;
    }


    public abstract PersonResponseDto toDto(Person person);

    public abstract List<PersonResponseDto> toListDtos(List<? extends Person> people);

    public UserDetails toUserDetails(String login, String password, List<Role> roles) {
        List<SimpleGrantedAuthority> allAuthorities = new ArrayList<>();
        roles.forEach(role -> allAuthorities.addAll(role.getAuthorities()));
        return new User(login, password, true, true, true, true, allAuthorities);
    }
}
