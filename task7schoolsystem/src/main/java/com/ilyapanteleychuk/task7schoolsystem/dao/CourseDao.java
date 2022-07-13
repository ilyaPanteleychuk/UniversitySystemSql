package com.ilyapanteleychuk.task7schoolsystem.dao;

import com.ilyapanteleychuk.task7schoolsystem.entity.Course;

import java.util.List;


public interface CourseDao {

    Course getCourseByName(String name);

    void addStudentToCourse(int studentId, int courseId);

    void removeStudentFromCourse(int studentId, int courseId);

    List<Integer> getAllStudentCourses(int studentId);
}
