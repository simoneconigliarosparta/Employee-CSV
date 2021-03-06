package org.example;

import org.example.employee.EmployeeDTO;
import org.example.file_utils.FilePath;
import org.example.file_utils.FileReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FileReaderTester {

    @Test
    @DisplayName("Check file exists")
    void checkFileExists() {
        Assertions.assertTrue(FileReader.readFile(FilePath.EMPLOYEE_RECORD) != null);
    }

    @Test
    @DisplayName("Check large file exists")
    void checkLargeFileExists() {
        Assertions.assertTrue(FileReader.readFile(FilePath.EMPLOYEE_RECORD_LARGE) != null);
    }

    @Test
    @DisplayName("Should return list of employees")
    void shouldReturnListOfEmployees() {
        int sizeList = 10000;
        List<EmployeeDTO> employees = FileReader.readFile(FilePath.EMPLOYEE_RECORD);
        Assertions.assertEquals(sizeList, employees.size());
    }

    @Test
    @DisplayName("Should return large list of employees")
    void shouldReturnLargeListOfEmployees() {
        int sizeList = 65499;
        List<EmployeeDTO> employees = FileReader.readFile(FilePath.EMPLOYEE_RECORD_LARGE);
        Assertions.assertEquals(sizeList, employees.size());
    }


}
