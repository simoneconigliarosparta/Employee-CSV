package org.example.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeLogger {

    private static Logger logger = Logger.getLogger("EmployeeLogger");

    public Logger getLogger() {
        return logger;
    }

    public void setupLogger() {

        try {
            Handler fileHandler = new FileHandler("src/main/java/org/example/logger/employee-csv.log");
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(new CustomFormatter());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
