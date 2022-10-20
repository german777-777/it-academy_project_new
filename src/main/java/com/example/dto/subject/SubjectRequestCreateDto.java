package com.example.dto.subject;

import javax.validation.constraints.NotBlank;

public class SubjectRequestCreateDto {
    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
