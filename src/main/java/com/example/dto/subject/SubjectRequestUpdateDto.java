package com.example.dto.subject;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SubjectRequestUpdateDto {
    @NotNull
    private Long id;
    @NotBlank
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
