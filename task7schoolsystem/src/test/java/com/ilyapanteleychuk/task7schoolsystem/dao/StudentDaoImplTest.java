package com.ilyapanteleychuk.task7schoolsystem.dao;

import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import com.ilyapanteleychuk.task7schoolsystem.repository.ConnectionProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;


class StudentDaoImplTest {

    private ConnectionProvider providerMock;
    private Connection connectionMock;
    private PreparedStatement statementMock;
    private ResultSet resultSetMock;

    @BeforeEach
    void init(){
        providerMock = Mockito.mock(ConnectionProvider.class);
        connectionMock = Mockito.mock(Connection.class);
        statementMock = Mockito.mock(PreparedStatement.class);
        resultSetMock = Mockito.mock(ResultSet.class);
    }

    @Test
    void addNewStudent_shouldAddStudentCorrectly() {
        try {
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class))).thenReturn(statementMock);
            StudentDao studentDao = new StudentDaoImpl(new GroupDaoImpl(providerMock), providerMock);
            Student student = new Student(new Group(1, "pp-13"),
                "John", "Doe");
            studentDao.addNewStudent(student);
            verify(statementMock, times(1))
                .setInt(1, student.getGroup().getId());
            verify(statementMock, times(1))
                .setString(2, student.getFirstName());
            verify(statementMock, times(1))
                .setString(3, student.getLastName());
            verify(statementMock, times(1)).executeUpdate();
            verifyNoMoreInteractions(statementMock);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void deleteStudentById_shouldDeleteStudentById() {
        try {
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class))).thenReturn(statementMock);
            StudentDao studentDao = new StudentDaoImpl(new GroupDaoImpl(providerMock), providerMock);
            studentDao.deleteStudentById(1);
            verify(statementMock, times(1))
                .setInt(1, 1);
            verify(statementMock, times(1)).executeUpdate();
            verifyNoMoreInteractions(statementMock);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getStudentById_shouldReturnStudentWithId() {
        try {
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class))).thenReturn(statementMock);
            when(statementMock.executeQuery()).thenReturn(resultSetMock);
            when(resultSetMock.next()).thenReturn(true);
            when(resultSetMock.getInt(any(String.class))).thenReturn(1);
            when(resultSetMock.getString(any(String.class))).thenReturn("first_name");
            when(resultSetMock.getString(any(String.class))).thenReturn("last_name");
            StudentDao studentDao = new StudentDaoImpl(new GroupDaoImpl(providerMock), providerMock);
            studentDao.getStudentById(1);
            verify(statementMock, times(2))
                .setInt(1, 1);
            verify(resultSetMock, times(2)).next();
            verify(statementMock, times(2)).executeQuery();
            verify(resultSetMock, times(1)).getInt("student_id");
            verify(resultSetMock, times(1)).getString("first_name");
            verify(resultSetMock, times(1)).getString("last_name");
            verifyNoMoreInteractions(statementMock);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getAllStudentsByCourseName_shouldReturnArrayListOfStudentCourses(){
        try {
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class))).thenReturn(statementMock);
            when(statementMock.executeQuery()).thenReturn(resultSetMock);
            when(resultSetMock.next()).thenReturn(true).thenReturn(false);
            when(resultSetMock.getInt(any(String.class))).thenReturn(1);
            StudentDao studentDao = new StudentDaoImpl(new GroupDaoImpl(providerMock), providerMock);
            studentDao.getAllStudentsByCourseName(new Course(1, "Math", "MathStudying"));
            verify(statementMock, times(1))
                .setInt(1, 1);
            verify(resultSetMock, times(2)).next();
            verify(statementMock, times(1)).executeQuery();
            verify(resultSetMock, times(1)).getInt("student_id");
            verifyNoMoreInteractions(resultSetMock);
            verifyNoMoreInteractions(statementMock);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
