package com.ilyapanteleychuk.task7schoolsystem.service;

import com.ilyapanteleychuk.task7schoolsystem.entity.Student;

import java.util.List;


public interface UserService {

    void createTables(String changeLogPath);

    void insertStudents(List<Student> studentList);

}
