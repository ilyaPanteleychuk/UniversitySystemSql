package com.ilyapanteleychuk.task7schoolsystem.service.dao;

import com.ilyapanteleychuk.task7schoolsystem.dao.GroupDaoImpl;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import java.util.List;
import java.util.stream.Collectors;


public class GroupService {

    private final GroupDaoImpl groupDao;

    public GroupService(GroupDaoImpl groupDao) {
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
}
