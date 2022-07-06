package com.ilyapanteleychuk.task7schoolsystem.service.dao;

import com.ilyapanteleychuk.task7schoolsystem.dao.CourseDaoImpl;
import com.ilyapanteleychuk.task7schoolsystem.dao.StudentDaoImpl;
import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import java.util.List;
import java.util.stream.Collectors;


public class StudentService {

    private final StudentDaoImpl studentDao;
    private final CourseDaoImpl courseDao;

    public StudentService(StudentDaoImpl studentDao, CourseDaoImpl courseDao) {
        this.studentDao = studentDao;
        this.courseDao = courseDao;
    }

    public List<Student> getAllStudentsByCourseName(String courseName) {
        Course course = courseDao.getCourseByName(courseName);
        return studentDao.getAllStudentsByCourseName(course).stream()
            .map(studentDao::getStudentById)
            .collect(Collectors.toList());
    }

    public void deleteStudentById(int id) {
        studentDao.deleteStudentById(id);
    }

    public void addNewStudent(Student student){
        studentDao.addNewStudent(student);
    }
}
