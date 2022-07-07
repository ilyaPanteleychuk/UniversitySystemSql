package com.ilyapanteleychuk.task7schoolsystem.service;

import com.ilyapanteleychuk.task7schoolsystem.dao.GroupDao;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import java.util.List;
import java.util.stream.Collectors;


public class GroupService {

    private final GroupDao groupDao;

    public GroupService(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public List<Group> findAllGroupWithLessOrEqualsStudentCount(int count) {
        return groupDao.findAllGroupWithLessOrEqualsStudentCount(count)
            .stream().map(groupDao::getGroupById)
            .collect(Collectors.toList());
    }

    public Group getGroupById(int groupId){
        return groupDao.getGroupById(groupId);
    }

    public int getGroupOfStudent(int studentId){
        return groupDao.getGroupOfStudent(studentId);
    }
}
