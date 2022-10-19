package com.example.model.users;

import com.example.model.salary.Salary;
import lombok.Getter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.DiscriminatorValue;
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
        name = "teacher-with-salaries",
        attributeNodes = {@NamedAttributeNode(value = "salaries")}
)
@DiscriminatorValue("3")
public class Teacher extends Person {
    @OneToMany
    @JoinColumn(name = "teacher_id")
    @Cascade({
            CascadeType.DELETE,
            CascadeType.REFRESH,
            CascadeType.SAVE_UPDATE
    })
    private List<Salary> salaries = new LinkedList<>();

    public void addSalary(Salary salary) {
        this.salaries.add(salary);
    }

    public void removeSalary(Salary salary) {
        this.salaries.remove(salary);
    }

    @Override
    public String toString() {
        return super.toString() + ", salaries: " + salaries.toString() + "]";
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}