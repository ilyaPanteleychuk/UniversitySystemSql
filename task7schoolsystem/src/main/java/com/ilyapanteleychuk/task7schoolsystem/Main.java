package com.ilyapanteleychuk.task7schoolsystem;

import com.ilyapanteleychuk.task7schoolsystem.dao.db.ConnectionProvider;
import com.ilyapanteleychuk.task7schoolsystem.service.PropertiesReader;


public class Main {
    public static void main(String[] args) {
        PropertiesReader propertiesReader = new PropertiesReader();
        String url = propertiesReader.getProperty("dbUrl");
        String username = propertiesReader.getProperty("username");
        String password = propertiesReader.getProperty("password");
        ConnectionProvider connectionProvider = new ConnectionProvider(url, username, password);
        Application application = new Application(connectionProvider);
        application.startApp();
    }
}
