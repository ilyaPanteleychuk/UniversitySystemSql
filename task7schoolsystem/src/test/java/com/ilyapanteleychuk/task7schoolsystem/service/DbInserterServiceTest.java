package com.ilyapanteleychuk.task7schoolsystem.service;

import com.ilyapanteleychuk.task7schoolsystem.dao.CommonDao;
import com.ilyapanteleychuk.task7schoolsystem.dao.StudentDao;
import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;


class DbInserterServiceTest {

    private final CommonDao<Course> courseCommonDaoMock =
        (CommonDao<Course>) Mockito.mock(CommonDao.class);
    private final CommonDao<Group> groupCommonDaoMock =
        (CommonDao<Group>) Mockito.mock(CommonDao.class);
    private final CommonDao<Student> studentCommonDao =
        (CommonDao<Student>) Mockito.mock(CommonDao.class);
    private final StudentDao studentDaoMock = Mockito.mock(StudentDao.class);
    private final RandomInitialDataGenerator dataGeneratorMock =
        Mockito.mock(RandomInitialDataGenerator.class);
    private final DbInserterService dbInserterService =
        new DbInserterService(courseCommonDaoMock, groupCommonDaoMock,
            studentCommonDao, studentDaoMock, dataGeneratorMock);

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
        verify(courseCommonDaoMock, times(1))
            .addAll(new ArrayList<>());
        verify(groupCommonDaoMock, times(1))
            .addAll(new ArrayList<>());
        verify(studentCommonDao, times(1))
            .addAll(new ArrayList<>());
        verify(studentDaoMock, times(1))
            .addAllCoursesStudents(new ArrayList<>());
        verifyNoMoreInteractions(courseCommonDaoMock);
        verifyNoMoreInteractions(groupCommonDaoMock);
        verifyNoMoreInteractions(studentCommonDao);
        verifyNoMoreInteractions(studentDaoMock);
        verifyNoMoreInteractions(dataGeneratorMock);
    }
}
