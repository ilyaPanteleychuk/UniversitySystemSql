package com.ilyapanteleychuk.task7schoolsystem.service.dao;

import com.ilyapanteleychuk.task7schoolsystem.dao.CourseDao;
import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import java.util.List;
import java.util.stream.Collectors;


public class CourseServiceImpl implements CourseService{

    private final CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }

    @Override
    public Course getCourseByName(String name) {
        return courseDao.getCourseByName(name);
    }

    @Override
    public void addStudentToCourse(int studentId, int courseId) {
        courseDao.addStudentToCourse(studentId, courseId);
    }

    @Override
    public void removeStudentFromCourse(int studentId, String courseName) {
        Course course = courseDao.getCourseByName(courseName);
        courseDao.removeStudentFromCourse(studentId, course);
    }

    @Override
    public List<Course> getAllStudentCourses(int studentId) {
        List<Integer> integers = courseDao.getAllStudentCourses(studentId);
        return integers.stream().map(courseDao::getCourseById)
            .collect(Collectors.toList());
    }

    @Override
    public Course getCourseById(int courseId) {
        return courseDao.getCourseById(courseId);
    }
}
