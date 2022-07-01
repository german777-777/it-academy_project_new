package com.example.service.marks;

import com.example.model.mark.Mark;
import com.example.model.users.Student;

import java.util.List;

public interface MarkService {
    boolean save(Mark mark, Student student);

    Mark findById(Long id);
    List<Mark> findMarksByStudentId(Long studentId);

    boolean update(Mark oldMark, Mark newMark, Student student);
    boolean delete(Long id);
}
