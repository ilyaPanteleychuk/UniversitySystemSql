package com.ilyapanteleychuk.task7schoolsystem.service;

import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import com.ilyapanteleychuk.task7schoolsystem.dao.DbInserterDao;
import java.util.List;


public class DbInserterService {

    private final DbInserterDao dbInserterDao;
    private final RandomInitialDataGenerator randomInitialDataGenerator;

    public DbInserterService(DbInserterDao dbInserterDao,
                             RandomInitialDataGenerator randomInitialDataGenerator) {
        this.dbInserterDao = dbInserterDao;
        this.randomInitialDataGenerator = randomInitialDataGenerator;
    }

    public void fillTables() {
        List<Group> groups = randomInitialDataGenerator.generateRandomGroups();
        List<Student> students = randomInitialDataGenerator.generateRandomStudents();
        students = randomInitialDataGenerator.assignStudentsToGroups(students, groups);
        List<Course> courses = randomInitialDataGenerator.generateCourses();
        students = randomInitialDataGenerator.assignStudentsToCourses(students, courses);
        dbInserterDao.addAllCourses(courses);
        dbInserterDao.addAllGroups(groups);
        dbInserterDao.addAllStudents(students);
        dbInserterDao.addAllCoursesStudents(students);
    }
}
