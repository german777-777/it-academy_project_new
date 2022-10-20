package com.example.dto.salary;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class SalaryRequestCreateDto {
    @NotNull
    private Long teacherId;
    @NotNull
    private LocalDate dateOfIssue;
    @NotNull
    private Integer count;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
