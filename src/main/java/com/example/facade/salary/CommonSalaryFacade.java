package com.example.facade.salary;

import com.example.annotation.Facade;
import com.example.annotation.Validate;
import com.example.dto.salary.SalaryRequestCreateDto;
import com.example.dto.salary.SalaryRequestUpdateDto;
import com.example.dto.salary.SalaryResponseDto;
import com.example.mapper.salary.SalaryMapper;
import com.example.service.salary.SalaryService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Facade
@RequiredArgsConstructor
public class CommonSalaryFacade implements SalaryFacade {

    private final SalaryService salaryService;
    private final SalaryMapper salaryMapper;

    @Override
    @Validate
    public void saveSalary(SalaryRequestCreateDto salaryRequestCreateDto) {
        salaryService.save(salaryRequestCreateDto.teacherId(), salaryMapper.toEntity(salaryRequestCreateDto));
    }

    @Override
    @Validate
    public SalaryResponseDto getSalaryById(Long id) {
        return salaryMapper.toDto(salaryService.findById(id));
    }

    @Override
    @Validate
    public List<SalaryResponseDto> getAllSalaries() {
        return salaryMapper.toListDtos(salaryService.findAll());
    }

    @Override
    @Validate
    public List<SalaryResponseDto> getAllSalariesByTeacherId(Long teacherId) {
        return salaryMapper.toListDtos(salaryService.findByTeacherId(teacherId));
    }

    @Override
    @Validate
    public void updateSalary(SalaryRequestUpdateDto salaryRequestUpdateDto) {
        salaryService.save(salaryRequestUpdateDto.teacherId(), salaryMapper.toEntity(salaryRequestUpdateDto));
    }

    @Override
    public void deleteSalary(Long id) {
        salaryService.delete(id);
    }
}