package com.ilyapanteleychuk.task7schoolsystem.view;

import com.ilyapanteleychuk.task7schoolsystem.service.CourseService;
import com.ilyapanteleychuk.task7schoolsystem.service.GroupService;
import com.ilyapanteleychuk.task7schoolsystem.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.mockito.Mockito.*;


class ApplicationUserMenuTest {

    private final CourseService courseService = Mockito.mock(CourseService.class);
    private final GroupService groupService = Mockito.mock(GroupService.class);
    private final StudentService studentService = Mockito.mock(StudentService.class);
    private final ApplicationUserMenu userMenu = new ApplicationUserMenu(courseService,
        groupService, studentService);

    @Test
    void startMenu_shouldCallStudentServiceAddNewStudent_whenInputIs1And0() {
        String lineSeparator = System.getProperty("line.separator");
        String userInputPath = "1" + lineSeparator +
            "name" + lineSeparator + "lastName" + lineSeparator +
            lineSeparator + "0" + lineSeparator + lineSeparator + 'n';
        InputStream in = new ByteArrayInputStream(userInputPath.getBytes());
        System.setIn(in);
        userMenu.startMenu();
        verify(studentService, times(1)).addNewStudent("name", "lastName");
        verifyNoMoreInteractions(studentService);
    }

    @Test
    void startMenu_shouldCallStudentServiceAddNewStudentWithGroup_whenInputIs1And1() {
        String lineSeparator = System.getProperty("line.separator");
        String userInputPath = "1" + lineSeparator +
            "name" + lineSeparator + "lastName" + lineSeparator +
            lineSeparator + "1" + lineSeparator + lineSeparator + "2" +
            lineSeparator + lineSeparator + 'n';
        InputStream in = new ByteArrayInputStream(userInputPath.getBytes());
        System.setIn(in);
        userMenu.startMenu();
        verify(studentService, times(1))
            .addNewStudentWithGroup("name", "lastName", 2);
        verifyNoMoreInteractions(studentService);
    }

    @Test
    void startMenu_shouldCallGroupServiceFindAllGroupsWithStudentCount_whenInputIs2() {
        String lineSeparator = System.getProperty("line.separator");
        String userInputPath = "2" + lineSeparator + "15" + lineSeparator + 'n';
        InputStream in = new ByteArrayInputStream(userInputPath.getBytes());
        System.setIn(in);
        userMenu.startMenu();
        verify(groupService, times(1))
            .findAllGroupWithLessOrEqualsStudentCount(15);
        verifyNoMoreInteractions(groupService);
    }

    @Test
    void startMenu_shouldCallCourseServiceAndStudentService_whenInputIs3() {
        String lineSeparator = System.getProperty("line.separator");
        String userInputPath = "3" + lineSeparator + "Math" + lineSeparator + 'n';
        InputStream in = new ByteArrayInputStream(userInputPath.getBytes());
        System.setIn(in);
        userMenu.startMenu();
        verify(courseService, times(1))
            .getAllCourses();
        verify(studentService, times(1))
            .getAllStudentsByCourseName("Math");
        verifyNoMoreInteractions(courseService);
        verifyNoMoreInteractions(studentService);
    }

    @Test
    void startMenu_shouldCallStudentServiceDeleteStudentById_whenInputIs4() {
        String lineSeparator = System.getProperty("line.separator");
        String userInputPath = "4" + lineSeparator +
            lineSeparator + "5" + lineSeparator + 'n';
        InputStream in = new ByteArrayInputStream(userInputPath.getBytes());
        System.setIn(in);
        userMenu.startMenu();
        verify(studentService, times(1)).deleteStudentById(5);
        verifyNoMoreInteractions(studentService);
    }

    @Test
    void startMenu_shouldCallCourseService_whenInputIs5() {
        String lineSeparator = System.getProperty("line.separator");
        String userInputPath = "5" + lineSeparator + "15"
            + lineSeparator + "1" + lineSeparator +'n';
        InputStream in = new ByteArrayInputStream(userInputPath.getBytes());
        System.setIn(in);
        userMenu.startMenu();
        verify(courseService, times(1))
            .getAllStudentCourses(15);
        verify(courseService, times(1))
            .removeStudentFromCourse(15, 1);
        verifyNoMoreInteractions(courseService);
    }

    @Test
    void startMenu_shouldCallCourseService_whenInputIs6() {
        String lineSeparator = System.getProperty("line.separator");
        String userInputPath = "6" + lineSeparator + "5"
            + lineSeparator + "1" + lineSeparator + 'n';
        InputStream in = new ByteArrayInputStream(userInputPath.getBytes());
        System.setIn(in);
        userMenu.startMenu();
        verify(courseService, times(1))
            .getAllCourses();
        verify(courseService, times(1))
            .addStudentToCourse(1, 5);
        verifyNoMoreInteractions(courseService);
    }
}
