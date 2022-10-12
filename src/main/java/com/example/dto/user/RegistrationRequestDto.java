package com.example.dto.user;

import com.example.exceptions.CreateEntityException;
import com.example.model.users.Person;
import com.example.model.users.Student;
import com.example.model.users.Teacher;

import javax.validation.constraints.NotBlank;
import java.time.DateTimeException;
import java.time.LocalDate;

public record RegistrationRequestDto(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotBlank
        String patronymic,
        @NotBlank
        String birthDate,
        @NotBlank
        String login,
        @NotBlank
        String password,
        @NotBlank
        String role
) {
    public static Person convertToPerson(RegistrationRequestDto personDto) {
        Person person = switch (personDto.role()) {
            case "Студент" -> new Student();
            case "Учитель" -> new Teacher();
            default -> throw new CreateEntityException("Неверно выбрана роль!");
        };

        String firstName = personDto.firstName();
        String lastName = personDto.lastName();
        String patronymic = personDto.patronymic();
        String birthDateString = personDto.birthDate();
        String login = personDto.login();
        String password = personDto.password();

        if (!firstName.isEmpty()) {
            person.setFirstName(firstName);
        }

        if (!lastName.isEmpty()) {
            person.setLastName(lastName);
        }

        if (!patronymic.isEmpty()) {
            person.setPatronymic(patronymic);
        }

        if (!birthDateString.isEmpty()) {
            try {
                person.setBirthDate(LocalDate.parse(birthDateString));
            } catch (DateTimeException ex) {
                person.setBirthDate(null);
            }
        }

        if (!login.isEmpty()) {
            person.setLogin(login);
        }

        if (!password.isEmpty()) {
            person.setPassword(password);
        }

        return person;
    }
}
