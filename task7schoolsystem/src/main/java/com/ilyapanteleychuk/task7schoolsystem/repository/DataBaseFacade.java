package com.ilyapanteleychuk.task7schoolsystem.repository;

import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import java.util.List;


public interface DataBaseFacade {

    void fillCoursesTable(List<Course> courses);

    void fillGroupsTable(List<Group> groups);

    void fillStudentsTable(List<Student> studentList);

    void fillJointStudentsCoursesTable(List<Student> students);
}
