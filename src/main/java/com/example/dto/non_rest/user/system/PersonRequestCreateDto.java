package com.example.dto.non_rest.user.system;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class PersonRequestCreateDto {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String patronymic;
    @NotNull
    private LocalDate birthDate;
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    @NotBlank
    private String role;

    public PersonRequestCreateDto() {
    }

    public PersonRequestCreateDto(String firstName, String lastName, String patronymic, LocalDate birthDate, String login, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
