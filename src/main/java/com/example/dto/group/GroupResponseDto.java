package com.example.dto.group;

import com.example.model.users.Teacher;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public record GroupResponseDto(
        @NotNull
        Long id,
        @NotBlank
        String name,
        @NotNull
        List<Teacher> teachers
) {
}
