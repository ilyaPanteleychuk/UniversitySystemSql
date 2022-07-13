package com.ilyapanteleychuk.task7schoolsystem.dao.impl;

import com.ilyapanteleychuk.task7schoolsystem.dao.db.ConnectionProvider;
import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
    private CourseDaoImpl courseDao;

    @BeforeEach
    void init(){
        providerMock = Mockito.mock(ConnectionProvider.class);
        connectionMock = Mockito.mock(Connection.class);
        statementMock = Mockito.mock(PreparedStatement.class);
        resultSetMock = Mockito.mock(ResultSet.class);
        courseDao = new CourseDaoImpl(providerMock);
    }

    @Test
    void loadById_shouldWorkCorrectly_whenInputIsValidId() {
        try {
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class))).thenReturn(statementMock);
            when(statementMock.executeQuery()).thenReturn(resultSetMock);
            when(resultSetMock.next()).thenReturn(true);
            when(resultSetMock.getInt("course_id")).thenReturn(1);
            when(resultSetMock.getString("course_name")).thenReturn("name");
            when(resultSetMock.getString("course_description")).thenReturn("description");
            courseDao.loadById(1);
            verify(statementMock, times(1))
                .setInt(1, 1);
            verify(statementMock, times(1)).executeQuery();
            verify(resultSetMock, times(1)).next();
            verify(resultSetMock, times(1)).getInt("course_id");
            verify(resultSetMock, times(1)).getString("course_name");
            verify(resultSetMock, times(1)).getString("course_description");
            verifyNoMoreInteractions(resultSetMock);
            verifyNoMoreInteractions(statementMock);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void loadAll_shouldWorkCorrectly() {
        try{
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class)))
                .thenReturn(statementMock);
            when(statementMock.executeQuery()).thenReturn(resultSetMock);
            when(resultSetMock.next()).thenReturn(true).thenReturn(false);
            when(resultSetMock.getInt("course_id")).thenReturn(1);
            when(resultSetMock.getString("course_name")).thenReturn("name");
            when(resultSetMock.getString("course_description")).thenReturn("description");
            courseDao.loadAll();
            verify(statementMock, times(1))
                .executeQuery();
            verify(resultSetMock, times(2)).next();
            verify(resultSetMock, times(1)).getInt("course_id");
            verify(resultSetMock, times(1)).getString("course_name");
            verify(resultSetMock, times(1)).getString("course_description");
            verifyNoMoreInteractions(resultSetMock);
            verifyNoMoreInteractions(statementMock);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void deleteById() {
        try{
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class)))
                .thenReturn(statementMock);
            courseDao.deleteById(1);
            verify(statementMock, times(1)).setInt(1, 1);
            verify(statementMock, times(1)).executeUpdate();
            verifyNoMoreInteractions(statementMock);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void add() {
        try {
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class)))
                .thenReturn(statementMock);
            courseDao.add(new Course(1, "Name", "Description"));
            verify(statementMock, times(1))
                .setInt(1, 1);
            verify(statementMock, times(1))
                .setString(2, "Name");
            verify(statementMock, times(1))
                .setString(3, "Description");
            verify(statementMock, times(1))
                .executeUpdate();
            verifyNoMoreInteractions(statementMock);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void addAll(){
        try {
            Course courseMock = Mockito.mock(Course.class);
            List<Course> courseList = List.of(courseMock);
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class)))
                .thenReturn(statementMock);
            when(courseMock.getId()).thenReturn(1);
            when(courseMock.getName()).thenReturn("name");
            when(courseMock.getDescription()).thenReturn("description");
            courseDao.addAll(courseList);
            verify(courseMock, times(1)).getId();
            verify(courseMock, times(1)).getName();
            verify(courseMock, times(1)).getDescription();
            verify(statementMock, times(1))
                .setInt(1, 1);
            verify(statementMock, times(1))
                .setString(2, "name");
            verify(statementMock, times(1))
                .setString(3, "description");
            verify(statementMock, times(1))
                .addBatch();
            verify(statementMock, times(1)).executeBatch();
            verify(connectionMock, times(1)).setAutoCommit(false);
            verify(connectionMock, times(1)).commit();
            verifyNoMoreInteractions(statementMock);
            verifyNoMoreInteractions(courseMock);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void getCourseByName() {
        try{
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class)))
                .thenReturn(statementMock);
            when(statementMock.executeQuery()).thenReturn(resultSetMock);
            when(resultSetMock.next()).thenReturn(true);
            when(resultSetMock.getInt("course_id")).thenReturn(1);
            when(resultSetMock.getString("course_name")).thenReturn("name");
            when(resultSetMock.getString("course_description")).thenReturn("description");
            courseDao.getCourseByName("Name");
            verify(statementMock, times(1)).setString(1, "Name");
            verify(statementMock, times(1))
                .executeQuery();
            verify(resultSetMock, times(1)).next();
            verify(resultSetMock, times(1)).getInt("course_id");
            verify(resultSetMock, times(1)).getString("course_name");
            verify(resultSetMock, times(1)).getString("course_description");
            verifyNoMoreInteractions(resultSetMock);
            verifyNoMoreInteractions(statementMock);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void addStudentToCourse() {
        try {
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class)))
                .thenReturn(statementMock);
            courseDao.addStudentToCourse(1, 1);
            verify(statementMock, times(1))
                .setInt(1, 1);
            verify(statementMock, times(1)).setInt(2, 1);
            verify(statementMock, times(1))
                .executeUpdate();
            verifyNoMoreInteractions(statementMock);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void removeStudentFromCourse() {
        try {
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class)))
                .thenReturn(statementMock);
            courseDao.removeStudentFromCourse(1, 1);
            verify(statementMock, times(1))
                .setInt(1, 1);
            verify(statementMock, times(1)).setInt(2, 1);
            verify(statementMock, times(1))
                .executeUpdate();
            verifyNoMoreInteractions(statementMock);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void getAllStudentCourses() {
        try{
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class)))
                .thenReturn(statementMock);
            when(statementMock.executeQuery()).thenReturn(resultSetMock);
            when(resultSetMock.next()).thenReturn(true).thenReturn(false);
            when(resultSetMock.getInt("course_id")).thenReturn(1);
            when(resultSetMock.getString("course_name")).thenReturn("name");
            when(resultSetMock.getString("course_description")).thenReturn("description");
            courseDao.getAllStudentCourses(1);
            verify(statementMock, times(1)).setInt(1, 1);
            verify(statementMock, times(1))
                .executeQuery();
            verify(resultSetMock, times(2)).next();
            verify(resultSetMock, times(1)).getInt("course_id");
            verifyNoMoreInteractions(resultSetMock);
            verifyNoMoreInteractions(statementMock);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
