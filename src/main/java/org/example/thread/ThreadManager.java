package org.example.thread;

import org.example.database.ConnectionManager;
import org.example.database.DatabaseConnection;
import org.example.database.EmployeesDAO;
import org.example.employee.EmployeeDTO;
import org.example.logger.EmployeeLogger;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ThreadManager {

    public static List<DatabaseThread> setThread(int numThreads, List<EmployeeDTO> employees) {
        EmployeeLogger logger = new EmployeeLogger();
        logger.setupLogger();
        logger.getLogger().log(Level.INFO, "Setting up threads...");

        final int EXTRA_CONNECTION = 10;

        DatabaseConnection databaseConnection = new DatabaseConnection(ConnectionManager.getConnection());

        if (numThreads + EXTRA_CONNECTION > databaseConnection.getMaxConnection()) {
            databaseConnection.setMaxConnection(numThreads + EXTRA_CONNECTION);
        }

        List<Connection> connections = new ArrayList<>();
        List<EmployeesDAO> employeesDAOS = new ArrayList<>();
        List<DatabaseThread> threads = new ArrayList<>();

        logger.getLogger().log(Level.INFO, "Creating connection and sub list of employees for each thread");

        for (int i = 0; i < numThreads; i++) {
            connections.add(ConnectionManager.getConnection());
            employeesDAOS.add(new EmployeesDAO(connections.get(i)));
            List<EmployeeDTO> list = employees.subList(i * employees.size() / numThreads, (1 + i) * employees.size() / numThreads);
            threads.add(new DatabaseThread(list, employeesDAOS.get(i), connections.get(i)));
        }
        logger.getLogger().log(Level.INFO, "Returning list of " + numThreads + " threads ready to be run");
        logger.closeHandler();
        return threads;
    }

}
