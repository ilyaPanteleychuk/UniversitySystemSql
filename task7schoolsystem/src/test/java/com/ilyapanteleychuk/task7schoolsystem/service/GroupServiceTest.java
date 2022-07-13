package com.ilyapanteleychuk.task7schoolsystem.service;

import com.ilyapanteleychuk.task7schoolsystem.dao.CommonDao;
import com.ilyapanteleychuk.task7schoolsystem.dao.GroupDao;
import com.ilyapanteleychuk.task7schoolsystem.entity.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;


class GroupServiceTest {

    private CommonDao<Group> groupCommonDao;
    private GroupDao groupDao;
    private GroupService groupService;

    @BeforeEach
    void init(){
        groupCommonDao = (CommonDao<Group>) Mockito.mock(CommonDao.class);
        groupDao = Mockito.mock(GroupDao.class);
        groupService = new GroupService(groupCommonDao, groupDao);
    }
    @Test
    void addNewStudent() {
        groupService.addNewStudent(new Group());
        verify(groupCommonDao, times(1)).add(new Group());
        verifyNoMoreInteractions(groupCommonDao);
    }

    @Test
    void loadAllGroups() {
        groupService.loadAllGroups();
        verify(groupCommonDao, times(1)).loadAll();
        verifyNoMoreInteractions(groupCommonDao);
    }

    @Test
    void deleteGroupById() {
        groupService.deleteGroupById(1);
        verify(groupCommonDao, times(1)).deleteById(1);
        verifyNoMoreInteractions(groupCommonDao);
    }

    @Test
    void getGroupById() {
        groupService.getGroupById(1);
        verify(groupCommonDao, times(1)).loadById(1);
        verifyNoMoreInteractions(groupCommonDao);
    }

    @Test
    void findAllGroupWithLessOrEqualsStudentCount() {
        groupService.findAllGroupWithLessOrEqualsStudentCount(15);
        verify(groupDao, times(1))
            .findAllGroupWithLessOrEqualsStudentCount(15);
        verifyNoMoreInteractions(groupDao);
    }

    @Test
    void getGroupOfStudent() {
        groupService.getGroupOfStudent(1);
        verify(groupDao, times(1)).getGroupOfStudent(1);
        verifyNoMoreInteractions(groupDao);
    }
}
