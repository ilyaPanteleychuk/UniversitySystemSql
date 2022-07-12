package com.ilyapanteleychuk.task7schoolsystem.service;

import com.ilyapanteleychuk.task7schoolsystem.dao.CommonDao;
import com.ilyapanteleychuk.task7schoolsystem.dao.StudentDao;
import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;


class StudentServiceTest {

    private CommonDao<Student> commonStudentDao;
    private StudentDao studentDao;
    private CourseService courseService;
    private GroupService groupService;
    private StudentService studentService;

    @BeforeEach
    void init(){
        commonStudentDao = (CommonDao<Student>) Mockito.mock(CommonDao.class);
        studentDao = Mockito.mock(StudentDao.class);
        courseService = Mockito.mock(CourseService.class);
        groupService = Mockito.mock(GroupService.class);
        studentService = new StudentService(commonStudentDao, studentDao,
            courseService, groupService);
    }

    @Test
    void getStudentById() {
        Student studentMock = Mockito.mock(Student.class);
        Group groupMock = Mockito.mock(Group.class);
        when(groupService.getGroupOfStudent(any(Integer.class)))
            .thenReturn(1);
        when(commonStudentDao.loadById(1)).thenReturn(studentMock);
        when(groupService.getGroupById(1)).thenReturn(groupMock);
        studentService.getStudentById(1);
        verify(groupService, times(1))
            .getGroupOfStudent(1);
        verify(commonStudentDao, times(1)).loadById(1);
        verify(studentMock, times(1)).setGroup(groupMock);
        verify(groupService, times(1)).getGroupById(1);
        verifyNoMoreInteractions(studentMock);
        verifyNoMoreInteractions(commonStudentDao);
        verifyNoMoreInteractions(groupMock);
    }

    @Test
    void loadAllStudents() {
        Student studentMock = Mockito.mock(Student.class);
        Group groupMock = Mockito.mock(Group.class);
        List<Student> studentsList = List.of(studentMock);
        when(commonStudentDao.loadAll()).thenReturn(studentsList);
        when(studentMock.getId()).thenReturn(1);
        when(groupService.getGroupOfStudent(1)).thenReturn(1);
        when(groupService.getGroupById(1)).thenReturn(groupMock);
        studentService.loadAllStudents();
        verify(commonStudentDao, times(1)).loadAll();
        verify(studentMock, times(1)).getId();
        verify(groupService, times(1)).getGroupById(1);
        verify(groupService, times(1)).getGroupOfStudent(1);
        verify(studentMock, times(1)).setGroup(groupMock);
        verifyNoMoreInteractions(commonStudentDao);
        verifyNoMoreInteractions(groupService);
        verifyNoMoreInteractions(studentMock);
    }

    @Test
    void deleteStudentById() {
        studentService.deleteStudentById(1);
        verify(commonStudentDao, times(1)).deleteById(1);
        verifyNoMoreInteractions(commonStudentDao);
    }

    @Test
    void addNewStudent() {
        studentService.addNewStudent("name", "lastName");
        verify(commonStudentDao, times(1))
            .add(new Student("name", "lastName"));
        verifyNoMoreInteractions(commonStudentDao);
    }

    @Test
    void addNewStudentWithGroup() {
        Student studentMock = Mockito.mock(Student.class);
        Group groupMock = Mockito.mock(Group.class);
        studentMock.setGroup(groupMock);
        when(groupService.getGroupById(1)).thenReturn(groupMock);
        studentService.addNewStudentWithGroup("name", "lastName", 1);
        verify(studentMock, times(1)).setGroup(groupMock);
        Student student = new Student("name", "lastName");
        student.setGroup(groupMock);
        verify(groupService, times(1)).getGroupById(1);
        verify(commonStudentDao, times(1)).add(student);
        verifyNoMoreInteractions(studentMock);
        verifyNoMoreInteractions(groupService);
        verifyNoMoreInteractions(commonStudentDao);
    }

    @Test
    public void addAllStudents(){
        studentService.addAllStudents(new ArrayList<>());
        verify(commonStudentDao, times(1)).addAll(new ArrayList<>());
        verifyNoMoreInteractions(commonStudentDao);
    }

    @Test
    void getAllStudentsByCourseName() {
        Course courseMock = Mockito.mock(Course.class);
        when(courseService.getCourseByName("name")).thenReturn(courseMock);
        studentService.getAllStudentsByCourseName("name");
        verify(courseService, times(1)).getCourseByName("name");
        verify(studentDao, times(1)).getAllStudentsByCourseName(courseMock);
        verifyNoMoreInteractions(courseService);
        verifyNoMoreInteractions(studentDao);
    }
}
