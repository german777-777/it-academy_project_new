package com.example.dto.salary;

import java.time.LocalDate;

public record SalaryResponseDto(
        Long id,
        LocalDate dateOfIssue,
        Integer count
) {
}
