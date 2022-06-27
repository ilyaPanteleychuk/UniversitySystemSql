package com.ilyapanteleychuk.task7schoolsystem.service;

import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import com.ilyapanteleychuk.task7schoolsystem.repository.DataBaseFacadeImpl;
import java.util.List;


public class UserServiceImpl implements UserService{

    private final DataBaseFacadeImpl dataBase;
    private final String url;
    private final String username;
    private final String pass;

    public UserServiceImpl(DataBaseFacadeImpl dataBase, String url, String username, String pass) {
        this.dataBase = dataBase;
        this.url = url;
        this.username = username;
        this.pass = pass;
    }

    @Override
    public void createTables(String changeLogPath) {
        dataBase.createTables(url, username, pass, changeLogPath);

    }

    public void insertCourses(List<Course> courses){
        dataBase.fillCoursesTable(courses, url, username, pass);
    }

    @Override
    public void insertStudents(List<Student> studentList){
        dataBase.fillStudentsTable(studentList, url, username, pass);
    }

    public void insertGroups(List<Group> groups){
        dataBase.fillGroupsTable(groups, url, username, pass);
    }

    public void fillJointStudentsCoursesTable(List<Student> students){
        dataBase.fillJointStudentsCoursesTable(students, url, username, pass);
    }
}
