package org.example.employee;

import org.example.file_utils.FilePath;
import org.example.file_utils.FileReader;

import java.util.ArrayList;
import java.util.List;

public class EmployeeFilter {

    private static List<EmployeeDTO> employees = new ArrayList<>();

    private static List<EmployeeDTO> duplicates = new ArrayList<>();


    public static List<EmployeeDTO> removeDuplicates(List<EmployeeDTO> listEmployees) {

        employees = new ArrayList<>(listEmployees);

        for (int i = 0; i < listEmployees.size(); i++) {
            for (int j = i + 1; j < listEmployees.size(); j++) {
                if (listEmployees.get(i).getEmpID() == listEmployees.get(j).getEmpID()) {
                    duplicates.add(listEmployees.get(i));
                    employees.remove(listEmployees.get(i));
                }
            }
        }
        return employees;
    }

    public static List<EmployeeDTO> getEmployees() {
        return employees;
    }

    public static List<EmployeeDTO> getDuplicates() {
        return duplicates;
    }

}

