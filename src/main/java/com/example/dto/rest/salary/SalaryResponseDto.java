package com.example.dto.rest.salary;

import java.time.LocalDate;

public record SalaryResponseDto(
        Long id,
        LocalDate dateOfIssue,
        Integer count
) {
}
