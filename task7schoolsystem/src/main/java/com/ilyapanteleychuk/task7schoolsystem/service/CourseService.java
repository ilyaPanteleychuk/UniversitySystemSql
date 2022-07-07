package com.ilyapanteleychuk.task7schoolsystem.service;

import com.ilyapanteleychuk.task7schoolsystem.dao.CourseDao;
import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import java.util.List;
import java.util.stream.Collectors;


public class CourseService {

    private final CourseDao courseDao;

    public CourseService(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }

    public void addStudentToCourse(int studentId, int courseId) {
        courseDao.addStudentToCourse(studentId, courseId);
    }

    public void removeStudentFromCourse(int studentId, int courseId) {
        courseDao.removeStudentFromCourse(studentId, courseId);
    }

    public List<Course> getAllStudentCourses(int studentId) {
        List<Integer> studentsId = courseDao.getAllStudentCourses(studentId);
        return studentsId.stream().map(courseDao::getCourseById)
            .collect(Collectors.toList());
    }
}
