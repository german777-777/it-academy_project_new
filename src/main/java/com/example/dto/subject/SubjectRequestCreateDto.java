package com.example.dto.subject;

import javax.validation.constraints.NotBlank;

public record SubjectRequestCreateDto(
        @NotBlank
        String name
) {}
