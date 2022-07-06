package com.ilyapanteleychuk.task7schoolsystem.repository;

import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;


class DbInserterTest {

    private ConnectionProvider providerMock;
    private Connection connectionMock;
    private PreparedStatement statementMock;

    @BeforeEach
    void init(){
        providerMock = Mockito.mock(ConnectionProvider.class);
        connectionMock = Mockito.mock(Connection.class);
        statementMock = Mockito.mock(PreparedStatement.class);
    }

    @Test
    void fillStudentsTable_shouldWorkCorrectly_whenInputIsArrayList() {
        try {
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class))).thenReturn(statementMock);
            DataBaseFacade dataBaseFacade = new DbInserter(providerMock);
            List<Student> studentList = new ArrayList<>();
            studentList.add(new Student(new Group(2, "xx-12"),
                "Jake", "Sally"));
            dataBaseFacade.fillStudentsTable(studentList);
            verify(statementMock, times(1))
                .setInt(1, studentList.get(0).getGroup().getId());
            verify(statementMock, times(1))
                .setString(2, studentList.get(0).getFirstName());
            verify(statementMock, times(1))
                .setString(3, studentList.get(0).getLastName());
            verify(statementMock, times(1)).executeUpdate();
            verifyNoMoreInteractions(statementMock);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void fillGroupsTable_shouldWorkCorrectly_whenInputIsArrayList() {
        try {
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class))).thenReturn(statementMock);
            DataBaseFacade dataBaseFacade = new DbInserter(providerMock);
            List<Group> groups = new ArrayList<>();
            groups.add(new Group(1, "xx-00"));
            dataBaseFacade.fillGroupsTable(groups);
            verify(statementMock, times(1))
                .setInt(1, groups.get(0).getId());
            verify(statementMock, times(1))
                .setString(1, groups.get(0).getName());
            verify(statementMock, times(1)).executeUpdate();
            verifyNoMoreInteractions(statementMock);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void fillCoursesTable() {
        try {
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class)))
                .thenReturn(statementMock);
            DataBaseFacade dataBaseFacade = new DbInserter(providerMock);
            List<Course> courses = new ArrayList<>();
            Course course = new Course("Math", "MathStudying");
            courses.add(course);
            dataBaseFacade.fillCoursesTable(courses);
            verify(statementMock, times(1))
                .setString(1, courses.get(0).getName());
            verify(statementMock, times(1))
                .setString(2, courses.get(0).getDescription());
            verify(statementMock, times(1)).executeUpdate();
            verifyNoMoreInteractions(statementMock);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void fillJointStudentsCoursesTable() {
        try {
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class)))
                .thenReturn(statementMock);
            DataBaseFacade dataBaseFacade = new DbInserter(providerMock);
            List<Student> students = new ArrayList<>();
            students.add(new Student(1, new Group(1, "pp-12"),
                "John", "Doe"));
            students.get(0).addCourse(new Course(1, "Math", "MathStudying"));
            dataBaseFacade.fillJointStudentsCoursesTable(students);
            verify(statementMock, times(1))
                .setInt(1, students.get(0).getId());
            verify(statementMock, times(1))
                .setInt(2, students.get(0).getCourses().get(0).getId());
            verify(statementMock, times(1)).executeUpdate();
            verifyNoMoreInteractions(statementMock);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
