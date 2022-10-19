package com.example.dto.rest.mark;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record MarkRequestUpdateDto(
        @NotNull
        Long id,
        @NotNull
        Long studentId,
        @NotNull
        Long subjectId,
        LocalDate dateOfIssue,
        Integer count
) {
}
