package org.example.employee;

import org.example.employee.EmployeeDTO;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {

    private static List<EmployeeDTO> employees = new ArrayList<>();

    private static List<EmployeeDTO> corruptedData = new ArrayList<>();


    public static void addEmployee(EmployeeDTO employee) {

        if (isDataCorrupted(employee)) {
            corruptedData.add(employee);
        } else {
            employees.add(employee);
        }
    }

    public static List<EmployeeDTO> getEmployees() {
        return employees;
    }

    public static List<EmployeeDTO> getCorruptedData() {
        return corruptedData;
    }

    private static boolean isDataCorrupted(EmployeeDTO employee) {

        if (isIdAlreadyExisting(employee)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isIdAlreadyExisting(EmployeeDTO employee) {
        for (EmployeeDTO element : employees) {
            if (employee.getEmpID() == element.getEmpID()) {
                return true;
            }
        }
        return false;
    }
}

