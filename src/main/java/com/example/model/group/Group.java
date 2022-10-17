package com.example.model.group;

import com.example.model.subject.Subject;
import com.example.model.users.Student;
import com.example.model.users.Teacher;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "groups")
@NamedEntityGraphs(
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
)
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
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

    public void addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }

    public void removeTeacher(Teacher teacher) {
        this.teachers.remove(teacher);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    public void removeSubject(Subject subject) {
        this.subjects.remove(subject);
    }

    @Override
    public String toString() {
        return "[ID: " + id + ", name: " + name + ", teachers: " + teachers.toString() + ", students: " + students.toString() + ", subjects: " + subjects.toString() + "]";
    }
}