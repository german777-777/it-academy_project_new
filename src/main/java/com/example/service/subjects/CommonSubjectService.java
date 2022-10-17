package com.example.service.subjects;

import com.example.exceptions.CreateEntityException;
import com.example.exceptions.DeleteEntityException;
import com.example.exceptions.NotFoundEntityException;
import com.example.exceptions.UpdateEntityException;
import com.example.model.subject.Subject;
import com.example.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
            throw new CreateEntityException(" cause already existing with this name");
        }
        return subjectRepository.save(subject);
    }

    @Override
    public Subject findById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException(" by id"));
    }

    @Override
    public Subject findByName(String name) {
        return subjectRepository.findByName(name)
                .orElseThrow(() -> new NotFoundEntityException(" by name"));
    }

    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    @Transactional(isolation = REPEATABLE_READ)
    public Subject update(Subject subject) {
        if (TRUE.equals(subjectRepository.ifExistsByName(subject.getName()))) {
            throw new UpdateEntityException(" cause already existing with this name");
        }
        return subjectRepository.save(subject);
    }

    @Override
    public boolean delete(Long id) {
        if (FALSE.equals(subjectRepository.ifExistsById(id))) {
            throw new DeleteEntityException(" cause not exists with this id");
        }
        subjectRepository.deleteById(id);
        return true;
    }
}
