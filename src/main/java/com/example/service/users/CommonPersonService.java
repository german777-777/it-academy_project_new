package com.example.service.users;

import com.example.exceptions.CreateEntityException;
import com.example.exceptions.DeleteEntityException;
import com.example.exceptions.NotFoundEntityException;
import com.example.exceptions.UpdateEntityException;
import com.example.model.users.Person;
import com.example.repository.users.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.springframework.transaction.annotation.Isolation.REPEATABLE_READ;

@Service
@RequiredArgsConstructor
public class CommonPersonService implements PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Person save(Person person) {
        if (TRUE.equals(personRepository.ifExistsByLogin(person.getLogin()))) {
            throw new CreateEntityException(" cause already existing with this login");
        }
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException(" by id"));
    }

    @Override
    public Person findByLogin(String login) {
        return personRepository.findByLogin(login)
                .orElseThrow(() -> new NotFoundEntityException(" by login"));
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    @Transactional(isolation = REPEATABLE_READ)
    public Person update(Person person) {
        if (TRUE.equals(personRepository.ifExistsByLogin(person.getLogin()))) {
            throw new UpdateEntityException(" cause already existing with this login");
        }
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }

    @Override
    public boolean delete(Long id) {
        if (FALSE.equals(personRepository.ifExistsById(id))) {
            throw new DeleteEntityException(" cause not exists with this id");
        }
        personRepository.deleteById(id);
        return true;
    }
}
