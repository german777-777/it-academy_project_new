package com.example.model.users;

import com.example.model.mark.Mark;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
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

    public void addMark(Mark mark) {
        this.marks.add(mark);
    }

    public void removeMark(Mark mark) {
        this.marks.remove(mark);
    }

    @Override
    public String toString() {
        return super.toString() + ", marks: " + marks.toString() + "]";
    }
}