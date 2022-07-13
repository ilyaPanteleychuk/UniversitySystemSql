package com.ilyapanteleychuk.task7schoolsystem.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesReader {

    private final Properties configProp = new Properties();

    public PropertiesReader(){
        InputStream inputStream = this.getClass().getClassLoader()
            .getResourceAsStream("application.properties");
        try {
            configProp.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key){
        return configProp.getProperty(key);
    }
}
