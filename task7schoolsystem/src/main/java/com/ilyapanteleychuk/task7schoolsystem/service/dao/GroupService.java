package com.ilyapanteleychuk.task7schoolsystem.service.dao;

import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import java.util.List;


public interface GroupService {

    Group getGroupById(int id);

    List<Group> findAllGroupWithLessOrEqualsStudentCount(int count);
}
