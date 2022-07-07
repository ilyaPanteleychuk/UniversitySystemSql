package com.ilyapanteleychuk.task7schoolsystem.dao;

import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import java.util.List;


public interface StudentDao {

    List<Integer> getAllStudentsByCourseName(Course course);

}
