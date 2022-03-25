package org.example;

import org.example.database.ConnectionManager;
import org.example.database.EmployeesDAO;
import org.example.employee.EmployeeDTO;
import org.example.employee.EmployeeFilter;
import org.junit.jupiter.api.*;
import org.example.file_utils.FileReader;

import java.sql.Connection;

public class PerformanceTester {

    private static Connection connection;

    @BeforeAll
    public static void loadData(){
        FileReader.readFile("src/main/resources/EmployeeRecordsLarge.csv");
        connection = ConnectionManager.getConnection();
    }
    
    @Test
    @DisplayName("Check speed insertion single thread")
    void checkSpeedInsertionSingleThread() {

        EmployeesDAO employeesDAO = new EmployeesDAO(connection);

        long start = System.nanoTime();
        for(EmployeeDTO employee : EmployeeFilter.getEmployees()){
            employeesDAO.insert(employee);
        }
        long end = System.nanoTime();
        long time = (end - start) / 1000000000;
        System.out.println("Time taken: " + time + " seconds");
    }

    @AfterAll
    public static void closeConnection(){
        ConnectionManager.closeConnection();
    }

    
    
}
