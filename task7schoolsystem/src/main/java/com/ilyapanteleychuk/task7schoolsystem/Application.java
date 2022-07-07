package com.ilyapanteleychuk.task7schoolsystem;

import com.ilyapanteleychuk.task7schoolsystem.service.PropertiesReader;
import com.ilyapanteleychuk.task7schoolsystem.dao.CourseDao;
import com.ilyapanteleychuk.task7schoolsystem.dao.GroupDao;
import com.ilyapanteleychuk.task7schoolsystem.dao.StudentDao;
import com.ilyapanteleychuk.task7schoolsystem.dao.ConnectionProvider;
import com.ilyapanteleychuk.task7schoolsystem.dao.DbInserterDao;
import com.ilyapanteleychuk.task7schoolsystem.service.*;
import com.ilyapanteleychuk.task7schoolsystem.view.ApplicationUserMenu;


public class Application {

    public void startApp(){
        PropertiesReader propertiesReader = new PropertiesReader();
        String url = propertiesReader.getProperty("dbUrl");
        String username = propertiesReader.getProperty("username");
        String password = propertiesReader.getProperty("password");
        ConnectionProvider connectionProvider = new ConnectionProvider(url, username, password);
        DbInserterDao dbInserterDao = new DbInserterDao(connectionProvider);
        RandomInitialDataGenerator randomInitialDataGenerator = new RandomInitialDataGenerator();
        DbInserterService dataBaseService = new DbInserterService(dbInserterDao, randomInitialDataGenerator);
        dataBaseService.fillTables();
        GroupDao groupDao = new GroupDao(connectionProvider);
        StudentDao studentDao = new StudentDao(connectionProvider);
        CourseDao courseDao = new CourseDao(connectionProvider);
        GroupService groupService = new GroupService(groupDao);
        StudentService studentService = new StudentService(studentDao, courseDao, groupService);
        CourseService courseService = new CourseService(courseDao);
        ApplicationUserMenu applicationUserMenu =
            new ApplicationUserMenu(courseService, groupService, studentService);
        applicationUserMenu.startApp();
    }

}
