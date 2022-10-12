package com.example.mapper.user;

import com.example.dto.user.RegistrationRequestDto;
import com.example.model.users.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "birthDate", expression = "java(LocalDate.parse(registrationRequestDto.birthDate())))")
    @Mapping(target = "credentials", expression = "java(new Credentials(registrationRequestDto.login(), registrationRequestDto.password()))")
    /*@Mapping(target = "roles", expression = "java(List.of(registrationRequestDto.role()))")*/
    Person toEntity(RegistrationRequestDto registrationRequestDto);
}
