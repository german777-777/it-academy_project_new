package com.example.service.marks;

import com.example.model.mark.Mark;

import java.util.List;

public interface MarkService {
    void save(Long studentId, Mark mark);
    Mark findById(Long id);
    List<Mark> findByStudentId(Long studentId);
    List<Mark> findAll();
    void update(Long studentId, Mark mark);
    boolean delete(Long id);
}
