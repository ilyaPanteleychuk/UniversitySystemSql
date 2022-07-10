package com.ilyapanteleychuk.task7schoolsystem.service;

import com.ilyapanteleychuk.task7schoolsystem.dao.DbInserterDao;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;


class DbInserterServiceTest {

    private final DbInserterDao dbInserterDaoMock = Mockito.mock(DbInserterDao.class);
    private final RandomInitialDataGenerator dataGeneratorMock =
        Mockito.mock(RandomInitialDataGenerator.class);
    private final DbInserterService dbInserterService =
        new DbInserterService(dbInserterDaoMock, dataGeneratorMock);

    @Test
    void fillTables() {
        dbInserterService.fillTables();
        verify(dataGeneratorMock, times(1))
            .generateCourses();
        verify(dataGeneratorMock, times(1))
            .generateRandomGroups();
        verify(dataGeneratorMock, times(1))
            .generateRandomStudents();
        verify(dataGeneratorMock, times(1))
            .assignStudentsToCourses(new ArrayList<>(), new ArrayList<>());
        verify(dataGeneratorMock, times(1))
            .assignStudentsToGroups(new ArrayList<>(), new ArrayList<>());
        verify(dbInserterDaoMock, times(1))
            .addAllCourses(new ArrayList<>());
        verify(dbInserterDaoMock, times(1))
            .addAllGroups(new ArrayList<>());
        verify(dbInserterDaoMock, times(1))
            .addAllStudents(new ArrayList<>());
        verify(dbInserterDaoMock, times(1))
            .addAllCoursesStudents(new ArrayList<>());
        verifyNoMoreInteractions(dbInserterDaoMock);
        verifyNoMoreInteractions(dataGeneratorMock);
    }
}
