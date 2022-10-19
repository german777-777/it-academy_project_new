package com.example.dto.rest.subject;

import javax.validation.constraints.NotBlank;

public record SubjectRequestCreateDto(
        @NotBlank
        String name
) {}
