package com.ilyapanteleychuk.task7schoolsystem.service;

import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class RandomInitialDataGeneratorTest {

    private RandomInitialDataGenerator randomGenerator;

    @BeforeEach
    void init(){
        randomGenerator = new RandomInitialDataGenerator();
    }

    @Test
    void assignStudentsToCourses_shouldReturnStudentsWithNoCoursesDuplicate() {
        List<Student> students = randomGenerator.generateRandomStudents();
        List<Course> courses = randomGenerator.generateCourses();
        students = randomGenerator.assignStudentsToCourses(students, courses);
        students = students.stream()
            .filter(el -> el.getCourses().size() > 1)
            .collect(Collectors.toList());
        List<Course> studentCourses = students.get(0).getCourses();
        Set<Course> uniqueCourses = new HashSet<>(studentCourses);
        boolean result = uniqueCourses.size() == studentCourses.size();
        assertTrue(result);
    }

    @Test
    void setCoursesToStudents_shouldReturnStudentsWithLessThen4Courses() {
        List<Student> students = randomGenerator.generateRandomStudents();
        List<Course> courses = randomGenerator.generateCourses();
        students = randomGenerator.assignStudentsToCourses(students, courses);
        List<Student> studentWithCourse = students.stream()
            .filter(el -> el.getCourses().size() > 3)
            .collect(Collectors.toList());
        boolean result = studentWithCourse.isEmpty();
        assertTrue(result);
    }

    @Test
    void setCoursesToStudents_shouldReturnStudentsWithAtLeast1Course() {
        List<Student> students = randomGenerator.generateRandomStudents();
        List<Course> courses = randomGenerator.generateCourses();
        students = randomGenerator.assignStudentsToCourses(students, courses);
        List<Student> studentWithCourse = students.stream()
            .filter(el -> el.getCourses().size() > 0)
            .collect(Collectors.toList());
        boolean result = !studentWithCourse.isEmpty();
        assertTrue(result);
    }

    @Test
    void setStudentsToGroup_shouldReturnStudentsWhereOneGroupHasAtLeast10Student() {
        List<Student> students = randomGenerator.generateRandomStudents();
        List<Group> groups = randomGenerator.generateRandomGroups();
        students = randomGenerator.assignStudentsToGroups(students, groups);
        List<Student> studentsWithGroup = students.stream()
            .filter(el -> el.getGroup().getName().equals(groups.get(0).getName()))
            .collect(Collectors.toList());
        boolean result = studentsWithGroup.size() >= 10;
        assertTrue(result);
    }


    @Test
    void generateRandomStudents_shouldCreateUniqueStudent() {
        List<Student> students = randomGenerator.generateRandomStudents();
        Set<Student> uniqueStudents = new HashSet<>(students);
        boolean result = students.size() == uniqueStudents.size();
        assertTrue(result);
    }

    @Test
    void generateRandomStudents_shouldCreate200RandomStudent() {
        assertEquals(200, randomGenerator.generateRandomStudents().size());
    }

    @Test
    void generateRandomGroups_shouldGenerate10Groups() {
        List<Group> groups = randomGenerator.generateRandomGroups();
        assertEquals(10, groups.size());
    }

    @Test
    void generateRandomGroups_shouldGenerateGroupsNameByStringPattern() {
        String pattern =("\\D{2}-\\d{2}");
        List<Group> groups = randomGenerator.generateRandomGroups();
        boolean result = groups.get(0).getName().matches(pattern);
        assertTrue(result);
    }

    @Test
    void generateCourses_shouldReturnRightArrayList() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course(1,"Math", "MathStudying"));
        courses.add(new Course(2,"Biology", "BiologyStudying"));
        courses.add(new Course(3,"PE", "Physical Culture"));
        courses.add(new Course(4, "IT", "Information Technology"));
        courses.add(new Course(5, "Chemistry", "ChemistryStudying"));
        courses.add(new Course(6, "Literature", "Native Literature"));
        courses.add(new Course(7,"Foreign Literature", "Foreign Literature"));
        courses.add(new Course(8,"Law", "Law studying"));
        courses.add(new Course(9,"Physics", "PhysicsStudying"));
        courses.add(new Course(10,"Economics", "EconomicsStudying"));
        assertEquals(courses, randomGenerator.generateCourses());
    }


}
