package com.example.dto.mark;

import java.time.LocalDate;

public record MarkResponseDto(
        Long id,
        String subjectName,
        LocalDate dateOfIssue,
        Integer count
) {
}
