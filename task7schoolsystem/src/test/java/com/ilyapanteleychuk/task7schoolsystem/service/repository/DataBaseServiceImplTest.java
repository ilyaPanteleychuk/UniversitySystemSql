package com.ilyapanteleychuk.task7schoolsystem.service.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


class DataBaseServiceImplTest {

    private ColumnGenerator columnGeneratorMock;
    private DataBaseFacade dataBaseFacadeMock;
    private DataBaseService dataBaseService;

    @BeforeEach
    void init(){
        columnGeneratorMock = Mockito.mock(ColumnGenerator.class);
        dataBaseFacadeMock = Mockito.mock(DataBaseFacade.class);
        dataBaseService = new DataBaseServiceImpl(dataBaseFacadeMock, columnGeneratorMock);
    }
    @Test
    void fillTables() {
        when(columnGeneratorMock.generateRandomGroups()).thenReturn(new ArrayList<>());
        when(columnGeneratorMock.generateCourses()).thenReturn(new ArrayList<>());
        when(columnGeneratorMock.generateRandomStudents()).thenReturn(new ArrayList<>());
        dataBaseService.fillTables();
        verify(columnGeneratorMock, times(1)).generateCourses();
        verify(columnGeneratorMock, times(1)).generateRandomStudents();
        verify(columnGeneratorMock, times(1)).generateRandomGroups();
        verify(columnGeneratorMock, times(1)).setStudentsToGroup(new ArrayList<>(), new ArrayList<>());
        verify(columnGeneratorMock, times(1)).setCoursesToStudents(new ArrayList<>(), new ArrayList<>());
        verify(dataBaseFacadeMock, times(1)).fillCoursesTable(new ArrayList<>());
        verify(dataBaseFacadeMock, times(1)).fillGroupsTable(new ArrayList<>());
        verify(dataBaseFacadeMock, times(1)).fillStudentsTable(new ArrayList<>());
        verify(dataBaseFacadeMock, times(1)).fillJointStudentsCoursesTable(new ArrayList<>());
        verifyNoMoreInteractions(columnGeneratorMock);
        verifyNoMoreInteractions(dataBaseFacadeMock);
    }
}
