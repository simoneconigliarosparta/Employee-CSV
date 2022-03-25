package org.example;

import org.example.database.ConnectionManager;
import org.example.database.EmployeesDAO;
import org.example.employee.EmployeeDTO;
import org.example.employee.EmployeeFilter;
import org.example.file_utils.FilePath;
import org.junit.jupiter.api.*;
import org.example.file_utils.FileReader;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class PerformanceSingleThreadTester {

    private static Connection connection;
    private static List<EmployeeDTO> filteredList = new ArrayList<>();

    @BeforeAll
    public static void loadData(){
        List<EmployeeDTO> fullList = FileReader.readFile(FilePath.EMPLOYEE_RECORD);
        filteredList = EmployeeFilter.removeDuplicates(fullList);
        connection = ConnectionManager.getConnection();
    }
    
    @Test
    @DisplayName("Check speed insertion single thread")
    void checkSpeedInsertionSingleThread() {

        EmployeesDAO employeesDAO = new EmployeesDAO(connection);

        long start = System.nanoTime();
        for(EmployeeDTO employee : filteredList){
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
