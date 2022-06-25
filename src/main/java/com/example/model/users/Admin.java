package com.example.model.users;

import com.example.model.users.credentials.Credentials;
import com.example.model.users.roles.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Table(name = "users")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate birthDate;

    @OneToOne(optional = false)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "credential_id")
    private Credentials credentials;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    @Cascade({
            CascadeType.REFRESH,
            CascadeType.SAVE_UPDATE
    })
    private List<Role> roles = new ArrayList<>();

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return id.equals(admin.id) && firstName.equals(admin.firstName) && lastName.equals(admin.lastName) && patronymic.equals(admin.patronymic) && birthDate.equals(admin.birthDate) && credentials.equals(admin.credentials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, patronymic, birthDate, credentials);
    }

    @Override
    public String toString() {
        return "[ID: " + id + ", first name: " + firstName + ", last name: " + lastName + ", patronymic: " + patronymic + ", birthdate: " + birthDate + ", credentials: " + credentials + ", roles: " + roles.toString() + "]";
    }
}