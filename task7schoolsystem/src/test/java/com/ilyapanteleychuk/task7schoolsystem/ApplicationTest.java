package com.ilyapanteleychuk.task7schoolsystem;

import com.ilyapanteleychuk.task7schoolsystem.dao.db.ConnectionProvider;
import com.ilyapanteleychuk.task7schoolsystem.service.*;
import com.ilyapanteleychuk.task7schoolsystem.view.ApplicationUserMenu;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


class ApplicationTest {

    private final DbInserterService dbInserterServiceMock =
        Mockito.mock(DbInserterService.class);
    private final ApplicationUserMenu applicationUserMenuMock =
        Mockito.mock(ApplicationUserMenu.class);
    private final ConnectionProvider connectionProvider
        = Mockito.mock(ConnectionProvider.class);
    private final Application application =
        new Application(connectionProvider);

    @Test
    void startApp() {
        application.dbInserterService = dbInserterServiceMock;
        application.applicationUserMenu = applicationUserMenuMock;
        application.startApp();
        verify(dbInserterServiceMock, times(1)).fillTables();
        verify(applicationUserMenuMock, times(1)).startMenu();
        verifyNoMoreInteractions(dbInserterServiceMock);
    }
}
