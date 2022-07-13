package com.ilyapanteleychuk.task7schoolsystem.service;

import com.ilyapanteleychuk.task7schoolsystem.dao.CommonDao;
import com.ilyapanteleychuk.task7schoolsystem.dao.CourseDao;
import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import java.util.List;
import java.util.stream.Collectors;


public class CourseService {

    private final CommonDao<Course> courseCommonDao;
    private final CourseDao courseDao;

    public CourseService(CommonDao<Course> courseCommonDao, CourseDao courseDao) {
        this.courseCommonDao = courseCommonDao;
        this.courseDao = courseDao;
    }

    public List<Course> getAllCourses() {
        return courseCommonDao.loadAll();
    }

    public void addStudentToCourse(int studentId, int courseId) {
        courseDao.addStudentToCourse(studentId, courseId);
    }

    public void removeStudentFromCourse(int studentId, int courseId) {
        courseDao.removeStudentFromCourse(studentId, courseId);
    }

    public List<Course> getAllStudentCourses(int studentId) {
        List<Integer> studentsId = courseDao.getAllStudentCourses(studentId);
        return studentsId.stream().map(courseCommonDao::loadById)
            .collect(Collectors.toList());
    }

    public Course getCourseByName(String courseName){
        return courseDao.getCourseByName(courseName);
    }
}
