package com.example.dto.salary;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record SalaryRequestUpdateDto(
        @NotNull
        Long id,
        @NotNull
        Long teacherId,
        LocalDate dateOfIssue,
        Integer count
) {
}
