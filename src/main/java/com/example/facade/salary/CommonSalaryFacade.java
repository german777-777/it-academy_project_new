package com.example.facade.salary;

import com.example.annotation.Facade;
import com.example.annotation.Validate;
import com.example.dto.salary.SalaryRequestCreateDto;
import com.example.dto.salary.SalaryRequestUpdateDto;
import com.example.dto.salary.SalaryResponseDto;
import com.example.exceptions.ValidationException;
import com.example.mapper.salary.SalaryMapper;
import com.example.model.salary.Salary;
import com.example.service.salary.SalaryService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.util.constant.Constants.NOT_VALID_DATA_MESSAGE;

@Facade
@RequiredArgsConstructor
public class CommonSalaryFacade implements SalaryFacade {

    private final SalaryService salaryService;
    private final SalaryMapper salaryMapper;

    @Override
    public void saveSalary(SalaryRequestCreateDto salaryRequestCreateDto) {
        salaryService.save(salaryRequestCreateDto.getTeacherId(), salaryMapper.toEntity(salaryRequestCreateDto));
    }

    @Override
    public SalaryResponseDto getSalaryById(Long id) {
        return salaryMapper.toDto(salaryService.findById(id));
    }

    @Override
    public List<SalaryResponseDto> getAllSalaries() {
        return salaryMapper.toListDtos(salaryService.findAll());
    }

    @Override
    public List<SalaryResponseDto> getAllSalariesByTeacherId(Long teacherId) {
        return salaryMapper.toListDtos(salaryService.findByTeacherId(teacherId));
    }

    @Override
    @Validate
    public void updateSalary(SalaryRequestUpdateDto salaryRequestUpdateDto) {
        Salary salary = salaryService.findById(salaryRequestUpdateDto.getId());

        checkSalaryUpdateRequestParameters(salaryRequestUpdateDto, salary);

        salaryService.save(salaryRequestUpdateDto.getTeacherId(), salary);
    }

    @Override
    public void deleteSalary(Long id) {
        salaryService.delete(id);
    }

    private void checkSalaryUpdateRequestParameters(SalaryRequestUpdateDto salaryRequestUpdateDto, Salary salary) {
        if (salaryRequestUpdateDto.getDateOfIssue() == null && salaryRequestUpdateDto.getCount() == null) {
            throw new ValidationException(NOT_VALID_DATA_MESSAGE);
        }

        if (salaryRequestUpdateDto.getDateOfIssue() != null) {
            salary.setDateOfIssue(salaryRequestUpdateDto.getDateOfIssue());
        }

        if (salaryRequestUpdateDto.getCount() != null) {
            salary.setCount(salaryRequestUpdateDto.getCount());
        }
    }
}
