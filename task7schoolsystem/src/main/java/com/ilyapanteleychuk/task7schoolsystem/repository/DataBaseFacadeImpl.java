package com.ilyapanteleychuk.task7schoolsystem.repository;

import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.*;
import java.util.List;


public class DataBaseFacadeImpl implements DataBaseFacade {

//    private final ColumnGeneratorImpl columnGenerator;
//
//    public DataBaseFacadeImpl(ColumnGeneratorImpl columnGenerator) {
//        this.columnGenerator = columnGenerator;
//    }

    @Override
    public void createTables(String url, String username, String pass, String changeLogPath) {
        Connection connection = null;
        Liquibase liquibase = null;
        try {
            connection = DriverManager.getConnection(url, username, pass);
            Database database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(new JdbcConnection(connection));
            liquibase = new Liquibase(changeLogPath,
                new ClassLoaderResourceAccessor(), database);
            liquibase.update(new Contexts());
        } catch (SQLException | LiquibaseException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.rollback();
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void fillStudentsTable(List<Student> students, String url, String username, String pass) {
        try (Connection connection = DriverManager.getConnection(url, username, pass)) {
            for (Student student : students) {
                PreparedStatement pr = connection
                    .prepareStatement
                        ("insert into university.students(group_id," +
                            "first_name," +
                            "last_name) " +
                            "values (?,?,?)");
                pr.setInt(1, student.getGroup().getId());
                pr.setString(2, student.getFirstName());
                pr.setString(3, student.getLastName());
                pr.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillGroupsTable(List<Group> groups, String url, String username, String pass) {
        try (Connection connection = DriverManager.getConnection(url, username, pass)) {
            for (Group group : groups) {
                PreparedStatement pr = connection
                    .prepareStatement
                        ("insert into university.groups(group_name)" +
                            "values (?)", Statement.RETURN_GENERATED_KEYS);
                pr.setInt(1, group.getId());
                pr.setString(1, group.getName());
                pr.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillCoursesTable(List<Course> courses, String url, String username, String pass) {
        try (Connection connection = DriverManager.getConnection(url, username, pass)) {
            for (Course course : courses) {
                PreparedStatement pr = connection
                    .prepareStatement
                        ("insert into university.courses(course_name, course_description) " +
                            "values (?,?)", Statement.RETURN_GENERATED_KEYS);
                pr.setString(1, course.getName());
                pr.setString(2, course.getDescription());
                pr.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillJointStudentsCoursesTable(List<Student> students, String url, String username, String pass) {
        try (Connection connection = DriverManager.getConnection(url, username, pass)) {
            for (Student student : students) {
                for (Course course : student.getCourses()) {
                    PreparedStatement pr = connection
                        .prepareStatement
                            ("insert into university.students_courses(student_id, course_id) " +
                                "values (?,?)");
                    pr.setInt(1, student.getId());
                    pr.setInt(2, course.getId());
                    pr.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
