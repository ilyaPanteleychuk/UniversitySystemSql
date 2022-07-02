package com.ilyapanteleychuk.task7schoolsystem.dao;

import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
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


class CourseDaoImplTest {

    private ConnectionProvider providerMock;
    private Connection connectionMock;
    private PreparedStatement statementMock;
    private ResultSet resultSetMock;

    @BeforeEach
    void init() {
        providerMock = Mockito.mock(ConnectionProvider.class);
        connectionMock = Mockito.mock(Connection.class);
        statementMock = Mockito.mock(PreparedStatement.class);
        resultSetMock = Mockito.mock(ResultSet.class);
    }

    @Test
    void getAllCourses_shouldReturnArrayListOfCourses() {
        try {
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class))).thenReturn(statementMock);
            when(statementMock.executeQuery()).thenReturn(resultSetMock);
            when(resultSetMock.next()).thenReturn(true).thenReturn(false);
            when(resultSetMock.getInt(any(String.class))).thenReturn(1);
            when(resultSetMock.getString(any(String.class))).thenReturn("course_name");
            CourseDao courseDao = new CourseDaoImpl(providerMock);
            courseDao.getAllCourses();
            verify(statementMock, times(1)).executeQuery();
            verify(resultSetMock, times(2)).next();
            verify(resultSetMock, times(1)).getInt("course_id");
            verify(resultSetMock, times(1)).getString("course_name");
            verify(resultSetMock, times(1)).getString("course_description");
            verifyNoMoreInteractions(statementMock);
            verifyNoMoreInteractions(resultSetMock);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getCourseByName_shouldReturnCourseWithParticularName() {
        try {
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class))).thenReturn(statementMock);
            when(statementMock.executeQuery()).thenReturn(resultSetMock);
            when(resultSetMock.next()).thenReturn(true).thenReturn(false);
            when(resultSetMock.getInt(any(String.class))).thenReturn(1);
            when(resultSetMock.getString(any(String.class))).thenReturn("course_name");
            CourseDao courseDao = new CourseDaoImpl(providerMock);
            courseDao.getCourseByName("Math");
            verify(statementMock, times(1)).setString(1, "Math");
            verify(statementMock, times(1)).executeQuery();
            verify(resultSetMock, times(2)).next();
            verify(resultSetMock, times(1)).getInt("course_id");
            verify(resultSetMock, times(1)).getString("course_name");
            verify(resultSetMock, times(1)).getString("course_description");
            verifyNoMoreInteractions(statementMock);
            verifyNoMoreInteractions(resultSetMock);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void addStudentToCourse_shouldAddStudentToParticularCourse() {
        try {
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class))).thenReturn(statementMock);
            CourseDao courseDao = new CourseDaoImpl(providerMock);
            courseDao.addStudentToCourse(1, 1);
            verify(statementMock, times(1)).setInt(1, 1);
            verify(statementMock, times(1)).setInt(2, 1);
            verify(statementMock, times(1)).executeUpdate();
            verifyNoMoreInteractions(statementMock);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void removeStudentFromCourse_shouldRemoveStudentFromCourse() {
        try {
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class))).thenReturn(statementMock);
            CourseDao courseDao = new CourseDaoImpl(providerMock);
            courseDao.removeStudentFromCourse(1, new Course(1, "name", "description"));
            verify(statementMock, times(1)).setInt(1, 1);
            verify(statementMock, times(1)).setInt(2, 1);
            verify(statementMock, times(1)).executeUpdate();
            verifyNoMoreInteractions(statementMock);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getCourseById_shouldReturnCourseById() {
        try {
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class))).thenReturn(statementMock);
            when(statementMock.executeQuery()).thenReturn(resultSetMock);
            when(resultSetMock.next()).thenReturn(true);
            when(resultSetMock.getInt(any(String.class))).thenReturn(1);
            when(resultSetMock.getString(any(String.class))).thenReturn("course_name");
            CourseDao courseDao = new CourseDaoImpl(providerMock);
            courseDao.getCourseById(1);
            verify(statementMock, times(1)).setInt(1, 1);
            verify(statementMock, times(1)).executeQuery();
            verify(resultSetMock, times(1)).next();
            verify(resultSetMock, times(1)).getInt("course_id");
            verify(resultSetMock, times(1)).getString("course_name");
            verify(resultSetMock, times(1)).getString("course_description");
            verifyNoMoreInteractions(statementMock);
            verifyNoMoreInteractions(resultSetMock);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getAllStudentCourses_shouldReturnArrayListOfCoursesOfStudent() {
        try {
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class))).thenReturn(statementMock);
            when(statementMock.executeQuery()).thenReturn(resultSetMock);
            when(resultSetMock.next()).thenReturn(true).thenReturn(false);
            when(resultSetMock.getInt(any(String.class))).thenReturn(1);
            CourseDao courseDao = new CourseDaoImpl(providerMock);
            courseDao.getAllStudentCourses(1);
            verify(statementMock, times(1)).setInt(1, 1);
            verify(statementMock, times(1)).executeQuery();
            verify(resultSetMock, times(2)).next();
            verify(resultSetMock, times(1)).getInt("course_id");
            verifyNoMoreInteractions(statementMock);
            verifyNoMoreInteractions(resultSetMock);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
