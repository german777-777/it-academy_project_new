package com.example.service.marks;

import com.example.exceptions.CreateEntityException;
import com.example.exceptions.DeleteEntityException;
import com.example.exceptions.NotFoundEntityException;
import com.example.exceptions.UpdateEntityException;
import com.example.model.mark.Mark;
import com.example.model.subject.Subject;
import com.example.model.users.Student;
import com.example.repository.MarkRepository;
import com.example.repository.users.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommonMarkService implements MarkService {

    private final MarkRepository markRepository;
    private final PersonRepository personRepository;

    @Override
    public boolean save(Mark mark, Student student) {
        if (student == null) {
            throw new CreateEntityException("Не найден студент, которому будет добавлена оценка!");
        }

        if (mark.getDateOfIssue() == null || mark.getSubject() == null || (mark.getCount() < 0 && mark.getCount() > 10)) {
            throw new CreateEntityException("Некорректные данные!");
        }

        student.addMark(mark);

        personRepository.save(student);

        return student.getId() != 0;
    }

    @Override
    public Mark findById(Long id) {
        return markRepository.findById(id).orElseThrow(() -> new NotFoundEntityException("Оценка не найдена по ID!"));
    }

    @Override
    public List<Mark> findMarksByStudentId(Long studentId) {
        return personRepository.findStudentById(studentId).orElseThrow(() -> new NotFoundEntityException("Студент не найден по ID: оценки не могут быть получены!")).getMarks();
    }

    @Override
    public boolean update(Mark oldMark, Mark newMark, Student student) {
        if (oldMark == null || student == null) {
            throw new UpdateEntityException("Некорректные данные: старая оценка или студент не найдены!");
        }

        setNewMarkParameters(oldMark, newMark);

        student.getMarks().removeIf(mark -> mark.getId().equals(oldMark.getId()));
        student.addMark(oldMark);

        personRepository.save(student);

        return student.getId() != 0;
    }

    private void setNewMarkParameters(Mark oldMark, Mark newMark) {
        int newCount = newMark.getCount();
        LocalDate newDateOfIssue = newMark.getDateOfIssue();
        Subject newSubject = newMark.getSubject();

        if (!(newCount < 0 || newCount > 10)) {
            oldMark.setCount(newCount);
        }

        if (newDateOfIssue != null) {
            oldMark.setDateOfIssue(newDateOfIssue);
        }

        if (newSubject != null) {
            oldMark.setSubject(newSubject);
        }
    }

    @Override
    public boolean delete(Long id) {
        markRepository.findById(id).orElseThrow(() -> new DeleteEntityException("Оценка не найдена по ID: удаление невозможно!"));
        markRepository.deleteById(id);
        return true;
    }
}
