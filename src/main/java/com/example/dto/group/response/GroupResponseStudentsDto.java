package com.example.dto.group.response;

import com.example.model.users.Student;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public record GroupResponseStudentsDto(
        @NotNull
        Long id,
        @NotBlank
        String name,
        List<Student> students
) {
}
