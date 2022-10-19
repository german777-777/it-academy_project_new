package com.example.dto.rest.user.system;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record PersonRequestCreateDto(
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
        String password,
        @NotBlank
        String role
) {}
