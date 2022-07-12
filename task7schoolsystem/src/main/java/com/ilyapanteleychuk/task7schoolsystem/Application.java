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

import java.util.Scanner;


public class Application {

    private final ConnectionProvider connectionProvider;
    private final DbInserterDao dbInserterDao;
    private final RandomInitialDataGenerator randomInitialDataGenerator;
    DbInserterService dbInserterService;
    private final GroupDao groupDao;
    private final CourseDao courseDao;
    private final CommonDao<Group> groupCommonDao;
    private final GroupService groupService;
    private final CommonDao<Course> courseCommonDao;
    private final CourseService courseService;
    private final StudentDao studentDao;
    private final CommonDao<Student> studentCommonDao;
    private final StudentService studentService;
    ApplicationUserMenu applicationUserMenu;

    public Application(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
        this.dbInserterDao = new DbInserterDao(connectionProvider);
        this.randomInitialDataGenerator = new RandomInitialDataGenerator();
        this.dbInserterService = new DbInserterService(dbInserterDao, randomInitialDataGenerator);
        this.groupDao = new GroupDaoImpl(connectionProvider);
        this.courseDao = new CourseDaoImpl(connectionProvider);
        this.groupCommonDao = new GroupDaoImpl(connectionProvider);
        this.groupService = new GroupService(groupCommonDao, groupDao);
        this.courseCommonDao = new CourseDaoImpl(connectionProvider);
        this.courseService = new CourseService(courseCommonDao, courseDao);
        this.studentDao = new StudentDaoImpl(connectionProvider);
        this.studentCommonDao = new StudentDaoImpl(connectionProvider);
        this.studentService = new StudentService(studentCommonDao, studentDao,
            courseService, groupService);
        this.applicationUserMenu = new ApplicationUserMenu(courseService, groupService, studentService);
    }

    public void startApp(){
        dbInserterService.fillTables();
        applicationUserMenu.startMenu();
    }
}
