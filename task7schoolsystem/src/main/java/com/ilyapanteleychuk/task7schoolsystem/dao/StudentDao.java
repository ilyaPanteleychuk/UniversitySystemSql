package com.ilyapanteleychuk.task7schoolsystem.dao;

import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class StudentDao {

    private final ConnectionProvider connectionProvider;

    public StudentDao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public void addNewStudent(Student student) {
        try {
            Connection connection = connectionProvider.getConnection();
            final String query = "insert into university." +
                "students(group_id, first_name, last_name) values (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, student.getGroup().getId());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> getAllStudentsByCourseName(Course course) {
        List<Integer> studentsId = new ArrayList<>();
        try {
            Connection connection = connectionProvider.getConnection();
            final String query =
                "select * from university.students_courses where course_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, course.getId());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                studentsId.add(resultSet.getInt("student_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentsId;
    }

    public void deleteStudentById(int id) {
        try {
            Connection connection = connectionProvider.getConnection();
            final String query =
                "delete from university.students where student_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Student getStudentById(int id) {
        Student student = null;
        try {
            Connection connection = connectionProvider.getConnection();
            final String query =
                "select * from university.students where student_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                String firstName = resultSet.getString("first_name");
                String secondName = resultSet.getString("last_name");
                student = new Student(firstName, secondName);
                student.setId(studentId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }
}
