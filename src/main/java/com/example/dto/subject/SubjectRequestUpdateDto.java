package com.example.dto.subject;

import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record SubjectRequestUpdateDto(
        @NotNull
        @Setter
        Long id,
        @NotBlank
        String name
) {
}
