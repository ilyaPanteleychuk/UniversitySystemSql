package com.ilyapanteleychuk.task7schoolsystem.dao;

import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import org.apache.commons.lang.StringUtils;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CourseDaoImpl implements CommonDao<Course>, CourseDao {

    private final ConnectionProvider connectionProvider;

    public CourseDaoImpl(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public Course loadById(int id) {
        Course course = null;
        try {
            Connection connection = connectionProvider.getConnection();
            final String query =
                "SELECT * FROM university.courses where course_id = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                int courseId = resultSet.getInt("course_id");
                String name = resultSet.getString("course_name");
                String description = resultSet.getString("course_description");
                course = new Course(id, name, description);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return course;
    }

    @Override
    public List<Course> loadAll() {
        List<Course> allCourses = new ArrayList<>();
        try {
            Connection connection = connectionProvider.getConnection();
            final String query = "select * from university.courses";
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

    @Override
    public void deleteById(int id) {
        try {
            Connection connection = connectionProvider.getConnection();
            final String query =
                "delete from university.courses where course_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Course course) {
        try {
            Connection connection = connectionProvider.getConnection();
            final String query = "insert into university." +
                "students(course_id, course_name, course_description) values (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, course.getId());
            statement.setString(2, course.getName());
            statement.setString(2, course.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Course getCourseByName(String name) {
        Course course = null;
        try {
            Connection connection = connectionProvider.getConnection();
            final String query =
                "select * from university.courses where course_name = ?";
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

    @Override
    public void addStudentToCourse(int studentId, int courseId) {
        try {
            Connection connection = connectionProvider.getConnection();
            final String query = "insert into university." +
                "students_courses(student_id, course_id) values (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeStudentFromCourse(int studentId, int courseId) {
        try {
            Connection connection = connectionProvider.getConnection();
            final String query = "DELETE FROM university.students_courses " +
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
            final String query =
                "SELECT * FROM university.courses where course_id = ?;";
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

    @Override
    public List<Integer> getAllStudentCourses(int studentId) {
        List<Integer> studentCourses = new ArrayList<>();
        try {
            Connection connection = connectionProvider.getConnection();
            final String query =
                "SELECT * FROM university.students_courses where student_id = ?;";
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
