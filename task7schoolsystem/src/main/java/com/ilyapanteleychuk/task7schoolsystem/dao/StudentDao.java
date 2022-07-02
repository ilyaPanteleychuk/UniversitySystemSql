package com.ilyapanteleychuk.task7schoolsystem.dao;

import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import java.util.List;


public interface StudentDao {

    List<Integer> getAllStudentsByCourseName(Course course);

    void addNewStudent(Student student);

    void deleteStudentById(int id);

    Student getStudentById(int id);


}
