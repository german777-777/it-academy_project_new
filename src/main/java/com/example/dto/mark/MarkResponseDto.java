package com.example.dto.mark;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record MarkResponseDto(
        @NotNull
        Long id,
        @NotBlank
        String subjectName,
        @NotNull
        LocalDate dateOfIssue,
        @NotNull
        Integer count
) {
}
