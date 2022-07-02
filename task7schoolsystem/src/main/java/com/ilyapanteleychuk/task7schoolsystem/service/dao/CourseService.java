package com.ilyapanteleychuk.task7schoolsystem.service.dao;

import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import java.util.List;


public interface CourseService {

    List<Course> getAllCourses();

    Course getCourseByName(String name);

    void addStudentToCourse(int studentId, int courseId);

    void removeStudentFromCourse(int studentId, String courseName);

    List<Course> getAllStudentCourses(int studentId);

    Course getCourseById(int courseId);

}
