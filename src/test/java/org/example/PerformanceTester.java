package org.example;

import org.example.database.ConnectionManager;
import org.example.database.EmployeesDAO;
import org.junit.jupiter.api.*;
import start.FileIOUtils;

import java.sql.Connection;

public class PerformanceTester {

    private static Connection connection;

    @BeforeAll
    public static void loadData(){
        FileIOUtils.readFile("src/main/resources/EmployeeRecords.csv");
        connection = ConnectionManager.getConnection();
    }
    
    @Test
    @DisplayName("Check speed insertion single thread")
    void checkSpeedInsertionSingleThread() {

        EmployeesDAO employeesDAO = new EmployeesDAO(connection);

        long start = System.nanoTime();
        for(EmployeeDTO employee : EmployeeManager.getEmployees()){
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
