package com.ilyapanteleychuk.task7schoolsystem;

import com.ilyapanteleychuk.task7schoolsystem.service.DbInserterService;
import com.ilyapanteleychuk.task7schoolsystem.view.ApplicationUserMenu;


public class Application {

    private final DbInserterService dbInserterService;
    private final ApplicationUserMenu applicationUserMenu;

    public Application(DbInserterService dbInserterService, ApplicationUserMenu applicationUserMenu) {
        this.dbInserterService = dbInserterService;
        this.applicationUserMenu = applicationUserMenu;
    }

    public void startApp(){
        dbInserterService.fillTables();
        applicationUserMenu.startMenu();
    }
}
