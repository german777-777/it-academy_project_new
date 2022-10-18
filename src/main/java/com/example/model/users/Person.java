package com.example.model.users;

import com.example.model.users.credentials.Credentials;
import com.example.model.users.roles.Role;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Getter
@Setter
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
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

    public String getLogin() {
        return this.credentials.getLogin();
    }

    public String getPassword() {
        return this.credentials.getPassword();
    }

    public void setPassword(String password) {
        this.credentials.setPassword(password);
    }

    @Override
    public String toString() {
        return "[ID: " + id + ", first name: " + firstName + ", last name: " + lastName + ", patronymic: " + patronymic + ", birthdate: " + birthDate + ", credentials: " + credentials + ", roles: " + roles.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Person person = (Person) o;
        return id != null && Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
