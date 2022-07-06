package com.ilyapanteleychuk.task7schoolsystem.service;

import com.ilyapanteleychuk.task7schoolsystem.PropertiesReader;
import com.ilyapanteleychuk.task7schoolsystem.dao.CourseDaoImpl;
import com.ilyapanteleychuk.task7schoolsystem.dao.GroupDaoImpl;
import com.ilyapanteleychuk.task7schoolsystem.dao.StudentDaoImpl;
import com.ilyapanteleychuk.task7schoolsystem.repository.ConnectionProvider;
import com.ilyapanteleychuk.task7schoolsystem.repository.DbInserter;
import com.ilyapanteleychuk.task7schoolsystem.service.dao.CourseService;
import com.ilyapanteleychuk.task7schoolsystem.service.dao.GroupService;
import com.ilyapanteleychuk.task7schoolsystem.service.dao.StudentService;
import com.ilyapanteleychuk.task7schoolsystem.service.repository.DbInserterService;
import com.ilyapanteleychuk.task7schoolsystem.service.view.ApplicationUserMenu;


public class UniversityService {

    public void startApp(){
        PropertiesReader propertiesReader = new PropertiesReader();
        String url = propertiesReader.getProperty("dbUrl");
        String username = propertiesReader.getProperty("username");
        String password = propertiesReader.getProperty("password");
        ConnectionProvider connectionProvider = new ConnectionProvider(url, username, password);
        DbInserter dbInserter = new DbInserter(connectionProvider);
        ColumnGenerator columnGenerator = new ColumnGenerator();
        DbInserterService dataBaseService = new DbInserterService(dbInserter, columnGenerator);
        dataBaseService.fillTables();
        GroupDaoImpl groupDao = new GroupDaoImpl(connectionProvider);
        StudentDaoImpl studentDao = new StudentDaoImpl(connectionProvider, groupDao);
        CourseDaoImpl courseDao = new CourseDaoImpl(connectionProvider);
        GroupService groupService = new GroupService(groupDao);
        StudentService studentService = new StudentService(studentDao, courseDao);
        CourseService courseService = new CourseService(courseDao);
        ApplicationUserMenu applicationUserMenu =
            new ApplicationUserMenu(courseService, groupService, studentService);
        applicationUserMenu.startApp();
    }

}
