package com.example.facade.salary;

import com.example.dto.salary.SalaryRequestCreateDto;
import com.example.dto.salary.SalaryRequestUpdateDto;
import com.example.dto.salary.SalaryResponseDto;

import java.util.List;

public interface SalaryFacade {
    void saveSalary(SalaryRequestCreateDto salaryRequestCreateDto);

    SalaryResponseDto getSalaryById(Long id);

    List<SalaryResponseDto> getAllSalaries();

    List<SalaryResponseDto> getAllSalariesByTeacherId(Long teacherId);

    void updateSalary(SalaryRequestUpdateDto salaryRequestUpdateDto);

    void deleteSalary(Long id);
}
