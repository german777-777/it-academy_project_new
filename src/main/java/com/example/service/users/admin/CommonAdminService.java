package com.example.service.users.admin;

import com.example.exceptions.CreateEntityException;
import com.example.exceptions.DeleteEntityException;
import com.example.exceptions.NotFoundEntityException;
import com.example.exceptions.UpdateEntityException;
import com.example.model.users.Admin;
import com.example.model.users.roles.Role;
import com.example.repository.users.AdminRepository;
import com.example.repository.users.roles.RoleRepository;
import com.example.security.user.UserSecurity;
import com.example.service.users.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommonAdminService implements PersonService<Admin> {

    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean save(Admin admin) {
        if (admin.getFirstName() == null || admin.getLastName() == null || admin.getPatronymic() == null || admin.getCredentials() == null || admin.getBirthDate() == null) {
            throw new CreateEntityException("Некорректные данные администратора!");
        }

        String newAdminLogin = admin.getCredentials().getLogin();
        String newAdminPassword = passwordEncoder.encode(admin.getCredentials().getPassword());

        if (adminRepository.ifExistsByLoginOrPassword(newAdminLogin, newAdminPassword)) {
            throw new CreateEntityException("Администратор с введёнными логином и паролем уже существует!");
        }

        admin.getCredentials().setPassword(newAdminPassword);

        Optional<Role> adminRole = roleRepository.findByName("ROLE_ADMIN");
        Optional<Role> teacherRole = roleRepository.findByName("ROLE_TEACHER");
        Optional<Role> studentRole = roleRepository.findByName("ROLE_STUDENT");
        admin.setRoles(List.of(
                adminRole.orElseThrow(() -> new NotFoundEntityException("Роль администратора не найдена: создание администратора невозможно!")),
                teacherRole.orElseThrow(() -> new NotFoundEntityException("Роль учителя не найдена: создание администратора невозможно!")),
                studentRole.orElseThrow(() -> new NotFoundEntityException("Роль студента не найдена: создание администратора невозможно!"))
        ));

        adminRepository.save(admin);

        return admin.getId() != 0;
    }

    @Override
    @Transactional(readOnly = true)
    public Admin findById(Long id) {
        return adminRepository.findById(id).orElseThrow(() -> new NotFoundEntityException("Администратор не найден по ID!"));
    }

    @Override
    public Admin findByLoginAndPassword(String login, String password) {
        return adminRepository.findByLoginAndPassword(login, passwordEncoder.encode(password)).orElseThrow(() -> new NotFoundEntityException("Администратор не найден по логину и паролю!"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @Override
    public boolean update(Admin oldAdmin, Admin newAdmin) {
        if (oldAdmin == null) {
            throw new UpdateEntityException("Некорректные данные: старый администратор не найден!");
        }

        String newAdminLogin = newAdmin.getCredentials().getLogin();
        String newAdminPassword = newAdmin.getCredentials().getPassword();

        if (newAdminLogin != null && newAdminPassword != null) {
            if (adminRepository.ifExistsByLoginOrPassword(newAdminLogin, passwordEncoder.encode(newAdminPassword))) {
                throw new UpdateEntityException("Администратор с введёнными логином и паролем уже существует!");
            }
        }

        setNewAdminParameters(oldAdmin, newAdmin);

        adminRepository.save(oldAdmin);

        return oldAdmin.getId() != 0;
    }

    private void setNewAdminParameters(Admin oldAdmin, Admin newAdmin) {
        String newFirstName = newAdmin.getFirstName();
        String newLastName = newAdmin.getLastName();
        String newPatronymic = newAdmin.getPatronymic();
        LocalDate newBirthDate = newAdmin.getBirthDate();
        String newLogin = newAdmin.getCredentials().getLogin();
        String newPassword = newAdmin.getCredentials().getPassword();

        if (newFirstName != null) {
            oldAdmin.setFirstName(newFirstName);
        }

        if (newLastName != null) {
            oldAdmin.setLastName(newLastName);
        }

        if (newPatronymic != null) {
            oldAdmin.setPatronymic(newPatronymic);
        }

        if (newBirthDate != null) {
            oldAdmin.setBirthDate(newBirthDate);
        }

        if (newLogin != null) {
            oldAdmin.getCredentials().setLogin(newLogin);
        }

        if (newPassword != null) {
            oldAdmin.getCredentials().setPassword(passwordEncoder.encode(newPassword));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Admin> adminOptional = adminRepository.findByLogin(login);
        if (adminOptional.isEmpty()) {
            throw new UsernameNotFoundException("Администратор не найден по логину!");
        }
        Admin admin = adminOptional.get();
        return UserSecurity.convertPersonToUserDetails(admin.getCredentials().getLogin(), admin.getCredentials().getPassword(), admin.getRoles());
    }

    @Override
    public boolean delete(Long id) {
        adminRepository.findById(id).orElseThrow(() -> new DeleteEntityException("Администратор не найден по ID: удаление невозможно!"));
        adminRepository.deleteById(id);
        return true;
    }
}
