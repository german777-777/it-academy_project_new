package com.example.dto.rest.group;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record GroupRequestUpdateDto(
        @NotNull
        Long id,
        @NotBlank
        String name
) {
}
