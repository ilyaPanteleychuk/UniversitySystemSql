package com.ilyapanteleychuk.task7schoolsystem.dao;

import com.ilyapanteleychuk.task7schoolsystem.dao.db.ConnectionProvider;
import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;


class DbInserterDaoTest {

    private ConnectionProvider providerMock;
    private Connection connectionMock;
    private PreparedStatement statementMock;
    private DbInserterDao dbInserterDao;

    @BeforeEach
    void init() {
        providerMock = Mockito.mock(ConnectionProvider.class);
        connectionMock = Mockito.mock(Connection.class);
        statementMock = Mockito.mock(PreparedStatement.class);
        dbInserterDao = new DbInserterDao(providerMock);
    }

    @Test
    void addAllStudents() {
        try {
            Student studentMock = Mockito.mock(Student.class);
            Group groupMock = Mockito.mock(Group.class);
            List<Student> studentList = List.of(studentMock);
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class))).thenReturn(statementMock);
            when(studentMock.getGroup()).thenReturn(groupMock);
            when(groupMock.getId()).thenReturn(1);
            when(studentMock.getFirstName()).thenReturn("firstName");
            when(studentMock.getLastName()).thenReturn("lastName");
            dbInserterDao.addAllStudents(studentList);
            verify(groupMock, times(1)).getId();
            verify(studentMock, times(1)).getGroup();
            verify(studentMock, times(1)).getFirstName();
            verify(studentMock, times(1)).getLastName();
            verify(statementMock, times(1)).setInt(1, 1);
            verify(statementMock, times(1)).setString(2, "firstName");
            verify(statementMock, times(1)).setString(3, "lastName");
            verify(statementMock, times(1)).executeUpdate();
            verifyNoMoreInteractions(statementMock);
            verifyNoMoreInteractions(statementMock);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void addAllGroups() {
        try {
            Group groupMock = Mockito.mock(Group.class);
            List<Group> groupList = List.of(groupMock);
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class))).thenReturn(statementMock);
            when(groupMock.getName()).thenReturn("name");
            when(groupMock.getId()).thenReturn(1);
            dbInserterDao.addAllGroups(groupList);
            verify(groupMock, times(1)).getId();
            verify(groupMock, times(1)).getName();
            verify(statementMock, times(1)).setInt(1, 1);
            verify(statementMock, times(1)).setString(1, "name");
            verify(statementMock, times(1)).executeUpdate();
            verifyNoMoreInteractions(groupMock);
            verifyNoMoreInteractions(statementMock);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void addAllCourses() {
        try {
            Course courseMock = Mockito.mock(Course.class);
            List<Course> courseList = List.of(courseMock);
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class))).thenReturn(statementMock);
            when(courseMock.getName()).thenReturn("name");
            when(courseMock.getDescription()).thenReturn("description");
            dbInserterDao.addAllCourses(courseList);
            verify(courseMock, times(1)).getName();
            verify(courseMock, times(1)).getDescription();
            verify(statementMock, times(1)).setString(1, "name");
            verify(statementMock, times(1)).setString(2, "description");
            verify(statementMock, times(1)).executeUpdate();
            verifyNoMoreInteractions(courseMock);
            verifyNoMoreInteractions(statementMock);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void addAllCoursesStudents() {
        try {
            Student studentMock = Mockito.mock(Student.class);
            List<Student> studentList = List.of(studentMock);
            Course courseMock = Mockito.mock(Course.class);
            List<Course> courseList = List.of(courseMock);
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class))).thenReturn(statementMock);
            when(studentMock.getCourses()).thenReturn(courseList);
            when(studentMock.getId()).thenReturn(1);
            when(courseMock.getId()).thenReturn(1);
            dbInserterDao.addAllCoursesStudents(studentList);
            verify(studentMock, times(1)).getCourses();
            verify(studentMock, times(1)).getId();
            verify(courseMock, times(1)).getId();
            verify(statementMock, times(1)).setInt(1, 1);
            verify(statementMock, times(1)).setInt(2, 1);
            verify(statementMock, times(1)).executeUpdate();
            verifyNoMoreInteractions(statementMock);
            verifyNoMoreInteractions(studentMock);
            verifyNoMoreInteractions(courseMock);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
