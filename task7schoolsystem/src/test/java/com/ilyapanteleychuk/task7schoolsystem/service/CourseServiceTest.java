package com.ilyapanteleychuk.task7schoolsystem.service;

import com.ilyapanteleychuk.task7schoolsystem.dao.CommonDao;
import com.ilyapanteleychuk.task7schoolsystem.dao.CourseDao;
import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;


class CourseServiceTest {

    private CommonDao<Course> courseCommonDao;
    private CourseDao courseDao;
    private CourseService courseService;

    @BeforeEach
    void init(){
        courseCommonDao = (CommonDao<Course>) Mockito.mock(CommonDao.class);
        courseDao = Mockito.mock(CourseDao.class);
        courseService = new CourseService(courseCommonDao, courseDao);
    }

    @Test
    void getAllCourses() {
        courseService.getAllCourses();
        verify(courseCommonDao, times(1)).loadAll();
        verifyNoMoreInteractions(courseCommonDao);
    }

    @Test
    void addStudentToCourse() {
        courseService.addStudentToCourse(1, 1);
        verify(courseDao, times(1))
            .addStudentToCourse(1, 1);
        verifyNoMoreInteractions(courseDao);
    }

    @Test
    void removeStudentFromCourse() {
        courseService.removeStudentFromCourse(1, 1);
        verify(courseDao, times(1))
            .removeStudentFromCourse(1, 1);
        verifyNoMoreInteractions(courseDao);
    }

    @Test
    void getAllStudentCourses() {
        courseService.getAllStudentCourses(1);
        when(courseCommonDao.loadById(any(Integer.class))).thenReturn(new Course());
        verify(courseDao, times(1))
            .getAllStudentCourses(1);
        verifyNoMoreInteractions(courseDao);
    }

    @Test
    void getCourseByName() {
        courseService.getCourseByName("name");
        verify(courseDao, times(1)).getCourseByName("name");
        verifyNoMoreInteractions(courseDao);
    }
}
