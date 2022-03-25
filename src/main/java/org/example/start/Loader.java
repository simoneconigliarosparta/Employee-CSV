package org.example.start;

import org.example.display.DisplayManager;
import org.example.employee.EmployeeDTO;
import org.example.employee.EmployeeManager;
import org.example.database.ConnectionManager;
import org.example.database.EmployeesDAO;
import org.example.fileIO.FileIOUtils;
import org.example.thread.DatabaseThread;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Loader {
    public static void start() {
        FileIOUtils.readFile("src/main/resources/EmployeeRecordsLarge.csv");

        setThread(1);
    }

    public static void setThread(int numOfThread) {
        List<Connection> connections = new ArrayList<>();
        List<EmployeesDAO> employeesDAOS = new ArrayList<>();
        List<EmployeeDTO> employees = EmployeeManager.getEmployees();
        List<DatabaseThread> threads = new ArrayList<>();

<<<<<<< HEAD:src/main/java/org/example/start/Loader.java
=======

        int numOfThread = 100;

>>>>>>> 3f424f24bdfab9bd4576e3bae8cd14562456cad3:src/main/java/start/App.java
        for (int i = 0; i < numOfThread; i ++) {
            connections.add(ConnectionManager.getConnection());
            employeesDAOS.add(new EmployeesDAO(connections.get(i)));
            List<EmployeeDTO> list = employees.subList(i * employees.size() / numOfThread, (1 + i) * employees.size() / numOfThread);
            threads.add(new DatabaseThread(list, employeesDAOS.get(i)));
        }

<<<<<<< HEAD:src/main/java/org/example/start/Loader.java
        DisplayManager displayManager = new DisplayManager();
        displayManager.printResult(threads);
=======
        long start = System.nanoTime();
        System.out.println("starting inserting data...");
        for (DatabaseThread t: threads){
            t.start();
        }

        for (DatabaseThread t: threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.nanoTime();
        long time = (end - start) / 1000000000;
        System.out.println("Time taken: " + time + " seconds");

>>>>>>> 3f424f24bdfab9bd4576e3bae8cd14562456cad3:src/main/java/start/App.java
    }

}

