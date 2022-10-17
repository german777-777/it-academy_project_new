package com.example.dto.group;

import javax.validation.constraints.NotBlank;

public record GroupRequestCreateDto(
        @NotBlank
        String name
) {}
