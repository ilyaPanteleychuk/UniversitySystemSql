package com.ilyapanteleychuk.task7schoolsystem.service;

import com.ilyapanteleychuk.task7schoolsystem.dao.*;
import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;

import java.util.List;


public class DbInserterService {

    private final CommonDao<Course> courseCommonDao;
    private final CommonDao<Group> groupCommonDao;
    private final CommonDao<Student> studentCommonDao;
    private final StudentDao studentDao;
    private final RandomInitialDataGenerator randomInitialDataGenerator;

    public DbInserterService(CommonDao<Course> courseCommonDao,
                             CommonDao<Group> groupCommonDao,
                             CommonDao<Student> studentCommonDao,
                             StudentDao studentDao,
                             RandomInitialDataGenerator randomInitialDataGenerator) {
        this.courseCommonDao = courseCommonDao;
        this.groupCommonDao = groupCommonDao;
        this.studentCommonDao = studentCommonDao;
        this.studentDao = studentDao;
        this.randomInitialDataGenerator = randomInitialDataGenerator;
    }

    public void fillTables() {
        List<Group> groups = randomInitialDataGenerator.generateRandomGroups();
        List<Student> students = randomInitialDataGenerator.generateRandomStudents();
        students = randomInitialDataGenerator.assignStudentsToGroups(students, groups);
        List<Course> courses = randomInitialDataGenerator.generateCourses();
        students = randomInitialDataGenerator.assignStudentsToCourses(students, courses);
        courseCommonDao.addAll(courses);
        groupCommonDao.addAll(groups);
        studentCommonDao.addAll(students);
        studentDao.addAllCoursesStudents(students);
    }
}
