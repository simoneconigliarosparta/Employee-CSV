package org.example.start;

import org.example.display.DisplayManager;
import org.example.employee.EmployeeDTO;
import org.example.employee.EmployeeManager;
import org.example.database.ConnectionManager;
import org.example.database.EmployeesDAO;
import org.example.fileIO.FileIOUtils;
import org.example.thread.DatabaseThread;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Loader {
    public static void start() {
        FileIOUtils.readFile("src/main/resources/EmployeeRecordsLarge.csv");

        setThread(100);
    }

    public static void setThread(int numOfThread) {
        List<Connection> connections = new ArrayList<>();
        List<EmployeesDAO> employeesDAOS = new ArrayList<>();
        List<EmployeeDTO> employees = EmployeeManager.getEmployees();
        List<DatabaseThread> threads = new ArrayList<>();

        for (int i = 0; i < numOfThread; i ++) {
            connections.add(ConnectionManager.getConnection());
            employeesDAOS.add(new EmployeesDAO(connections.get(i)));
            List<EmployeeDTO> list = employees.subList(i * employees.size() / numOfThread, (1 + i) * employees.size() / numOfThread);
            threads.add(new DatabaseThread(list, employeesDAOS.get(i)));
        }
        DisplayManager displayManager = new DisplayManager();
        displayManager.printResult(threads);

    }

}

