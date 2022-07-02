package com.ilyapanteleychuk.task7schoolsystem;

import com.ilyapanteleychuk.task7schoolsystem.service.view.ApplicationUserMenu;
import com.ilyapanteleychuk.task7schoolsystem.service.view.ApplicationUserMenuImpl;


public class Main {

    private static final String url = "jdbc:postgresql://localhost/test_db";
    private static final String username = "postgres";
    private static final String password = "1234";

    public static void main(String[] args) {

        ApplicationUserMenu applicationUserMenu = new ApplicationUserMenuImpl(url, username, password);
        applicationUserMenu.prepareTables();
        applicationUserMenu.startApp();
    }
}
