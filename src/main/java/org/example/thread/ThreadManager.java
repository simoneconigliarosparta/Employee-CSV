package org.example.thread;

import org.example.database.ConnectionManager;
import org.example.database.EmployeesDAO;
import org.example.employee.EmployeeDTO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ThreadManager {

    public static List<DatabaseThread> setThread(int numThreads, List<EmployeeDTO> employees) {
        List<Connection> connections = new ArrayList<>();
        List<EmployeesDAO> employeesDAOS = new ArrayList<>();
        List<DatabaseThread> threads = new ArrayList<>();

        for (int i = 0; i < numThreads; i ++) {
            connections.add(ConnectionManager.getConnection());
            employeesDAOS.add(new EmployeesDAO(connections.get(i)));
            List<EmployeeDTO> list = employees.subList(i * employees.size() / numThreads, (1 + i) * employees.size() / numThreads);
            threads.add(new DatabaseThread(list, employeesDAOS.get(i)));
        }
        return threads;
    }

}