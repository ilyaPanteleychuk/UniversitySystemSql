package com.ilyapanteleychuk.task7schoolsystem;

import com.ilyapanteleychuk.task7schoolsystem.service.UniversityService;


public class Main {
    public static void main(String[] args) {
        UniversityService universityService = new UniversityService();
        universityService.startApp();
    }
}
