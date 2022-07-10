package com.ilyapanteleychuk.task7schoolsystem.dao.impl;

import com.ilyapanteleychuk.task7schoolsystem.dao.CommonDao;
import com.ilyapanteleychuk.task7schoolsystem.dao.db.ConnectionProvider;
import com.ilyapanteleychuk.task7schoolsystem.dao.StudentDao;
import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class StudentDaoImpl implements CommonDao<Student>, StudentDao {

    private final ConnectionProvider connectionProvider;

    public StudentDaoImpl(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public void add(Student student) {
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

    @Override
    public void addAll(List<Student> studentList) {
        try {
            Connection connection = connectionProvider.getConnection();
            connection.setAutoCommit(false);
            final String query = "insert into university." +
                "students(group_id, first_name, last_name) values (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            for(Student student : studentList) {
                statement.setInt(1, student.getGroup().getId());
                statement.setString(2, student.getFirstName());
                statement.setString(3, student.getLastName());
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student loadById(int id) {
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

    @Override
    public List<Student> loadAll() {
        List<Student> students = new ArrayList<>();
        try {
            Connection connection = connectionProvider.getConnection();
            final String query =
                "select * from university.students";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                String firstName = resultSet.getString("first_name");
                String secondName = resultSet.getString("last_name");
                Student student = new Student(firstName, secondName);
                student.setId(studentId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    @Override
    public void deleteById(int id) {
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

    @Override
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

}
