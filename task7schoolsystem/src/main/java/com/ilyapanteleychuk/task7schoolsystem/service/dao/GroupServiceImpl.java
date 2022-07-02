package com.ilyapanteleychuk.task7schoolsystem.service.dao;

import com.ilyapanteleychuk.task7schoolsystem.dao.GroupDao;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import java.util.List;
import java.util.stream.Collectors;


public class GroupServiceImpl implements GroupService {

    private final GroupDao groupDao;

    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public Group getGroupById(int id) {
        return groupDao.getGroupById(id);
    }

    @Override
    public List<Group> findAllGroupWithLessOrEqualsStudentCount(int count) {
        return groupDao.findAllGroupWithLessOrEqualsStudentCount(count)
            .stream().map(groupDao::getGroupById)
            .collect(Collectors.toList());
    }
}
