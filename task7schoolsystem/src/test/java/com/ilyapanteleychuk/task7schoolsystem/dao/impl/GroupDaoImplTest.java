package com.ilyapanteleychuk.task7schoolsystem.dao.impl;

import com.ilyapanteleychuk.task7schoolsystem.dao.GroupDao;
import com.ilyapanteleychuk.task7schoolsystem.dao.db.ConnectionProvider;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
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


class GroupDaoImplTest {

    private ConnectionProvider providerMock;
    private Connection connectionMock;
    private PreparedStatement statementMock;
    private ResultSet resultSetMock;
    private GroupDaoImpl groupDao;

    @BeforeEach
    void init(){
        providerMock = Mockito.mock(ConnectionProvider.class);
        connectionMock = Mockito.mock(Connection.class);
        statementMock = Mockito.mock(PreparedStatement.class);
        resultSetMock = Mockito.mock(ResultSet.class);
        groupDao = new GroupDaoImpl(providerMock);
    }

    @Test
    void add() {
        try {
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class)))
                .thenReturn(statementMock);
            groupDao.add(new Group(1, "Name"));
            verify(statementMock, times(1))
                .setInt(1, 1);
            verify(statementMock, times(1))
                .setString(2, "Name");
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
            Group groupMock = Mockito.mock(Group.class);
            List<Group> groupList = List.of(groupMock);
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class)))
                .thenReturn(statementMock);
            when(groupMock.getId()).thenReturn(1);
            when(groupMock.getName()).thenReturn("name");
            groupDao.addAll(groupList);
            verify(groupMock, times(1)).getId();
            verify(groupMock, times(1)).getName();
            verify(statementMock, times(1))
                .setInt(1, 1);
            verify(statementMock, times(1))
                .setString(2, "name");
            verify(statementMock, times(1))
                .addBatch();
            verify(statementMock, times(1)).executeBatch();
            verify(connectionMock, times(1)).setAutoCommit(false);
            verify(connectionMock, times(1)).commit();
            verifyNoMoreInteractions(statementMock);
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
            when(resultSetMock.getInt("group_id")).thenReturn(1);
            when(resultSetMock.getString("group_name")).thenReturn("name");
            groupDao.loadById(1);
            verify(statementMock, times(1))
                .setInt(1, 1);
            verify(statementMock, times(1)).executeQuery();
            verify(resultSetMock, times(1)).next();
            verify(resultSetMock, times(1)).getInt("group_id");
            verify(resultSetMock, times(1)).getString("group_name");
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
            when(resultSetMock.getInt("group_id")).thenReturn(1);
            when(resultSetMock.getString("group_name")).thenReturn("name");
            groupDao.loadAll();
            verify(statementMock, times(1))
                .executeQuery();
            verify(resultSetMock, times(1)).next();
            verify(resultSetMock, times(1)).getInt("group_id");
            verify(resultSetMock, times(1)).getString("group_name");
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
            groupDao.deleteById(1);
            verify(statementMock, times(1)).setInt(1, 1);
            verify(statementMock, times(1)).executeUpdate();
            verifyNoMoreInteractions(statementMock);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void getGroupOfStudent() {
        try{
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class)))
                .thenReturn(statementMock);
            when(statementMock.executeQuery()).thenReturn(resultSetMock);
            when(resultSetMock.next()).thenReturn(true);
            when(resultSetMock.getInt("student_id")).thenReturn(1);
            groupDao.getGroupOfStudent(1);
            verify(statementMock, times(1))
                .setInt(1, 1);
            verify(statementMock, times(1))
                .executeQuery();
            verify(resultSetMock, times(1)).next();
            verify(resultSetMock, times(1))
                .getInt("group_id");
            verifyNoMoreInteractions(resultSetMock);
            verifyNoMoreInteractions(statementMock);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void findAllGroupWithLessOrEqualsStudentCount() {
        try {
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class))).thenReturn(statementMock);
            when(statementMock.executeQuery()).thenReturn(resultSetMock);
            when(resultSetMock.next()).thenReturn(true).thenReturn(false);
            when(resultSetMock.getInt("ln")).thenReturn(1);
            GroupDao groupDao = new GroupDaoImpl(providerMock);
            groupDao.findAllGroupWithLessOrEqualsStudentCount(15);
            verify(statementMock, times(1))
                .setInt(1, 15);
            verify(statementMock, times(1)).executeQuery();
            verify(resultSetMock, times(2)).next();
            verify(resultSetMock, times(1)).getInt("ln");
            verifyNoMoreInteractions(resultSetMock);
            verifyNoMoreInteractions(statementMock);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
