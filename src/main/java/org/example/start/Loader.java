package org.example.start;

import org.example.database.DatabaseConnection;
import org.example.display.DisplayManager;
import org.example.employee.EmployeeDTO;
import org.example.employee.EmployeeFilter;
import org.example.database.ConnectionManager;
import org.example.database.EmployeesDAO;
import org.example.file_utils.FileReader;
import org.example.file_utils.FilePath;
import org.example.thread.DatabaseThread;
import org.example.thread.ThreadManager;

import java.util.List;

public class Loader {
    public static void start() {

<<<<<<< HEAD
        setThread(200);
    }

    public static void setThread(int numOfThread) {
        final int EXTRA_CONNECTION = 10;

        DatabaseConnection databaseConnection = new DatabaseConnection(ConnectionManager.getConnection());

        if (numOfThread + EXTRA_CONNECTION > databaseConnection.getMaxConnection()) {
            databaseConnection.setMaxConnection(numOfThread + EXTRA_CONNECTION);
        }

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

=======
        List<EmployeeDTO> fullList = FileReader.readFile(FilePath.EMPLOYEE_RECORD);
        List<EmployeeDTO> filteredList = EmployeeFilter.removeDuplicates(fullList);
        List<DatabaseThread> runningThreads = ThreadManager.setThread(100, filteredList);
        DisplayManager.printResult(runningThreads);
    }
>>>>>>> 55bf39d743de75465e435edb064e20c3e0b9c235
}

