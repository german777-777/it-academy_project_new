package com.example.dto.salary;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class SalaryRequestUpdateDto {
    @NotNull
    private Long id;
    @NotNull
    private Long teacherId;
    private LocalDate dateOfIssue;
    private Integer count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
