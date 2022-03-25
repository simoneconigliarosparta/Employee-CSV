package org.example.employee;

import org.example.file_utils.FilePath;
import org.example.file_utils.FileReader;
import org.example.logger.EmployeeLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class EmployeeFilter {

    private static List<EmployeeDTO> employees = new ArrayList<>();

    private static List<EmployeeDTO> duplicates = new ArrayList<>();


    public static List<EmployeeDTO> removeDuplicates(List<EmployeeDTO> listEmployees) {
        EmployeeLogger logger = new EmployeeLogger();
        logger.setupLogger();
        logger.getLogger().log(Level.INFO, "Removing duplicates from list employees...");

        employees = new ArrayList<>(listEmployees);

        for (int i = 0; i < listEmployees.size(); i++) {
            for (int j = i + 1; j < listEmployees.size(); j++) {
                if (listEmployees.get(i).getEmpID() == listEmployees.get(j).getEmpID()) {
                    duplicates.add(listEmployees.get(i));
                    employees.remove(listEmployees.get(i));
                }
            }
        }
        logger.getLogger().log(Level.INFO, "Returning filtered list of " + employees.size() + " employees");
        return employees;
    }

    public static List<EmployeeDTO> getEmployees() {
        return employees;
    }

    public static List<EmployeeDTO> getDuplicates() {
        return duplicates;
    }

}

