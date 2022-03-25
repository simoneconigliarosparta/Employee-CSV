package org.example.display;

import org.example.thread.DatabaseThread;

import java.sql.SQLException;
import java.util.List;

public class DisplayManager {
    public static void printResult(List<DatabaseThread> threads) {
        long start = System.nanoTime();

        for (DatabaseThread t: threads){
            t.start();
        }

        for (DatabaseThread t: threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                t.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        long end = System.nanoTime();
        long time = (end - start) / 1000000000;
        System.out.println("Time taken: " + time + " seconds");
    }
}
