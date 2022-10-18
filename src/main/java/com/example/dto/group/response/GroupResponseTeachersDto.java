package com.example.dto.group.response;

import com.example.model.users.Teacher;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public record GroupResponseTeachersDto(
        @NotNull
        Long id,
        @NotBlank
        String name,
        List<Teacher> teachers
) {
}