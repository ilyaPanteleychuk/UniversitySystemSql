package com.ilyapanteleychuk.task7schoolsystem.dao.impl;

import com.ilyapanteleychuk.task7schoolsystem.dao.db.ConnectionProvider;
import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
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


class StudentDaoImplTest {

    private ConnectionProvider providerMock;
    private Connection connectionMock;
    private PreparedStatement statementMock;
    private ResultSet resultSetMock;
    private StudentDaoImpl studentDao;

    @BeforeEach
    void init(){
        providerMock = Mockito.mock(ConnectionProvider.class);
        connectionMock = Mockito.mock(Connection.class);
        statementMock = Mockito.mock(PreparedStatement.class);
        resultSetMock = Mockito.mock(ResultSet.class);
        studentDao = new StudentDaoImpl(providerMock);
    }

    @Test
    void add() {
        try {
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class)))
                .thenReturn(statementMock);
            Student student = new Student("name", "lastName");
            student.setGroup(new Group(1, "name"));
            studentDao.add(student);
            verify(statementMock, times(1))
                .setInt(1, 1);
            verify(statementMock, times(1))
                .setString(2, "name");
            verify(statementMock, times(1))
                .setString(3, "lastName");
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
            Student studentMock = Mockito.mock(Student.class);
            Group groupMock = Mockito.mock(Group.class);
            List<Student> studentList = List.of(studentMock);
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class)))
                .thenReturn(statementMock);
            when(studentMock.getGroup()).thenReturn(groupMock);
            when(groupMock.getId()).thenReturn(1);
            when(studentMock.getFirstName()).thenReturn("firstName");
            when(studentMock.getLastName()).thenReturn("lastName");
            studentDao.addAll(studentList);
            verify(studentMock, times(1)).getGroup();
            verify(groupMock, times(1)).getId();
            verify(studentMock, times(1)).getFirstName();
            verify(studentMock, times(1)).getLastName();
            verify(statementMock, times(1))
                .setInt(1, 1);
            verify(statementMock, times(1))
                .setString(2, "firstName");
            verify(statementMock, times(1))
                .setString(3, "lastName");
            verify(statementMock, times(1))
                .addBatch();
            verify(statementMock, times(1)).executeBatch();
            verify(connectionMock, times(1)).setAutoCommit(false);
            verify(connectionMock, times(1)).commit();
            verifyNoMoreInteractions(statementMock);
            verifyNoMoreInteractions(studentMock);
            verifyNoMoreInteractions(groupMock);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void loadById() {
        try {
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class))).thenReturn(statementMock);
            when(statementMock.executeQuery()).thenReturn(resultSetMock);
            when(resultSetMock.next()).thenReturn(true);
            when(resultSetMock.getInt("student_id")).thenReturn(1);
            when(resultSetMock.getString("first_name")).thenReturn("name");
            when(resultSetMock.getString("last_name")).thenReturn("lastName");
            studentDao.loadById(1);
            verify(statementMock, times(1))
                .setInt(1, 1);
            verify(statementMock, times(1)).executeQuery();
            verify(resultSetMock, times(1)).next();
            verify(resultSetMock, times(1)).getInt("student_id");
            verify(resultSetMock, times(1)).getString("first_name");
            verify(resultSetMock, times(1)).getString("last_name");
            verifyNoMoreInteractions(resultSetMock);
            verifyNoMoreInteractions(statementMock);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void loadAll() {
        try{
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class)))
                .thenReturn(statementMock);
            when(statementMock.executeQuery()).thenReturn(resultSetMock);
            when(resultSetMock.next()).thenReturn(true).thenReturn(false);
            when(resultSetMock.getInt("student_id")).thenReturn(1);
            when(resultSetMock.getString("first_name")).thenReturn("name");
            when(resultSetMock.getString("last_name")).thenReturn("lastName");

            studentDao.loadAll();
            verify(statementMock, times(1))
                .executeQuery();
            verify(resultSetMock, times(2)).next();
            verify(resultSetMock, times(1)).getInt("student_id");
            verify(resultSetMock, times(1)).getString("first_name");
            verify(resultSetMock, times(1)).getString("last_name");
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
            studentDao.deleteById(1);
            verify(statementMock, times(1)).setInt(1, 1);
            verify(statementMock, times(1)).executeUpdate();
            verifyNoMoreInteractions(statementMock);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void getAllStudentsByCourseName() {
        try{
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class)))
                .thenReturn(statementMock);
            when(statementMock.executeQuery()).thenReturn(resultSetMock);
            when(resultSetMock.next()).thenReturn(true).thenReturn(false);
            when(resultSetMock.getInt("student_id")).thenReturn(1);
            studentDao.getAllStudentsByCourseName(new Course());
            verify(statementMock, times(1)).setInt(1, 0);
            verify(statementMock, times(1))
                .executeQuery();
            verify(resultSetMock, times(2)).next();
            verify(resultSetMock, times(1)).getInt("student_id");
            verifyNoMoreInteractions(resultSetMock);
            verifyNoMoreInteractions(statementMock);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
