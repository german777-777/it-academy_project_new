package com.example.dto.rest.user.person;

import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record PersonRequestUpdateDto(
        @NotNull
        @Setter
        Long id,
        String firstName,
        String lastName,
        String patronymic,
        LocalDate birthDate,
        String login,
        String password
) {}
