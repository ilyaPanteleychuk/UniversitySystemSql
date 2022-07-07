package com.ilyapanteleychuk.task7schoolsystem.service;

import com.ilyapanteleychuk.task7schoolsystem.dao.CommonDao;
import com.ilyapanteleychuk.task7schoolsystem.dao.StudentDao;
import com.ilyapanteleychuk.task7schoolsystem.dao.StudentDaoImpl;
import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import java.util.List;
import java.util.stream.Collectors;


public class StudentService {

    private final CommonDao<Student> commonStudentDao;
    private final StudentDao studentDao;
    private final CourseService courseService;
    private final GroupService groupService;

    public StudentService(CommonDao<Student> commonStudentDao,
                          StudentDao studentDao, CourseService courseService,
                          GroupService groupService) {
        this.commonStudentDao = commonStudentDao;
        this.studentDao = studentDao;
        this.courseService = courseService;
        this.groupService = groupService;
    }

    public Student getStudentById(int studentId){
        int groupId = groupService.getGroupOfStudent(studentId);
        Student student = commonStudentDao.loadById(studentId);
        student.setGroup(groupService.getGroupById(groupId));
        return student;
    }

    public List<Student> loadAllStudents(){
        List<Student> students = commonStudentDao.loadAll();
        for(Student student : students){
            int groupId = groupService.getGroupOfStudent(student.getId());
            student.setGroup(groupService.getGroupById(groupId));
        }
        return students;
    }

    public void deleteStudentById(int id) {
        commonStudentDao.deleteById(id);
    }

    public void addNewStudent(String firstName, String lastName){
        Student student = new Student(firstName, lastName);
        commonStudentDao.add(student);
    }

    public void addNewStudentWithGroup(String firstName, String lastName, int groupId){
        Group group = groupService.getGroupById(groupId);
        Student student = new Student(firstName, lastName);
        student.setGroup(group);
        commonStudentDao.add(student);
    }

    public List<Student> getAllStudentsByCourseName(String courseName) {
        Course course = courseService.getCourseByName(courseName);
        return studentDao.getAllStudentsByCourseName(course).stream()
            .map(this::getStudentById)
            .collect(Collectors.toList());
    }
}
