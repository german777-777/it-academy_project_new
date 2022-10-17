package com.example.dto.subject;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record SubjectResponseDto(
        @NotNull
        Long id,
        @NotBlank
        String name
) {}
