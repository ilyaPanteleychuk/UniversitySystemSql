package com.ilyapanteleychuk.task7schoolsystem.dao;

import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import java.util.List;


public interface GroupDao {

    Group getGroupById(int id);

    List<Integer> findAllGroupWithLessOrEqualsStudentCount(int count);

}
