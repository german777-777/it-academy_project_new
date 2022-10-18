package com.example.model.salary;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@Entity
@Setter
@Table(name = "salaries")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfIssue;

    private int count;

    public Long getId() {
        return id;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public int getCount() {
        return count;
    }

    public Salary(Long id, LocalDate dateOfIssue, int count) {
        this.id = id;
        this.dateOfIssue = dateOfIssue;
        this.count = count;
    }

    @Override
    public String toString() {
        return "[ID: " + id + ", date of issue: " + dateOfIssue + ", count: " + count + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Salary salary = (Salary) o;
        return id != null && Objects.equals(id, salary.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}