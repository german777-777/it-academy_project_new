package com.example.dto.user.person;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record PersonRequestUpdateDto(
        @NotNull
        Long id,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotBlank
        String patronymic,
        @NotNull
        @Valid
        LocalDate birthDate,
        @NotBlank
        String login,
        @NotBlank
        String password
) {}
