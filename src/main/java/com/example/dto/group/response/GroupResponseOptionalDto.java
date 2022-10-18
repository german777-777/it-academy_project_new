package com.example.dto.group.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record GroupResponseOptionalDto(
        @NotNull
        Long id,
        @NotBlank
        String name
) {
}
