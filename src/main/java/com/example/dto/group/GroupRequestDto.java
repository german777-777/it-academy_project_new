package com.example.dto.group;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record GroupRequestDto(
        @NotBlank
        @Size(min = 4)
        String name) {
}
