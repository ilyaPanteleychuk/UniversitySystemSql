package com.ilyapanteleychuk.task7schoolsystem.service.dao;

import com.ilyapanteleychuk.task7schoolsystem.entity.Student;

import java.util.List;


public interface StudentService {

    void addNewStudent(Student student);

    List<Student> getAllStudentsByCourseName(String courseName);
    void deleteStudentById(int id);

    Student getStudentById(int id);


}
