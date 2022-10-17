package com.example.service.marks;

import com.example.exceptions.DeleteEntityException;
import com.example.exceptions.NotFoundEntityException;
import com.example.model.mark.Mark;
import com.example.model.users.Student;
import com.example.repository.MarkRepository;
import com.example.repository.users.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Boolean.FALSE;

@Service
@RequiredArgsConstructor
public class CommonMarkService implements MarkService {
    private final MarkRepository markRepository;
    private final PersonRepository personRepository;

    @Override
    public void save(Long studentId, Mark mark) {
        saveStudentWithNewMark(studentId, mark);
    }

    @Override
    public Mark findById(Long id) {
        return markRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException(" by id"));
    }

    @Override
    public List<Mark> findByStudentId(Long studentId) {
        Student student = (Student) personRepository.findById(studentId)
                .filter(person -> person.getClass().equals(Student.class))
                .orElseThrow(() -> new NotFoundEntityException(" by id"));
        return student.getMarks();
    }

    @Override
    public List<Mark> findAll() {
        return markRepository.findAll();
    }

    @Override
    public void update(Long studentId, Mark mark) {
        saveStudentWithNewMark(studentId, mark);
    }

    @Override
    public boolean delete(Long id) {
        if (FALSE.equals(markRepository.ifExistsById(id))) {
            throw new DeleteEntityException(" cause not exists with this id");
        }
        markRepository.deleteById(id);
        return true;
    }

    private void saveStudentWithNewMark(Long studentId, Mark mark) {
        Student student = (Student) (personRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundEntityException(" by id")));

        student.addMark(mark);

        personRepository.save(student);
    }
}
