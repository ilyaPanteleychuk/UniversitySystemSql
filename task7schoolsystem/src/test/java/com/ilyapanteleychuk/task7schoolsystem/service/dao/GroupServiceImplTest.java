package com.ilyapanteleychuk.task7schoolsystem.service.dao;

import com.ilyapanteleychuk.task7schoolsystem.dao.GroupDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;


class GroupServiceImplTest {

    private GroupDao groupDaoMock;
    private GroupService groupService;

    @BeforeEach
    void init(){
        groupDaoMock = Mockito.mock(GroupDao.class);
        groupService = new GroupServiceImpl(groupDaoMock);
    }
    @Test
    void getGroupById_shouldCallMethodGetGroupByIdOneTime() {
        groupService.getGroupById(1);
        verify(groupDaoMock, times(1)).getGroupById(1);
        verifyNoMoreInteractions(groupDaoMock);
    }

    @Test
    void findAllGroupWithLessOrEqualsStudentCount() {
        groupService.findAllGroupWithLessOrEqualsStudentCount(15);
        verify(groupDaoMock, times(1))
            .findAllGroupWithLessOrEqualsStudentCount(15);
        verifyNoMoreInteractions(groupDaoMock);
    }
}
