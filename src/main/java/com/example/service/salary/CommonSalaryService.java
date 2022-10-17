package com.example.service.salary;

import com.example.exceptions.DeleteEntityException;
import com.example.exceptions.NotFoundEntityException;
import com.example.model.salary.Salary;
import com.example.model.users.Teacher;
import com.example.repository.SalaryRepository;
import com.example.repository.users.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.Boolean.FALSE;
import static org.springframework.transaction.annotation.Isolation.REPEATABLE_READ;

@Service
@RequiredArgsConstructor
public class CommonSalaryService implements SalaryService {

    private final SalaryRepository salaryRepository;
    private final PersonRepository personRepository;

    @Override
    @Transactional
    public void save(Long teacherId, Salary salary) {
        saveTeacherWithNewSalary(teacherId, salary);
    }

    @Override
    public Salary findById(Long id) {
        return salaryRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException(" by id"));
    }

    @Override
    public List<Salary> findAll() {
        return salaryRepository.findAll();
    }

    @Override
    public List<Salary> findByTeacherId(Long teacherId) {
        Teacher teacher = (Teacher) personRepository.findById(teacherId)
                .filter(person -> person.getClass().equals(Teacher.class))
                .orElseThrow(() -> new NotFoundEntityException(" by id"));
        return teacher.getSalaries();
    }

    @Override
    @Transactional(isolation = REPEATABLE_READ)
    public void update(Long teacherId, Salary salary) {
        saveTeacherWithNewSalary(teacherId, salary);
    }

    @Override
    public boolean delete(Long id) {
        if (FALSE.equals(salaryRepository.ifExistsById(id))) {
            throw new DeleteEntityException(" cause not exists with this id");
        }
        salaryRepository.deleteById(id);
        return true;
    }

    private void saveTeacherWithNewSalary(Long teacherId, Salary salary) {
        Teacher teacher = (Teacher) (personRepository.findById(teacherId)
                .orElseThrow(() -> new NotFoundEntityException(" by id")));

        teacher.addSalary(salary);

        personRepository.save(teacher);
    }
}
