package com.ilyapanteleychuk.task7schoolsystem.dao;

import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import org.apache.commons.lang.StringUtils;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CourseDao {

    private final ConnectionProvider connectionProvider;

    public CourseDao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public List<Course> getAllCourses() {
        List<Course> allCourses = new ArrayList<>();
        try {
            Connection connection = connectionProvider.getConnection();
            String query = "select * from university.courses";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("course_id");
                String name = resultSet.getString("course_name");
                String description = resultSet.getString("course_description");
                allCourses.add(new Course(id, name, description));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allCourses;
    }

    public Course getCourseByName(String name) {
        Course course = null;
        try {
            Connection connection = connectionProvider.getConnection();
            String query = "select * from university.courses where course_name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, StringUtils.capitalize(name));
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("course_id");
                String courseName = resultSet.getString("course_name");
                String courseDescription = resultSet.getString("course_description");
                course = new Course(id, courseName, courseDescription);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return course;
    }

    public void addStudentToCourse(int studentId, int courseId) {
        try {
            Connection connection = connectionProvider.getConnection();
            String query = "insert into university." +
                "students_courses(student_id, course_id) values (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeStudentFromCourse(int studentId, int courseId) {
        try {
            Connection connection = connectionProvider.getConnection();
            String query = "DELETE FROM university.students_courses " +
                "where student_id = ? and course_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Course getCourseById(int courseId) {
        Course course = null;
        try {
            Connection connection = connectionProvider.getConnection();
            String query = "SELECT * FROM university.courses where course_id = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, courseId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                int id = resultSet.getInt("course_id");
                String name = resultSet.getString("course_name");
                String description = resultSet.getString("course_description");
                course = new Course(id, name, description);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return course;
    }

    public List<Integer> getAllStudentCourses(int studentId) {
        List<Integer> studentCourses = new ArrayList<>();
        try {
            Connection connection = connectionProvider.getConnection();
            String query = "SELECT * FROM university.students_courses where student_id = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int courseName = resultSet.getInt("course_id");
                studentCourses.add(courseName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentCourses;
    }
}
