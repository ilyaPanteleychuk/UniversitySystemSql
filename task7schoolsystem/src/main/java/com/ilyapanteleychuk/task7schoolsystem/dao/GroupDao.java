package com.ilyapanteleychuk.task7schoolsystem.dao;

import java.util.List;


public interface GroupDao {

    List<Integer> findAllGroupWithLessOrEqualsStudentCount(int count);

    int getGroupOfStudent(int studentId);
}
