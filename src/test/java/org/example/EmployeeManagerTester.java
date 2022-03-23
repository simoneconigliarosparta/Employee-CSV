package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import start.FileIOUtils;

import java.util.List;

public class EmployeeManagerTester {

    @BeforeAll
    public static void loadData(){
        FileIOUtils.readFile("src/main/resources/EmployeeRecords.csv");
    }

    @Test
    @DisplayName("Check list employees is populated")
    void checkListEmployeesIsPopulated() {
        List<EmployeeDTO> employees = EmployeeManager.getEmployees();
        Assertions.assertTrue(employees.size() > 0);
    }

    @Test
    @DisplayName("Check list corrupted data is populated")
    void checkListCorruptedDataIsPopulated() {
        List<EmployeeDTO> corruptedData = EmployeeManager.getCorruptedData();
        Assertions.assertTrue(corruptedData.size() > 0);
    }
}
