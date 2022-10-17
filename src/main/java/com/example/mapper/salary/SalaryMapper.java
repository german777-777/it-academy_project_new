package com.example.mapper.salary;

import com.example.dto.salary.SalaryRequestCreateDto;
import com.example.dto.salary.SalaryRequestUpdateDto;
import com.example.dto.salary.SalaryResponseDto;
import com.example.model.salary.Salary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SalaryMapper {

    @Mapping(source = "dateOfIssue", target = "dateOfIssue", expression = "java(salaryRequestCreateDto.dateOfIssue())")
    @Mapping(source = "count", target = "count", expression = "java(salaryRequestCreateDto.count())")
    Salary toEntity(SalaryRequestCreateDto salaryRequestCreateDto);

    @Mapping(source = "id", target = "id", expression = "java(salaryRequestUpdateDto.id())")
    @Mapping(source = "dateOfIssue", target = "dateOfIssue", expression = "java(salaryRequestUpdateDto.dateOfIssue())")
    @Mapping(source = "count", target = "count", expression = "java(salaryRequestUpdateDto.count())")
    Salary toEntity(SalaryRequestUpdateDto salaryRequestUpdateDto);

    @Mapping(source = "id", target = "id", expression = "java(salary.id())")
    @Mapping(source = "dateOfIssue", target = "dateOfIssue", expression = "java(salary.dateOfIssue())")
    @Mapping(source = "count", target = "count", expression = "java(salary.count())")
    SalaryResponseDto toDto(Salary salary);

    List<SalaryResponseDto> toListDtos(List<Salary> salaries);

}
