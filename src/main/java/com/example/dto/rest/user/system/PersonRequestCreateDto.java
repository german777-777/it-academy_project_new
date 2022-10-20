package com.example.dto.rest.user.system;

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
        LocalDate birthDate,
        @NotBlank
        String login,
        @NotBlank
        String password,
        @NotBlank
        String role
) {}
