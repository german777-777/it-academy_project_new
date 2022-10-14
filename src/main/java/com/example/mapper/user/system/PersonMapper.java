package com.example.mapper.user.system;

import com.example.dto.user.person.PersonDto;
import com.example.dto.user.system.RegistrationDto;
import com.example.model.users.Person;

import java.util.List;

public interface PersonMapper {
    Person toEntity(RegistrationDto registrationDto);

    PersonDto toDto(Person person);

    List<PersonDto> toListDtos(List<Person> people);

}
