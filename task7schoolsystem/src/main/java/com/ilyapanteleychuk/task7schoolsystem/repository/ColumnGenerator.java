package com.ilyapanteleychuk.task7schoolsystem.repository;

import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import java.util.List;


public interface ColumnGenerator {

    List<Student> setCoursesToStudents(List<Student> students, List<Course> courses);

    List<Student> setStudentsToGroup(List<Student> students, List<Group> groups);

    List<Student> generateRandomStudents();

    List<Group> generateRandomGroups();

    List<Course> generateCourses();
}
