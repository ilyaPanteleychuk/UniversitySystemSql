package com.ilyapanteleychuk.task7schoolsystem;

import com.ilyapanteleychuk.task7schoolsystem.dao.*;
import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import com.ilyapanteleychuk.task7schoolsystem.service.PropertiesReader;
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
        GroupDao groupDao = new GroupDaoImpl(connectionProvider);
        CommonDao<Group> groupCommonDao = new GroupDaoImpl(connectionProvider);
        GroupService groupService = new GroupService(groupCommonDao, groupDao);
        CourseDao courseDao = new CourseDaoImpl(connectionProvider);
        CommonDao<Course> courseCommonDao = new CourseDaoImpl(connectionProvider);
        CourseService courseService = new CourseService(courseCommonDao, courseDao);
        StudentDao studentDao = new StudentDaoImpl(connectionProvider);
        CommonDao<Student> studentCommonDao = new StudentDaoImpl(connectionProvider);
        StudentService studentService =
            new StudentService(studentCommonDao, studentDao, courseService, groupService);
        ApplicationUserMenu applicationUserMenu =
            new ApplicationUserMenu(courseService, groupService, studentService);
        applicationUserMenu.startApp();
    }

}
