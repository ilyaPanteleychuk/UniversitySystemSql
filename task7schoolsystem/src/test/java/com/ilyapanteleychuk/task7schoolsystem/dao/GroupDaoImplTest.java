package com.ilyapanteleychuk.task7schoolsystem.dao;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


class GroupDaoImplTest {

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
    void getGroupById_shouldGetStudentWithRightId() {
        try {
            when(providerMock.getConnection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(any(String.class))).thenReturn(statementMock);
            when(statementMock.executeQuery()).thenReturn(resultSetMock);
            GroupDao groupDao = new GroupDaoImpl(providerMock);
            groupDao.getGroupById(1);
            verify(statementMock, times(1))
                .setInt(1, 1);
            verify(statementMock, times(1)).executeQuery();
            verify(resultSetMock, times(1)).next();
            verifyNoMoreInteractions(resultSetMock);
            verifyNoMoreInteractions(statementMock);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void findAllGroupWithLessOrEqualsStudentCount_shouldReturnGroupArrayList() {
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
