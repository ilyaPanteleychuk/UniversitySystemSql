package com.ilyapanteleychuk.task7schoolsystem.dao;

import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import com.ilyapanteleychuk.task7schoolsystem.repository.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class StudentDaoImpl {

    private final ConnectionProvider connectionProvider;
    private final GroupDaoImpl groupDao;

    public StudentDaoImpl(ConnectionProvider connectionProvider, GroupDaoImpl groupDao) {
        this.connectionProvider = connectionProvider;
        this.groupDao = groupDao;
    }

    public void addNewStudent(Student student) {
        try {
            Connection connection = connectionProvider.getConnection();
            String query = "insert into university." +
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
            String query = "select * from university.students_courses where course_id = ?";
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
            String query = "delete from university.students where student_id = ?";
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
            String query =
                "select * from university.students where student_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                int groupId = resultSet.getInt("group_id");
                Group group = groupDao.getGroupById(groupId);
                String firstName = resultSet.getString("first_name");
                String secondName = resultSet.getString("last_name");
                student = new Student(studentId, group, firstName, secondName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }
}
