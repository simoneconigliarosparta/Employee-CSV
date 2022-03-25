package org.example.display;

import org.example.logger.EmployeeLogger;
import org.example.thread.DatabaseThread;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

public class DisplayManager {
    public static void printResult(List<DatabaseThread> threads) {
        EmployeeLogger logger = new EmployeeLogger();
        logger.setupLogger();

        long start = System.nanoTime();

        logger.getLogger().log(Level.INFO, "Running threads and inserting data into database...");
        for (DatabaseThread t : threads) {
            t.start();
        }

        for (DatabaseThread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                logger.getLogger().log(Level.WARNING, e.getMessage());
                e.printStackTrace();
            }

            try {
                t.getConnection().close();
            } catch (SQLException e) {
                logger.getLogger().log(Level.WARNING, e.getMessage());
                e.printStackTrace();
            }
        }

        long end = System.nanoTime();
        long time = (end - start) / 1000000000;
        logger.getLogger().log(Level.INFO, "Employees inserted into database in " + time + " seconds");
        logger.closeHandler();
        System.out.println("Time taken: " + time + " seconds");

    }
}
