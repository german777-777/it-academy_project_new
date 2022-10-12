package com.example.dto.credentials;

import javax.validation.constraints.NotBlank;

public record CredentialsRequestDto(
        @NotBlank
        String login,
        @NotBlank
        String password
) {
}
