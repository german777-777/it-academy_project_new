package com.example.mapper.credentials;

import com.example.dto.credentials.CredentialsRequestDto;
import com.example.model.users.credentials.Credentials;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CredentialsMapper {
    Credentials toEntity(CredentialsRequestDto credentialsRequestDto);
}
