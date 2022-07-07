package com.ilyapanteleychuk.task7schoolsystem.dao;

import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class DbInserterDao {

    private final ConnectionProvider connectionProvider;

    public DbInserterDao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public void fillStudentsTable(List<Student> students) {
        try {
            Connection connection = connectionProvider.getConnection();
            final String query = "insert into university" +
                ".students(group_id, first_name, last_name) values (?,?,?)";
            for (Student student : students) {
                PreparedStatement pr = connection.prepareStatement(query);
                pr.setInt(1, student.getGroup().getId());
                pr.setString(2, student.getFirstName());
                pr.setString(3, student.getLastName());
                pr.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillGroupsTable(List<Group> groups) {
        try {
            Connection connection = connectionProvider.getConnection();
            final String query = "insert into university.groups(group_name) values(?)";
            for (Group group : groups) {
                PreparedStatement pr = connection.prepareStatement(query);
                pr.setInt(1, group.getId());
                pr.setString(1, group.getName());
                pr.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillCoursesTable(List<Course> courses) {
        try {
            Connection connection = connectionProvider.getConnection();
            final String query = "insert into university" +
                ".courses(course_name, course_description) values(?,?)";
            for (Course course : courses) {
                PreparedStatement pr = connection.prepareStatement(query);
                pr.setString(1, course.getName());
                pr.setString(2, course.getDescription());
                pr.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillJointStudentsCoursesTable(List<Student> students) {
        try {
            Connection connection = connectionProvider.getConnection();
            final String query = "insert into university" +
                ".students_courses(student_id, course_id) values (?,?) " +
                "ON CONFLICT (student_id, course_id) DO NOTHING";
            for (Student student : students) {
                for (Course course : student.getCourses()) {
                    PreparedStatement pr = connection.prepareStatement(query);
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
