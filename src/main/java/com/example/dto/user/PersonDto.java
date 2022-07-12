package com.example.dto.user;

import com.example.exceptions.CreateEntityException;
import com.example.model.users.Person;
import com.example.model.users.Student;
import com.example.model.users.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DateTimeException;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PersonDto {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String birthDate;
    private String login;
    private String password;
    private String role;

    public static Person convertToPerson(PersonDto personDto) {
        Person person;

        switch (personDto.getRole()) {
            case "Студент":
                person = new Student();
                break;
            case "Учитель":
                person = new Teacher();
                break;
            default:
                throw new CreateEntityException("Неверно выбрана роль!");
        }

        String firstName = personDto.getFirstName();
        String lastName = personDto.getLastName();
        String patronymic = personDto.getPatronymic();
        String birthDateString = personDto.getBirthDate();
        String login = personDto.getLogin();
        String password = personDto.getPassword();

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
