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

    @Mapping(source = "dateOfIssue", target = "dateOfIssue")
    @Mapping(source = "count", target = "count")
    Salary toEntity(SalaryRequestCreateDto salaryRequestCreateDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "dateOfIssue", target = "dateOfIssue")
    @Mapping(source = "count", target = "count")
    Salary toEntity(SalaryRequestUpdateDto salaryRequestUpdateDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "dateOfIssue", target = "dateOfIssue")
    @Mapping(source = "count", target = "count")
    SalaryResponseDto toDto(Salary salary);

    List<SalaryResponseDto> toListDtos(List<Salary> salaries);

}
