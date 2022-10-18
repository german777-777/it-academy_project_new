package com.example.mapper.user;

import com.example.dto.user.person.PersonRequestUpdateDto;
import com.example.dto.user.person.PersonResponseDto;
import com.example.dto.user.system.PersonRequestCreateDto;
import com.example.model.users.Person;
import com.example.model.users.roles.Role;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface PersonMapper {
    Person toEntity(PersonRequestCreateDto personRequestCreateDto);

    Person toEntity(PersonRequestUpdateDto personRequestUpdateDto);

    PersonResponseDto toDto(Person person);

    List<PersonResponseDto> toListDtos(List<? extends Person> people);

    UserDetails toUserDetails(String login, String password, List<Role> roles);
}
