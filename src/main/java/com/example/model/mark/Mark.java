package com.example.model.mark;

import com.example.model.subject.Subject;
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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@Setter
@Entity
@Table(name = "marks")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfIssue;

    private int count;

    public Mark(Long id, Subject subject, LocalDate dateOfIssue, int count) {
        this.id = id;
        this.subject = subject;
        this.dateOfIssue = dateOfIssue;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public Subject getSubject() {
        return subject;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "[ID: " + id + ", subjects: " + subject + ", date of issue: " + dateOfIssue + ", count: " + count + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Mark mark = (Mark) o;
        return id != null && Objects.equals(id, mark.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}