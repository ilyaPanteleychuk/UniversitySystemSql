package com.ilyapanteleychuk.task7schoolsystem.service.repository;

import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import com.ilyapanteleychuk.task7schoolsystem.service.ColumnGenerator;
import com.ilyapanteleychuk.task7schoolsystem.repository.DbInserter;

import java.util.List;


public class DbInserterService {

    private final DbInserter dbInserter;
    private final ColumnGenerator columnGenerator;

    public DbInserterService(DbInserter dbInserter, ColumnGenerator columnGenerator) {
        this.dbInserter = dbInserter;
        this.columnGenerator = columnGenerator;
    }

    public void fillTables() {
        List<Group> groups = columnGenerator.generateRandomGroups();
        List<Student> students = columnGenerator.generateRandomStudents();
        students = columnGenerator.setStudentsToGroup(students, groups);
        List<Course> courses = columnGenerator.generateCourses();
        students = columnGenerator.setCoursesToStudents(students, courses);
        dbInserter.fillCoursesTable(courses);
        dbInserter.fillGroupsTable(groups);
        dbInserter.fillStudentsTable(students);
        dbInserter.fillJointStudentsCoursesTable(students);
    }
}
