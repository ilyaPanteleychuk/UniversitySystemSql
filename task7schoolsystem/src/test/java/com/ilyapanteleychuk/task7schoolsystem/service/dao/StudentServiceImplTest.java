package com.ilyapanteleychuk.task7schoolsystem.service.dao;

import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


class StudentServiceImplTest {

    private StudentDao studentDaoMock;
    private CourseDao courseDaoMock;
    private StudentService studentService;

    @BeforeEach
    void init(){
        studentDaoMock = Mockito.mock(StudentDao.class);
        courseDaoMock = Mockito.mock(CourseDao.class);
        studentService = new StudentServiceImpl(studentDaoMock, courseDaoMock);
    }
    @Test
    void addNewStudent_shouldCallMethodAddStudentOneTime() {
        studentService.addNewStudent(new Student());
        verify(studentDaoMock).addNewStudent(new Student());
        verifyNoMoreInteractions(studentDaoMock);
    }

    @Test
    void getAllStudentsByCourseName_shouldCallgetAllStudentsByCourseOneTime() {
        when(courseDaoMock.getCourseByName(any(String.class)))
            .thenReturn(new Course());
        studentService.getAllStudentsByCourseName("Math");
        verify(courseDaoMock, times(1)).getCourseByName("Math");
        verify(studentDaoMock, times(1))
            .getAllStudentsByCourseName(new Course());
        verifyNoMoreInteractions(courseDaoMock);
        verifyNoMoreInteractions(studentDaoMock);
    }

    @Test
    void deleteStudentById_shouldCallStudentByIdOneTime() {
        studentService.deleteStudentById(1);
        verify(studentDaoMock, times(1))
            .deleteStudentById(1);
        verifyNoMoreInteractions(studentDaoMock);
    }

    @Test
    void getStudentById_shouldCallMethodGetStudentByIdOneTime() {
        studentService.getStudentById(1);
        verify(studentDaoMock, times(1)).getStudentById(1);
        verifyNoMoreInteractions(studentDaoMock);
    }
}
