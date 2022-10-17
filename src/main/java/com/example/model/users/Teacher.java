package com.example.model.users;

import com.example.model.salary.Salary;
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
        name = "teacher-with-salaries",
        attributeNodes = {@NamedAttributeNode(value = "salaries")}
)
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
}