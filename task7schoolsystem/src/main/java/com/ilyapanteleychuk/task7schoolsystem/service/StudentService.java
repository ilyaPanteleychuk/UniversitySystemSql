package com.ilyapanteleychuk.task7schoolsystem.service;

import com.ilyapanteleychuk.task7schoolsystem.dao.CourseDao;
import com.ilyapanteleychuk.task7schoolsystem.dao.StudentDao;
import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import java.util.List;
import java.util.stream.Collectors;


public class StudentService {

    private final StudentDao studentDao;
    private final CourseDao courseDao;
    private final GroupService groupService;

    public StudentService(StudentDao studentDao, CourseDao courseDao,
                          GroupService groupService) {
        this.studentDao = studentDao;
        this.courseDao = courseDao;
        this.groupService = groupService;
    }

    public Student getStudentById(int studentId){
        int groupId = groupService.getGroupOfStudent(studentId);
        Student student = studentDao.getStudentById(studentId);
        student.setGroup(groupService.getGroupById(groupId));
        return student;
    }

    public List<Student> getAllStudentsByCourseName(String courseName) {
        Course course = courseDao.getCourseByName(courseName);
        return studentDao.getAllStudentsByCourseName(course).stream()
            .map(this::getStudentById)
            .collect(Collectors.toList());
    }

    public void deleteStudentById(int id) {
        studentDao.deleteStudentById(id);
    }

    public void addNewStudent(String firstName, String lastName){
        Student student = new Student(firstName, lastName);
        studentDao.addNewStudent(student);
    }

    public void addNewStudentWithGroup(String firstName, String lastName, int groupId){
        Group group = groupService.getGroupById(groupId);
        Student student = new Student(firstName, lastName);
        student.setGroup(group);
        studentDao.addNewStudent(student);
    }
}
