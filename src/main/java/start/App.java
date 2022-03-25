package start;

import org.example.EmployeeDTO;
import org.example.EmployeeManager;
import org.example.database.ConnectionManager;
import org.example.database.EmployeesDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Loader.start();

        List<Connection> connections = new ArrayList<>();
        List<EmployeesDAO> employeesDAOS = new ArrayList<>();
        List<EmployeeDTO> employees = EmployeeManager.getEmployees();
        List<DatabaseThread> threads = new ArrayList<>();


        int numOfThread = 100;

        for (int i = 0; i < numOfThread; i ++) {
            connections.add(ConnectionManager.getConnection());
            employeesDAOS.add(new EmployeesDAO(connections.get(i)));
            List<EmployeeDTO> list = employees.subList(i * employees.size() / numOfThread, (1 + i) * employees.size() / numOfThread);
            threads.add(new DatabaseThread(list, employeesDAOS.get(i)));
        }

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

    }
}
