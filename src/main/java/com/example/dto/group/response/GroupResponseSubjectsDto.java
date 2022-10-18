package com.example.dto.group.response;

import com.example.model.subject.Subject;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public record GroupResponseSubjectsDto(
        @NotNull
        Long id,
        @NotBlank
        String name,
        List<Subject> subjects
) {
}
