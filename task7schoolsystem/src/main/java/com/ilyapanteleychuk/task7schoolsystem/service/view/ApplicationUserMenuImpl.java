package com.ilyapanteleychuk.task7schoolsystem.service.view;

import com.ilyapanteleychuk.task7schoolsystem.dao.*;
import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import com.ilyapanteleychuk.task7schoolsystem.repository.*;
import com.ilyapanteleychuk.task7schoolsystem.service.dao.*;
import com.ilyapanteleychuk.task7schoolsystem.service.repository.DataBaseService;
import com.ilyapanteleychuk.task7schoolsystem.service.repository.DataBaseServiceImpl;
import java.util.List;
import java.util.Scanner;


public class ApplicationUserMenuImpl implements ApplicationUserMenu {

    private final String URL;
    private final String USERNAME;
    private final String PASSWORD;

    public ApplicationUserMenuImpl(String url, String username, String password) {
        URL = url;
        USERNAME = username;
        PASSWORD = password;
    }

    @Override
    public void prepareTables(){
        ConnectionProvider connectionProvider = new ConnectionProvider(URL, USERNAME, PASSWORD);
        DataBaseFacade dataBaseFacade = new DataBaseFacadeImpl(connectionProvider);
        ColumnGenerator columnGenerator = new ColumnGeneratorImpl();
        DataBaseService dataBaseService = new DataBaseServiceImpl(dataBaseFacade, columnGenerator);
        dataBaseService.fillTables();
    }

    public void startApp() {
        Scanner scanner = new Scanner(System.in);
        do {
            printOptions();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    addNewStudent(scanner);
                    break;
                }
                case 2: {
                    findAllGroupsWithLessOrEqualsStudentCount(scanner);
                    break;
                }
                case 3: {
                    findAllStudentsByCourseName(scanner);
                    break;
                }
                case 4: {
                    deleteStudentById(scanner);
                    break;
                }
                case 5: {
                    removeStudentFromCourse(scanner);
                    break;
                }
                case 6:{
                    addStudentToCourse(scanner);
                    break;
                }
                default: {
                    System.out.println("Wrong operation");
                    break;
                }
            }
        }while (true);
    }

    private void printOptions(){
        String greeting = "Choose option " + System.lineSeparator() +
            "1 - Add new student" + System.lineSeparator() +
            "2 - Find all groups with less or equals student count" + System.lineSeparator() +
            "3 - Find all students related to course with given name" + System.lineSeparator() +
            "4 - Delete student by STUDENT_ID" + System.lineSeparator() +
            "5 - Remove the student from one of his or her courses" + System.lineSeparator() +
            "6 - Add student to course";
        System.out.println(greeting);
    }


    private void addNewStudent(Scanner scanner){
        ConnectionProvider provider = new ConnectionProvider(URL, USERNAME, PASSWORD);
        GroupDao groupDao = new GroupDaoImpl(provider);
        StudentDao studentDao = new StudentDaoImpl(groupDao, provider);
        CourseDao courseDao = new CourseDaoImpl(provider);
        StudentService studentService = new StudentServiceImpl(studentDao, courseDao);
        System.out.println("Write student firstname(in double quotes)");
        String firstName = scanner.nextLine();
        System.out.println("Write student secondName(in double quotes)");
        String secondName = scanner.nextLine();
        System.out.println("Would you like to assign group for student (1 - yes, 0 - no");
        int groupChoice = scanner.nextInt();
        scanner.nextLine();
        if (groupChoice == 1) {
            System.out.println("Write group id");
            int groupId = scanner.nextInt();
            scanner.nextLine();
            Group group = groupDao.getGroupById(groupId);
            Student student = new Student(group, firstName, secondName);
            studentService.addNewStudent(student);
            System.out.println("Added successfully");
        } else {
            Student student = new Student(null, firstName, secondName);
            studentService.addNewStudent(student);
        }
    }

    private void findAllGroupsWithLessOrEqualsStudentCount(Scanner scanner){
        ConnectionProvider provider = new ConnectionProvider(URL, USERNAME, PASSWORD);
        GroupDao groupDao = new GroupDaoImpl(provider);
        GroupService groupService = new GroupServiceImpl(groupDao);
        System.out.println("Determine amount of students");
        int amount = scanner.nextInt();
        List<Group> groups = groupService.findAllGroupWithLessOrEqualsStudentCount(amount);
        if(groups.isEmpty()){
            System.out.println("No such group with that amount (min 10, max 30)");
        }else{
            groups.forEach(System.out::println);
        }
    }

    private void findAllStudentsByCourseName(Scanner scanner){
        ConnectionProvider provider = new ConnectionProvider(URL, USERNAME, PASSWORD);
        StudentDao studentDao = new StudentDaoImpl(new GroupDaoImpl(provider), provider);
        CourseDao courseDao = new CourseDaoImpl(provider);
        CourseService courseService = new CourseServiceImpl(courseDao);
        StudentService studentService = new StudentServiceImpl(studentDao, courseDao);
        System.out.println("Provide course name. Here is the list of them ->");
        List<Course> courses = courseService.getAllCourses();
        courses.forEach(el -> System.out.println(el.getName()));
        scanner.nextLine();
        String courseName = scanner.nextLine();
        List<Student> students = studentService.getAllStudentsByCourseName(courseName);
        students.forEach(System.out::println);
        System.out.println("Done successfully");
    }

    private void deleteStudentById(Scanner scanner){
        ConnectionProvider provider = new ConnectionProvider(URL, USERNAME, PASSWORD);
        StudentDao studentDao = new StudentDaoImpl(new GroupDaoImpl(provider), provider);
        CourseDao courseDao = new CourseDaoImpl(provider);
        StudentService studentService = new StudentServiceImpl(studentDao, courseDao);
        System.out.println("Provide id of Student you want to delete");
        int studentId = scanner.nextInt();
        studentService.deleteStudentById(studentId);
        System.out.println("Deleted successfully");
    }

    private void removeStudentFromCourse(Scanner scanner){
        ConnectionProvider provider = new ConnectionProvider(URL, USERNAME, PASSWORD);
        CourseDao courseDao = new CourseDaoImpl(provider);
        CourseService courseService = new CourseServiceImpl(courseDao);
        System.out.println("Provide id of student ");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Provide name of course");
        String courseName = scanner.nextLine();
        courseService.removeStudentFromCourse(studentId, courseName);
        System.out.println("Removed successfully");
    }

    private void addStudentToCourse(Scanner scanner){
        ConnectionProvider provider = new ConnectionProvider(URL, USERNAME, PASSWORD);
        CourseDao courseDao = new CourseDaoImpl(provider);
        CourseService courseService = new CourseServiceImpl(courseDao);
        List<Course> courses = courseService.getAllCourses();
        courses.forEach(System.out::println);
        System.out.println("provide course id");
        int courseId = scanner.nextInt();
        System.out.println("provide id of student");
        int studentId = scanner.nextInt();
        courseService.addStudentToCourse(studentId, courseId);
        System.out.println("Added successfully");
    }
}
