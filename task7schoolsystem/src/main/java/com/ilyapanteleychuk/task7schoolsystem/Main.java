package com.ilyapanteleychuk.task7schoolsystem;

import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import com.ilyapanteleychuk.task7schoolsystem.repository.ColumnGeneratorImpl;
import com.ilyapanteleychuk.task7schoolsystem.repository.DataBaseFacadeImpl;
import com.ilyapanteleychuk.task7schoolsystem.service.UserServiceImpl;

import java.util.List;


//jdbc:postgresql://localhost:5432/postgres
public class Main {

    private static final String URL ="jdbc:postgresql://localhost:5432" +
        "/test_db?createDatabaseIfNotExist=true";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";
    private static final String changeLogFile = "/db/changelog/changelog-master.xml";

    public static void main( String[] args ) {
        ColumnGeneratorImpl columnGenerator = new ColumnGeneratorImpl();
        List<Group> groups = columnGenerator.generateRandomGroups();
        List<Student> students = columnGenerator.setStudentsToGroup(groups);
        List<Course> courses = columnGenerator.generateCourses();
        students = columnGenerator.setCoursesToStudents(students, courses);
        DataBaseFacadeImpl dataBase = new DataBaseFacadeImpl();
        UserServiceImpl service = new UserServiceImpl(dataBase, URL, USERNAME, PASSWORD);
        service.createTables(changeLogFile);
        service.insertCourses(courses);
        service.insertGroups(groups);
        service.insertStudents(students);
        service.fillJointStudentsCoursesTable(students);
    }
}
