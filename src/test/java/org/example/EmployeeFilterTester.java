package org.example;

import org.example.employee.EmployeeDTO;
import org.example.employee.EmployeeFilter;
import org.example.file_utils.FilePath;
import org.example.file_utils.FileReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EmployeeFilterTester {


    static List<EmployeeDTO> listEmployees= new ArrayList<>(0);

    @BeforeAll
    static void populateListEmployee() {
        listEmployees = FileReader.readFile(FilePath.EMPLOYEE_RECORD);
    }

    @Test
    @DisplayName("Should return filtered list of employees")
    void shouldReturnFilteredListOfEmployees() {
        List<EmployeeDTO> filteredList = EmployeeFilter.removeDuplicates(listEmployees);
        Assertions.assertTrue(listEmployees.size() > filteredList.size() && filteredList.size() > 0);
    }

    @Test
    @DisplayName("Should return duplicates")
    void shouldReturnDuplicates() {
        EmployeeFilter.removeDuplicates(listEmployees);
        List<EmployeeDTO> duplicates = EmployeeFilter.getDuplicates();
        Assertions.assertTrue(listEmployees.size() > duplicates.size() && duplicates.size() > 0);
    }


}
