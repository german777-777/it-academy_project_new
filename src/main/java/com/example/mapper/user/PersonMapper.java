package com.example.mapper.user;

import com.example.dto.user.person.PersonResponseDto;
import com.example.dto.user.system.PersonRequestCreateDto;
import com.example.exceptions.CreateEntityException;
import com.example.model.users.Person;
import com.example.model.users.Student;
import com.example.model.users.Teacher;
import com.example.model.users.credentials.Credentials;
import com.example.model.users.roles.Role;
import org.mapstruct.Mapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    default Person toEntity(PersonRequestCreateDto personRequestCreateDto) {
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

    default UserDetails toUserDetails(String login, String password, List<Role> roles) {
        List<SimpleGrantedAuthority> allAuthorities = new ArrayList<>();
        roles.forEach(role -> allAuthorities.addAll(role.getAuthorities()));
        return new User(login, password, true, true, true, true, allAuthorities);
    }

    PersonResponseDto toDto(Person person);

    List<PersonResponseDto> toListDtos(List<? extends Person> people);
}
