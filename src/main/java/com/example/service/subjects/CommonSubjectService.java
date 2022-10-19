package com.example.service.subjects;

import com.example.exceptions.CreateEntityException;
import com.example.exceptions.DeleteEntityException;
import com.example.exceptions.NotFoundEntityException;
import com.example.exceptions.UpdateEntityException;
import com.example.model.subject.Subject;
import com.example.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.util.constant.Constants.ALREADY_EXISTING_WITH_NAME_MESSAGE;
import static com.example.util.constant.Constants.BY_ID_MESSAGE;
import static com.example.util.constant.Constants.BY_NAME_MESSAGE;
import static com.example.util.constant.Constants.NOT_EXISTING_WITH_THIS_ID;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.springframework.transaction.annotation.Isolation.REPEATABLE_READ;

@Service
@RequiredArgsConstructor
public class CommonSubjectService implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Override
    @Transactional
    public Subject save(Subject subject) {
        if (TRUE.equals(subjectRepository.ifExistsByName(subject.getName()))) {
            throw new CreateEntityException(ALREADY_EXISTING_WITH_NAME_MESSAGE);
        }
        return subjectRepository.save(subject);
    }

    @Override
    public Subject findById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException(BY_ID_MESSAGE));
    }

    @Override
    public Subject findByName(String name) {
        return subjectRepository.findByName(name)
                .orElseThrow(() -> new NotFoundEntityException(BY_NAME_MESSAGE));
    }

    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    @Transactional(isolation = REPEATABLE_READ)
    public Subject update(Subject subject) {
        if (TRUE.equals(subjectRepository.ifExistsByName(subject.getName()))) {
            throw new UpdateEntityException(ALREADY_EXISTING_WITH_NAME_MESSAGE);
        }
        return subjectRepository.save(subject);
    }

    @Override
    public boolean delete(Long id) {
        if (FALSE.equals(subjectRepository.ifExistsById(id))) {
            throw new DeleteEntityException(NOT_EXISTING_WITH_THIS_ID);
        }
        subjectRepository.deleteById(id);
        return true;
    }
}
