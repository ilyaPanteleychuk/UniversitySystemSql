package com.ilyapanteleychuk.task7schoolsystem;

import com.ilyapanteleychuk.task7schoolsystem.dao.*;
import com.ilyapanteleychuk.task7schoolsystem.dao.db.ConnectionProvider;
import com.ilyapanteleychuk.task7schoolsystem.dao.impl.CourseDaoImpl;
import com.ilyapanteleychuk.task7schoolsystem.dao.impl.GroupDaoImpl;
import com.ilyapanteleychuk.task7schoolsystem.dao.impl.StudentDaoImpl;
import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import com.ilyapanteleychuk.task7schoolsystem.service.*;
import com.ilyapanteleychuk.task7schoolsystem.view.ApplicationUserMenu;


public class Main {
    public static void main(String[] args) {
        PropertiesReader propertiesReader = new PropertiesReader();
        String url = propertiesReader.getProperty("dbUrl");
        String username = propertiesReader.getProperty("username");
        String password = propertiesReader.getProperty("password");
        ConnectionProvider connectionProvider = new ConnectionProvider(url, username, password);
        DbInserterDao dbInserterDao = new DbInserterDao(connectionProvider);
        RandomInitialDataGenerator randomInitialDataGenerator = new RandomInitialDataGenerator();
        DbInserterService dbInserterService =
            new DbInserterService(dbInserterDao, randomInitialDataGenerator);
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
        Application application = new Application(dbInserterService, applicationUserMenu);
        application.startApp();
    }
}
