package com.ilyapanteleychuk.task7schoolsystem.service.dao;

import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


class CourseServiceImplTest {

    private CourseDao courseDaoMock;
    private CourseService courseService;
    @BeforeEach
    void init(){
        courseDaoMock = Mockito.mock(CourseDao.class);
        courseService = new CourseServiceImpl(courseDaoMock);
    }

    @Test
    void getAllCourses_shouldCallMethodGetAllCoursesOfCourseDaoClass() {
        courseService.getAllCourses();
        verify(courseDaoMock, times(1)).getAllCourses();
        verifyNoMoreInteractions(courseDaoMock);
    }

    @Test
    void getCourseByName() {
        courseService.getCourseByName("math");
        verify(courseDaoMock, times(1)).getCourseByName("math");
        verifyNoMoreInteractions(courseDaoMock);
    }

    @Test
    void addStudentToCourse() {
        courseService.addStudentToCourse(1, 1);
        verify(courseDaoMock, times(1))
            .addStudentToCourse(1, 1);
        verifyNoMoreInteractions(courseDaoMock);
    }

    @Test
    void removeStudentFromCourse() {
        when(courseDaoMock.getCourseByName(any(String.class))).thenReturn(new Course());
        courseService.removeStudentFromCourse(1, "Math");
        verify(courseDaoMock, times(1)).getCourseByName("Math");
        verify(courseDaoMock, times(1))
            .removeStudentFromCourse(1, new Course());
        verifyNoMoreInteractions(courseDaoMock);
    }

    @Test
    void getAllStudentCourses_shouldCallGetAllStudentCoursesOneTime() {
        when(courseDaoMock.getAllStudentCourses(1)).thenReturn(new ArrayList<>());
        courseService.getAllStudentCourses(1);
        verify(courseDaoMock, times(1))
            .getAllStudentCourses(1);
        verifyNoMoreInteractions(courseDaoMock);
    }

    @Test
    void getCourseById_shouldCallMethodGetCourseByIdOneTime() {
        courseService.getCourseById(1);
        verify(courseDaoMock, times(1))
            .getCourseById(1);
        verifyNoMoreInteractions(courseDaoMock);
    }
}
