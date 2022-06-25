package com.example.model.group;

import com.example.model.subject.Subject;
import com.example.model.users.Student;
import com.example.model.users.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.Table;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "groups")
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "group-with-teachers",
                attributeNodes = {@NamedAttributeNode(value = "teachers", subgraph = "teachers.salaries")},
                subgraphs = {@NamedSubgraph(name = "teachers.salaries", attributeNodes = @NamedAttributeNode(value = "salaries"))}
        ),
        @NamedEntityGraph(
                name = "group-with-teachers-students-subjects",
                attributeNodes = {
                        @NamedAttributeNode(value = "teachers", subgraph = "teachers.salaries"),
                        @NamedAttributeNode(value = "students", subgraph = "students.marks"),
                        @NamedAttributeNode(value = "subjects")
                },
                subgraphs = {
                        @NamedSubgraph(name = "teachers.salaries", attributeNodes = {@NamedAttributeNode(value = "salaries")}),
                        @NamedSubgraph(name = "students.marks", attributeNodes = {@NamedAttributeNode(value = "marks")})
                }
        )
})
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "groups_teachers",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "teacher_id")}
    )
    @Cascade({
            CascadeType.REFRESH,
            CascadeType.SAVE_UPDATE
    })
    private List<Teacher> teachers = new LinkedList<>();

    @ManyToMany
    @JoinTable(
            name = "groups_students",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")}
    )
    @Cascade({
            CascadeType.REFRESH,
            CascadeType.SAVE_UPDATE
    })
    private List<Student> students = new LinkedList<>();

    @ManyToMany
    @JoinTable(
            name = "groups_subjects",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "subject_id")}
    )
    @Cascade({
            CascadeType.REFRESH,
            CascadeType.SAVE_UPDATE
    })
    private List<Subject> subjects = new LinkedList<>();

    public void setName(String name) {
        this.name = name;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return id.equals(group.id) && name.equals(group.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "[ID: " + id + ", name: " + name + ", teachers: " + teachers.toString() + ", students: " + students.toString() + ", subjects: " + subjects.toString() + "]";
    }
}