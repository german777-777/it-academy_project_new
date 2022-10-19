package com.example.mapper.salary;

import com.example.dto.rest.salary.SalaryRequestCreateDto;
import com.example.dto.rest.salary.SalaryResponseDto;
import com.example.model.salary.Salary;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SalaryMapper {

    Salary toEntity(SalaryRequestCreateDto salaryRequestCreateDto);

    SalaryResponseDto toDto(Salary salary);

    List<SalaryResponseDto> toListDtos(List<Salary> salaries);

}
