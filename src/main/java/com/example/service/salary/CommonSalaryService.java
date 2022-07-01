package com.example.service.salary;

import com.example.exceptions.CreateEntityException;
import com.example.exceptions.DeleteEntityException;
import com.example.exceptions.NotFoundEntityException;
import com.example.exceptions.UpdateEntityException;
import com.example.model.salary.Salary;
import com.example.model.users.Teacher;
import com.example.repository.SalaryRepository;
import com.example.repository.users.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommonSalaryService implements SalaryService {

    private final SalaryRepository salaryRepository;
    private final PersonRepository personRepository;

    @Override
    public boolean save(Salary salary, Teacher teacher) {
        if (teacher == null) {
            throw new CreateEntityException("Не найден учитель, которому будет добавлена зарплата!");
        }

        if (salary.getDateOfIssue() == null || salary.getCount() <= 0) {
            throw new CreateEntityException("Некорректные данные!");
        }

        teacher.addSalary(salary);

        personRepository.save(teacher);

        return teacher.getId() != 0;
    }

    @Override
    public Salary findById(Long id) {
        return salaryRepository.findById(id).orElseThrow(() -> new NotFoundEntityException("Зарплата не найдена по ID!"));
    }

    @Override
    public List<Salary> findSalariesByTeacherId(Long teacherId) {
        return personRepository.findTeacherById(teacherId).orElseThrow(() -> new NotFoundEntityException("Учитель не найден по ID: зарплаты не могут быть получены!")).getSalaries();
    }

    @Override
    public boolean update(Salary oldSalary, Salary newSalary, Teacher teacher) {
        if (oldSalary == null || teacher == null) {
            throw new UpdateEntityException("Некорректные данные: старая зарплата или учитель не найдены!");
        }

        setNewSalaryParameters(oldSalary, newSalary);

        teacher.getSalaries().removeIf(salary -> salary.getId().equals(oldSalary.getId()));
        teacher.addSalary(oldSalary);

        personRepository.save(teacher);

        return teacher.getId() != 0;
    }

    private void setNewSalaryParameters(Salary oldSalary, Salary newSalary) {
        int newCount = newSalary.getCount();
        LocalDate newDateOfIssue = newSalary.getDateOfIssue();

        if (!(newCount <= 0)) {
            oldSalary.setCount(newCount);
        }

        if (newDateOfIssue != null) {
            oldSalary.setDateOfIssue(newDateOfIssue);
        }
    }

    @Override
    public boolean delete(Long id) {
        salaryRepository.findById(id).orElseThrow(() -> new DeleteEntityException("Зарплата не найдена по ID: удаление невозможно!"));
        salaryRepository.deleteById(id);
        return true;
    }
}
