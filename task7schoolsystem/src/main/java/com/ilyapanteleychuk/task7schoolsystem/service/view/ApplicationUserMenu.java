package com.ilyapanteleychuk.task7schoolsystem.service.view;

import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import com.ilyapanteleychuk.task7schoolsystem.service.dao.CourseService;
import com.ilyapanteleychuk.task7schoolsystem.service.dao.GroupService;
import com.ilyapanteleychuk.task7schoolsystem.service.dao.StudentService;
import java.util.Scanner;


public class ApplicationUserMenu {

    private final CourseService courseService;
    private final GroupService groupService;
    private final StudentService studentService;

    public ApplicationUserMenu(CourseService courseService, GroupService groupService,
                               StudentService studentService) {
        this.courseService = courseService;
        this.groupService = groupService;
        this.studentService = studentService;
    }

    public void startApp() {
        Scanner scanner = new Scanner(System.in);
        do {
            printOptions();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    System.out.println("Write student firstname(in double quotes)");
                    String firstName = scanner.nextLine();
                    System.out.println("Write student secondName(in double quotes)");
                    String secondName = scanner.nextLine();
                    System.out.println("Would you like to assign group for student" +
                        " (1 - yes, 0 - no");
                    int groupChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (groupChoice == 1) {
                        System.out.println("Write group id");
                        int groupId = scanner.nextInt();
                        scanner.nextLine();
                        Group group = groupService.getGroupById(groupId);
                        Student student = new Student(group, firstName, secondName);
                        studentService.addNewStudent(student);
                        break;
                    } else {
                        Student student = new Student(null, firstName, secondName);
                        studentService.addNewStudent(student);
                    }
                }
                case 2: {
                    System.out.println("Determine amount of students");
                    int amount = scanner.nextInt();
                    System.out.println(groupService
                        .findAllGroupWithLessOrEqualsStudentCount(amount));
                    break;
                }
                case 3: {
                    System.out.println("Provide course name. Here is the list of them ->");
                    System.out.println(courseService.getAllCourses());
                    String courseName = scanner.nextLine();
                    System.out.println(studentService.getAllStudentsByCourseName(courseName));
                    break;
                }
                case 4: {
                    System.out.println("Determine student id");
                    int studentId = scanner.nextInt();
                    studentService.deleteStudentById(studentId);
                    break;
                }
                case 5: {
                    System.out.println("Provide id of student ");
                    int studentId = scanner.nextInt();
                    System.out.println("Courses of this student -> ");
                    System.out.println(courseService.getAllStudentCourses(studentId));
                    scanner.nextLine();
                    System.out.println("Provide id of course");
                    int courseId = scanner.nextInt();
                    courseService.removeStudentFromCourse(studentId, courseId);
                    break;
                }
                case 6: {
                    System.out.println(courseService.getAllCourses());
                    System.out.println("provide course id");
                    int courseId = scanner.nextInt();
                    System.out.println("provide id of student");
                    int studentId = scanner.nextInt();
                    courseService.addStudentToCourse(studentId, courseId);
                    break;
                }
                default: {
                    System.out.println("Wrong operation");
                    break;
                }
            }
        } while (true);
    }

    private void printOptions() {
        String greeting = "Choose option " + System.lineSeparator() +
            "1 - Add new student" + System.lineSeparator() +
            "2 - Find all groups with less or equals student count" + System.lineSeparator() +
            "3 - Find all students related to course with given name" + System.lineSeparator() +
            "4 - Delete student by STUDENT_ID" + System.lineSeparator() +
            "5 - Remove the student from one of his or her courses" + System.lineSeparator() +
            "6 - Add student to course";
        System.out.println(greeting);
    }
}
