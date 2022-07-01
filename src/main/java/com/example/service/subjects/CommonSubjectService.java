package com.example.service.subjects;

import com.example.exceptions.CreateEntityException;
import com.example.exceptions.DeleteEntityException;
import com.example.exceptions.NotFoundEntityException;
import com.example.exceptions.UpdateEntityException;
import com.example.model.subject.Subject;
import com.example.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommonSubjectService implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Override
    public boolean save(Subject subject) {
        if (subject.getName() == null) {
            throw new CreateEntityException("Некорректные данные предмета!");
        }

        if (subjectRepository.ifExistsByName(subject.getName())) {
            throw new CreateEntityException("Предмет с введённым названием уже существует!");
        }

        subjectRepository.save(subject);

        return subject.getId() != 0;
    }

    @Override
    public Subject findById(Long id) {
        return subjectRepository.findById(id).orElseThrow(() -> new NotFoundEntityException("Предмет не найден по ID!"));
    }

    @Override
    public Subject findByName(String name) {
        return subjectRepository.findByName(name).orElseThrow(() -> new NotFoundEntityException("Предмет не найден по названию!"));
    }

    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public boolean update(Subject oldSubject, Subject newSubject) {
        if (oldSubject == null) {
            throw new UpdateEntityException("Некорректные данные: старый предмет не найден!");
        }

        String newSubjectName = newSubject.getName();

        if (subjectRepository.ifExistsByName(newSubjectName)) {
            throw new UpdateEntityException("Предмет с введённым названием уже существует!");
        }

        if (newSubjectName != null) {
            oldSubject.setName(newSubjectName);
        }

        subjectRepository.save(oldSubject);

        return oldSubject.getId() != 0;
    }

    @Override
    public boolean delete(Long id) {
        subjectRepository.findById(id).orElseThrow(() -> new DeleteEntityException("Предмет не найден по ID: удаление невозможно!"));
        subjectRepository.deleteById(id);
        return true;
    }
}
