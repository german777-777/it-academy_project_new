package com.example.service.users;

import com.example.exceptions.CreateEntityException;
import com.example.exceptions.DeleteEntityException;
import com.example.exceptions.NotFoundEntityException;
import com.example.exceptions.UpdateEntityException;
import com.example.model.users.Person;
import com.example.model.users.Student;
import com.example.model.users.Teacher;
import com.example.repository.PersonRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.util.constant.Constants.ALREADY_EXISTING_WITH_LOGIN_MESSAGE;
import static com.example.util.constant.Constants.BY_ID_MESSAGE;
import static com.example.util.constant.Constants.BY_LOGIN_MESSAGE;
import static com.example.util.constant.Constants.NOT_EXISTING_WITH_THIS_ID;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.springframework.transaction.annotation.Isolation.REPEATABLE_READ;

@Service
public class CommonPersonService implements PersonService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public CommonPersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Person save(Person person) {
        if (TRUE.equals(personRepository.ifExistsByLogin(person.getLogin()))) {
            throw new CreateEntityException(ALREADY_EXISTING_WITH_LOGIN_MESSAGE);
        }
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException(BY_ID_MESSAGE));
    }

    @Override
    public Person findByLogin(String login) {
        return personRepository.findByLogin(login)
                .orElseThrow(() -> new NotFoundEntityException(BY_LOGIN_MESSAGE));
    }

    @Override
    public Student findStudentById(Long studentId) {
        return personRepository.findStudentById(studentId)
                .orElseThrow(() -> new NotFoundEntityException(BY_ID_MESSAGE));
    }

    @Override
    public Teacher findTeacherById(Long teacherId) {
        return personRepository.findTeacherById(teacherId)
                .orElseThrow(() -> new NotFoundEntityException(BY_ID_MESSAGE));
    }

    @Override
    public List<Student> findAllStudents() {
        return personRepository.findAllStudents();
    }

    @Override
    public List<Teacher> findAllTeachers() {
        return personRepository.findAllTeachers();
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    @Transactional(isolation = REPEATABLE_READ)
    public Person update(Person person) {
        if (TRUE.equals(personRepository.ifExistsByLogin(person.getLogin()))) {
            throw new UpdateEntityException(ALREADY_EXISTING_WITH_LOGIN_MESSAGE);
        }
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }

    @Override
    public boolean delete(Long id) {
        if (FALSE.equals(personRepository.ifExistsById(id))) {
            throw new DeleteEntityException(NOT_EXISTING_WITH_THIS_ID);
        }
        personRepository.deleteById(id);
        return true;
    }
}
