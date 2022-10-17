package com.example.dto.salary;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record SalaryResponseDto(
        @NotNull
        Long id,
        @NotNull
        LocalDate dateOfIssue,
        @NotNull
        Integer count
) {
}
