package com.example.model.users;

import com.example.model.mark.Mark;
import lombok.Getter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;

@Getter
@Entity
@NamedEntityGraph(
        name = "student-with-marks",
        attributeNodes = {@NamedAttributeNode(value = "marks")}
)
public class Student extends Person {
    @OneToMany
    @JoinColumn(name = "student_id")
    @Cascade({
            CascadeType.DELETE,
            CascadeType.REFRESH,
            CascadeType.SAVE_UPDATE
    })
    private List<Mark> marks = new LinkedList<>();

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString() + ", marks: " + marks.toString() + "]";
    }
}