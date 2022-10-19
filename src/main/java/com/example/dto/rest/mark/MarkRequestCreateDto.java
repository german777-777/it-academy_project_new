package com.example.dto.rest.mark;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record MarkRequestCreateDto(
        @NotNull
        Long studentId,
        @NotNull
        Long subjectId,
        @NotNull
        LocalDate dateOfIssue,
        @NotNull
        Integer count
) {
}
