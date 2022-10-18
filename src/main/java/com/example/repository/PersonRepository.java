package com.example.repository;

import com.example.model.users.Person;
import com.example.model.users.Student;
import com.example.model.users.Teacher;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    /**
     *
     * @param id  user identifier
     * @return {@link java.lang.Boolean} type, which indicates that user exists or not
     */
    @Query("SELECT CASE WHEN count(p) > 0 THEN TRUE ELSE FALSE END FROM Person p WHERE p.id = ?1")
    Boolean ifExistsById(Long id);

    /**
     *
     * @param login  user login
     * @return {@link java.lang.Boolean} type, which indicates that user exists or not
     */
    @Query("SELECT CASE WHEN count(p) > 0 THEN TRUE ELSE FALSE END FROM Person p WHERE p.credentials.login = ?1")
    Boolean ifExistsByLogin(String login);

    /**
     *
     * @param login user login
     * @return {@link java.util.Optional<com.example.model.users.Person>} -- person-wrapper, for safe checking person on higher level
     */
    @Query("SELECT p FROM Person p WHERE p.credentials.login = ?1")
    Optional<Person> findByLogin(String login);

    /**
     * Method for getting {@link com.example.model.users.Student} with his subtypes ({@link java.util.List} of marks)
     * @param studentId - student identifier
     * @return {@link java.util.Optional<com.example.model.users.Student>} -- student-wrapper, for safe checking on higher level
     */
    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, value = "student-with-marks")
    @Query("SELECT s FROM Student s WHERE s.id = ?1")
    Optional<Student> findStudentById(Long studentId);

    /**
     * Method for finding all students with his subtypes ({@link java.util.List} of marks)
     * @return {@link java.util.List} of students
     */
    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, value = "student-with-marks")
    @Query("SELECT s FROM Student s")
    List<Student> findAllStudents();

    /**
     * Method for getting {@link com.example.model.users.Teacher} with his subtypes ({@link java.util.List} of salaries)
     * @param teacherId - teacher identifier
     * @return {@link java.util.Optional<com.example.model.users.Teacher>} -- teacher-wrapper, for safe checking on higher level
     */
    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, value = "teacher-with-salaries")
    @Query("SELECT t FROM Teacher t WHERE t.id = ?1")
    Optional<Teacher> findTeacherById(Long teacherId);

    /**
     * Method for finding all students with his subtypes ({@link java.util.List} of salaries)
     * @return {@link java.util.List} of teachers
     */
    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, value = "teacher-with-salaries")
    @Query("SELECT t FROM Teacher t")
    List<Teacher> findAllTeachers();
}
