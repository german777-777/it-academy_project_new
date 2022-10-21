package com.example.service.marks;

import com.example.exceptions.DeleteEntityException;
import com.example.exceptions.NotFoundEntityException;
import com.example.model.mark.Mark;
import com.example.model.users.Student;
import com.example.repository.MarkRepository;
import com.example.service.users.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.util.constant.Constants.BY_ID_MESSAGE;
import static com.example.util.constant.Constants.NOT_EXISTING_WITH_THIS_ID_MESSAGE;
import static java.lang.Boolean.FALSE;
import static org.springframework.transaction.annotation.Isolation.REPEATABLE_READ;

@Service
@RequiredArgsConstructor
public class CommonMarkService implements MarkService {
    private final MarkRepository markRepository;
    private final PersonService personService;

    @Override
    @Transactional
    public void save(Long studentId, Mark mark) {
        saveStudentWithNewMark(studentId, mark);
    }

    @Override
    public Mark findById(Long id) {
        return markRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException(BY_ID_MESSAGE));
    }

    @Override
    public List<Mark> findByStudentId(Long studentId) {
        Student student = personService.findStudentById(studentId);
        return student.getMarks();
    }

    @Override
    public List<Mark> findAll() {
        return markRepository.findAll();
    }

    @Override
    @Transactional(isolation = REPEATABLE_READ)
    public void update(Long studentId, Mark mark) {
        saveStudentWithNewMark(studentId, mark);
    }

    @Override
    public boolean delete(Long id) {
        if (FALSE.equals(markRepository.ifExistsById(id))) {
            throw new DeleteEntityException(NOT_EXISTING_WITH_THIS_ID_MESSAGE);
        }
        markRepository.deleteById(id);
        return true;
    }

    private void saveStudentWithNewMark(Long studentId, Mark mark) {
        Student student = personService.findStudentById(studentId);

        student.addMark(mark);

        personService.save(student);
    }
}
