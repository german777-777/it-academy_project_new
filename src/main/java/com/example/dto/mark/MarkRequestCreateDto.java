package com.example.dto.mark;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class MarkRequestCreateDto {
    @NotNull
    private Long studentId;
    @NotNull
    private Long subjectId;
    @NotNull
    private LocalDate dateOfIssue;
    @NotNull
    private Integer count;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
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
