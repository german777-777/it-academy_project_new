package com.example.mapper.user.system;

import com.example.dto.user.person.PersonDto;
import com.example.dto.user.system.RegistrationDto;
import com.example.exceptions.CreateEntityException;
import com.example.model.users.Person;
import com.example.model.users.Student;
import com.example.model.users.Teacher;
import com.example.model.users.credentials.Credentials;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommonPersonMapper implements PersonMapper {
    @Override
    public Person toEntity(RegistrationDto registrationDto) {
        Person person = switch (registrationDto.role()) {
            case "Student" -> new Student();
            case "Teacher" -> new Teacher();
            default -> throw new CreateEntityException(registrationDto.role());
        };

        person.setFirstName(registrationDto.firstName());
        person.setLastName(registrationDto.lastName());
        person.setPatronymic(registrationDto.patronymic());
        person.setBirthDate(registrationDto.birthDate());
        person.setCredentials(new Credentials(registrationDto.login(), registrationDto.password()));

        return person;
    }

    @Override
    public PersonDto toDto(Person person) {
        return new PersonDto(person.getFirstName(), person.getLastName(), person.getPatronymic(), person.getBirthDate());
    }

    @Override
    public List<PersonDto> toListDtos(List<Person> people) {
        return people.stream()
                .map(this::toDto)
                .toList();
    }
}
