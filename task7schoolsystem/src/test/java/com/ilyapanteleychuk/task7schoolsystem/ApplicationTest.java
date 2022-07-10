package com.ilyapanteleychuk.task7schoolsystem;

import com.ilyapanteleychuk.task7schoolsystem.service.*;
import com.ilyapanteleychuk.task7schoolsystem.view.ApplicationUserMenu;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;


class ApplicationTest {

    private final DbInserterService dbInserterService =
        Mockito.mock(DbInserterService.class);
    private final ApplicationUserMenu applicationUserMenu =
        Mockito.mock(ApplicationUserMenu.class);
    private final Application application =
        new Application(dbInserterService, applicationUserMenu);

    @Test
    void startApp() {
        application.startApp();
        verify(dbInserterService, times(1)).fillTables();
        verify(applicationUserMenu, times(1)).startMenu();
        verifyNoMoreInteractions(dbInserterService);
        verifyNoMoreInteractions(applicationUserMenu);
    }
}
