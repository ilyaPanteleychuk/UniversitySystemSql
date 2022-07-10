package com.ilyapanteleychuk.task7schoolsystem.service;

import com.ilyapanteleychuk.task7schoolsystem.dao.CommonDao;
import com.ilyapanteleychuk.task7schoolsystem.dao.GroupDao;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;

import java.util.List;
import java.util.stream.Collectors;


public class GroupService {

    private final CommonDao<Group> groupCommonDao;
    private final GroupDao groupDao;

    public GroupService(CommonDao<Group> groupCommonDao, GroupDao groupDao) {
        this.groupCommonDao = groupCommonDao;
        this.groupDao = groupDao;
    }

    public void addNewStudent(Group group){
        groupCommonDao.add(group);
    }

    public List<Group> loadAllGroups(){
        return groupCommonDao.loadAll();
    }

    public void deleteGroupById(int id) {
        groupCommonDao.deleteById(id);
    }

    public Group getGroupById(int groupId){
        return groupCommonDao.loadById(groupId);
    }

    public List<Group> findAllGroupWithLessOrEqualsStudentCount(int count) {
        return groupDao.findAllGroupWithLessOrEqualsStudentCount(count)
            .stream().map(groupCommonDao::loadById)
            .collect(Collectors.toList());
    }

    public int getGroupOfStudent(int studentId){
        return groupDao.getGroupOfStudent(studentId);
    }
}
