package com.example.dto.group;

import javax.validation.constraints.NotBlank;

public class GroupRequestCreateDto {
    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
