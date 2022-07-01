package com.example.service.users.teacher;

import com.example.model.group.Group;
import com.example.model.users.Teacher;

import java.util.List;

public interface TeacherService {
    boolean addTeacherToGroup(Teacher teacher, Group group);

    Teacher findTeacherById(Long id);
    List<Teacher> findAllTeachers();

    boolean deleteTeacherFromGroup(Teacher teacher, Group group);
}
