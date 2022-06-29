package com.example.service.users;

import com.example.exceptions.CreateEntityException;
import com.example.exceptions.DeleteEntityException;
import com.example.exceptions.NotFoundEntityException;
import com.example.exceptions.UpdateEntityException;
import com.example.model.users.Admin;
import com.example.model.users.Person;
import com.example.model.users.Student;
import com.example.model.users.Teacher;
import com.example.model.users.roles.Role;
import com.example.repository.users.PersonRepository;
import com.example.repository.users.roles.RoleRepository;
import com.example.security.user.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommonPersonService implements PersonService<Person> {

    private final PersonRepository<Person> personRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean save(Person person) {
        if (person.getFirstName() == null || person.getLastName() == null || person.getPatronymic() == null || person.getCredentials() == null || person.getBirthDate() == null) {
            throw new CreateEntityException("Некорректные данные пользователя!");
        }

        String newPersonLogin = person.getCredentials().getLogin();
        String newPersonPassword = passwordEncoder.encode(person.getCredentials().getPassword());

        if (personRepository.ifExistsByLoginOrPassword(newPersonLogin, newPersonPassword)) {
            throw new CreateEntityException("Пользователь с введёнными логином и паролем уже существует!");
        }

        person.getCredentials().setPassword(newPersonPassword);

        return setRolesToPersonAndSave(person);
    }

    private boolean setRolesToPersonAndSave(Person person) {
        boolean ifPersonSaved = false;

        Optional<Role> adminRole = roleRepository.findByName("ROLE_ADMIN");
        Optional<Role> teacherRole = roleRepository.findByName("ROLE_TEACHER");
        Optional<Role> studentRole = roleRepository.findByName("ROLE_STUDENT");

        if (person instanceof Admin) {
            person.setRoles(List.of(
                    adminRole.orElseThrow(() -> new NotFoundEntityException("Роль администратора не найдена: создание администратора невозможно!")),
                    teacherRole.orElseThrow(() -> new NotFoundEntityException("Роль учителя не найдена: создание администратора невозможно!")),
                    studentRole.orElseThrow(() -> new NotFoundEntityException("Роль студента не найдена: создание администратора невозможно!"))));
            Admin admin = (Admin) person;
            personRepository.save(admin);
            ifPersonSaved = admin.getId() != 0;
        } else if (person instanceof Teacher) {
            person.setRoles(List.of(
                    teacherRole.orElseThrow(() -> new NotFoundEntityException("Роль учителя не найдена: создание учителя невозможно!")),
                    studentRole.orElseThrow(() -> new NotFoundEntityException("Роль студента не найдена: создание учителя невозможно!"))));
            Teacher teacher = (Teacher) person;
            personRepository.save(teacher);
            ifPersonSaved = teacher.getId() != 0;
        } else if (person instanceof Student) {
            person.setRoles(List.of(
                    studentRole.orElseThrow(() -> new NotFoundEntityException("Роль студента не найдена: создание студента невозможно!"))));
            Student student = (Student) person;
            personRepository.save(student);
            ifPersonSaved = student.getId() != 0;
        }

        return ifPersonSaved;
    }

    @Override
    public Student findStudentById(Long id) {
        return personRepository.findStudentById(id).orElseThrow(() -> new NotFoundEntityException("Студент не найден по ID!"));
    }

    @Override
    public Teacher findTeacherById(Long id) {
        return personRepository.findTeacherById(id).orElseThrow(() -> new NotFoundEntityException("Учитель не найден по ID!"));
    }

    @Override
    public Person findByLoginAndPassword(String login, String password) {
        return personRepository.findByLoginAndPassword(login, passwordEncoder.encode(password)).orElseThrow(() -> new NotFoundEntityException("Пользователь не найден по логину и паролю!"));

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
    public boolean update(Person oldPerson, Person newPerson) {
        if (oldPerson == null) {
            throw new UpdateEntityException("Некорректные данные: старый пользователь не найден!");
        }

        String newAdminLogin = newPerson.getCredentials().getLogin();
        String newAdminPassword = newPerson.getCredentials().getPassword();

        if (newAdminLogin != null && newAdminPassword != null) {
            if (personRepository.ifExistsByLoginOrPassword(newAdminLogin, passwordEncoder.encode(newAdminPassword))) {
                throw new UpdateEntityException("Пользователь с введёнными логином и паролем уже существует!");
            }
        }

        setNewPersonParameters(oldPerson, newPerson);

        personRepository.save(oldPerson);

        return oldPerson.getId() != 0;
    }

    private void setNewPersonParameters(Person oldPerson, Person newPerson) {
        String newFirstName = newPerson.getFirstName();
        String newLastName = newPerson.getLastName();
        String newPatronymic = newPerson.getPatronymic();
        LocalDate newBirthDate = newPerson.getBirthDate();
        String newLogin = newPerson.getCredentials().getLogin();
        String newPassword = newPerson.getCredentials().getPassword();

        if (newFirstName != null) {
            oldPerson.setFirstName(newFirstName);
        }

        if (newLastName != null) {
            oldPerson.setLastName(newLastName);
        }

        if (newPatronymic != null) {
            oldPerson.setPatronymic(newPatronymic);
        }

        if (newBirthDate != null) {
            oldPerson.setBirthDate(newBirthDate);
        }

        if (newLogin != null) {
            oldPerson.getCredentials().setLogin(newLogin);
        }

        if (newPassword != null) {
            oldPerson.getCredentials().setPassword(passwordEncoder.encode(newPassword));
        }
    }

    @Override
    public boolean delete(Long id) {
        personRepository.findById(id).orElseThrow(() -> new DeleteEntityException("Пользователь не найден по ID: удаление невозможно!"));
        personRepository.deleteById(id);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Person> personOptional = personRepository.findByLogin(login);
        if (personOptional.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь не найден по логину!");
        }
        Person person = personOptional.get();
        return UserSecurity.convertPersonToUserDetails(person.getCredentials().getLogin(), person.getCredentials().getPassword(), person.getRoles());
    }
}
