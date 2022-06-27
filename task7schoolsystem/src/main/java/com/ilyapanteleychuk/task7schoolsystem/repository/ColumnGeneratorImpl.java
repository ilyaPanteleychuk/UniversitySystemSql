package com.ilyapanteleychuk.task7schoolsystem.repository;

import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import org.apache.commons.lang.RandomStringUtils;
import java.util.*;


public class ColumnGeneratorImpl implements ColumnGenerator {

    private static final Random random = new Random();

    @Override
    public List<Student> setCoursesToStudents(List<Student> students, List<Course> courses){
        int randomAmountOfCourses = random.nextInt(4);
        if(randomAmountOfCourses == 0){
            randomAmountOfCourses++;
        }
        for (Student student : students) {
            for (int k = 0; k < randomAmountOfCourses; k++) {
                Course course = courses.get(random.nextInt(courses.size()));
                if(!(student.getCourses().contains(course))) {
                    student.addCourse(course);
                }else {
                    k--;
                }
            }
            randomAmountOfCourses = random.nextInt(4);
            if(randomAmountOfCourses == 0){
                randomAmountOfCourses++;
            }
        }
        return students;
    }

    @Override
    public List<Student> setStudentsToGroup(List<Group> groups) {
        List<Student> students = new ArrayList<>();
        List<Group> copyOfGroups = new ArrayList<>(groups);
        List<Student> copyOfStudents = generateRandomStudents();
        for (int i = 0; i < 10; i++) {
            Group randomGroup = null;
            if(!copyOfGroups.isEmpty()) {
                randomGroup = copyOfGroups.get(random.nextInt(copyOfGroups.size()));
            }
            int amountOfStudents = random.nextInt(30 - 10) + 10;
            for (int k = 0; k <= amountOfStudents; k++) {
                if(!copyOfStudents.isEmpty()) {
                    Student student =
                        copyOfStudents.get(random.nextInt(copyOfStudents.size()));
                    student.setGroup(randomGroup);
                    copyOfStudents.remove(student);
                    students.add(student);
                }
            }
            copyOfGroups.remove(randomGroup);
        }
        return students;
    }

    @Override
    public List<Student> generateRandomStudents() {
        List<Student> students = new ArrayList<>();
        List<String> firstNames = Arrays.asList("Abdul", "Murat", "Henry",
            "Vasiliy", "Ricardo", "Federucho", "Adolf", "Benjamin", "Eva",
            "Elena", "Michail", "Roman", "Ilya", "Anastasia", "Alexey",
            "Alexandr", "Alena", "Rosa", "Dmitriy");
        List<String> lastNames = Arrays.asList("Panteleychuk", "Butevich",
            "Smith", "Doe", "Winchester", "Morningstar", "Evans", "Hemsword",
            "Depp", "Decker", "Rebbit", "Ivanov", "Sidorov", "Stark",
            "Barateon", "Greyjoy", "Targarian", "Kupina", "Samodelova",
            "Amber");
        for (int i = 0; i < 200; i++) {
            Student student = new Student(firstNames.get(random.nextInt(firstNames.size())),
                lastNames.get(random.nextInt(lastNames.size())));
            if (!(students.contains(student))) {
                students.add(student);
            }else{
                i--;
            }
        }
        for(int i = 0; i < 200; i++){
            students.get(i).setId(i + 1);
        }
        return students;
    }

    @Override
    public List<Group> generateRandomGroups() {
        Set<Group> groups = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            String groupName = RandomStringUtils.randomAlphabetic(2)
                .toLowerCase() + "-" + RandomStringUtils.randomNumeric(2);
            Group group = new Group(groupName);
            group.setId(i + 1);
            groups.add(group);
        }
        return new ArrayList<>(groups);
    }

    @Override
    public List<Course> generateCourses() {
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
        return courses;
    }
}
