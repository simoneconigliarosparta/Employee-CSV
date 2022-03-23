package org.example.database;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

    public static Properties getProperties(){
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/main/resources/myJdbc.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
