package com.ilyapanteleychuk.task7schoolsystem.service.dao;

import com.ilyapanteleychuk.task7schoolsystem.dao.CourseDao;
import com.ilyapanteleychuk.task7schoolsystem.dao.StudentDao;
import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import java.util.List;
import java.util.stream.Collectors;


public class StudentServiceImpl implements StudentService{

    private final StudentDao studentDao;
    private final CourseDao courseDao;

    public StudentServiceImpl(StudentDao studentDao, CourseDao courseDao) {
        this.studentDao = studentDao;
        this.courseDao = courseDao;
    }

    @Override
    public void addNewStudent(Student student) {
        studentDao.addNewStudent(student);
    }

    @Override
    public List<Student> getAllStudentsByCourseName(String courseName) {
        Course course = courseDao.getCourseByName(courseName);
        return studentDao.getAllStudentsByCourseName(course).stream()
            .map(studentDao::getStudentById)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteStudentById(int id) {
        studentDao.deleteStudentById(id);
    }

    @Override
    public Student getStudentById(int id) {
        return studentDao.getStudentById(id);
    }
}
