package com.example.dto.rest.group;

import javax.validation.constraints.NotBlank;

public record GroupRequestCreateDto(
        @NotBlank
        String name
) {}
