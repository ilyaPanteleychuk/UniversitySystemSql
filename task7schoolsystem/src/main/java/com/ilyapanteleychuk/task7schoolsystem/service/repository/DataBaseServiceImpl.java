package com.ilyapanteleychuk.task7schoolsystem.service.repository;

import com.ilyapanteleychuk.task7schoolsystem.entity.Course;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import com.ilyapanteleychuk.task7schoolsystem.entity.Student;
import com.ilyapanteleychuk.task7schoolsystem.repository.ColumnGenerator;
import com.ilyapanteleychuk.task7schoolsystem.repository.DataBaseFacade;
import java.util.List;


public class DataBaseServiceImpl implements DataBaseService {

    private final DataBaseFacade dataBase;
    private final ColumnGenerator columnGenerator;

    public DataBaseServiceImpl(DataBaseFacade dataBase, ColumnGenerator columnGenerator) {
        this.dataBase = dataBase;
        this.columnGenerator = columnGenerator;
    }

    @Override
    public void fillTables() {
        List<Group> groups = columnGenerator.generateRandomGroups();
        List<Student> students = columnGenerator.generateRandomStudents();
        students = columnGenerator.setStudentsToGroup(students, groups);
        List<Course> courses = columnGenerator.generateCourses();
        students = columnGenerator.setCoursesToStudents(students, courses);
        dataBase.fillCoursesTable(courses);
        dataBase.fillGroupsTable(groups);
        dataBase.fillStudentsTable(students);
        dataBase.fillJointStudentsCoursesTable(students);
    }
}
