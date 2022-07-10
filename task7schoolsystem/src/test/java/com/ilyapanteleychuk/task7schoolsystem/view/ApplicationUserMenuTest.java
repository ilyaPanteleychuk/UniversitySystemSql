package com.ilyapanteleychuk.task7schoolsystem.view;

import com.ilyapanteleychuk.task7schoolsystem.service.CourseService;
import com.ilyapanteleychuk.task7schoolsystem.service.GroupService;
import com.ilyapanteleychuk.task7schoolsystem.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


class ApplicationUserMenuTest {

    private CourseService courseService = Mockito.mock(CourseService.class);
    private GroupService groupService = Mockito.mock(GroupService.class);
    private StudentService studentService = Mockito.mock(StudentService.class);
    private ApplicationUserMenu userMenu = new ApplicationUserMenu(courseService,
        groupService, studentService);

    @Test
    void startApp() {

    }
}
