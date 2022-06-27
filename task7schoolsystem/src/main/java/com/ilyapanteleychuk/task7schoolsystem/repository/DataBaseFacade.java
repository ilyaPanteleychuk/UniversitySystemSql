package com.ilyapanteleychuk.task7schoolsystem.repository;

public interface DataBaseFacade {

    void createTables(String url, String username, String pass, String changeLogPath);
}
