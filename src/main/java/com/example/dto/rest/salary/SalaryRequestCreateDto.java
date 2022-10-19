package com.example.dto.rest.salary;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record SalaryRequestCreateDto(
        @NotNull
        Long teacherId,
        @NotNull
        LocalDate dateOfIssue,
        @NotNull
        Integer count
) {
}
