package org.example;

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

    private static boolean isDataCorrupted(EmployeeDTO employee) {

        if (isIdAlreadyExisting(employee)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isIdAlreadyExisting(EmployeeDTO employee) {
        for (EmployeeDTO element : employees) {
            if (employee.getEmpID().equals(element.getEmpID())) {
                return true;
            }
        }
        return false;
    }


}

