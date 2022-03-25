package org.example.file_utils;

import org.example.employee.EmployeeDTO;
import org.example.logger.EmployeeLogger;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class FileReader {
    public static List<EmployeeDTO> readFile(String fileName) {
        EmployeeLogger logger = new EmployeeLogger();
        logger.setupLogger();

        List<EmployeeDTO> employees = new ArrayList<>();

        try {
            java.io.FileReader fileReader = new java.io.FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            String line = bufferedReader.readLine();

            logger.getLogger().log(Level.INFO, "Extracting employees from file...");
            while (line != null) {
                String[] employeeData = line.split(",");
                EmployeeDTO employeeDTO = new EmployeeDTO(employeeData);
                employees.add(employeeDTO);
                line = bufferedReader.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.getLogger().log(Level.WARNING, e.getMessage());
        }
        logger.getLogger().log(Level.INFO, "Returning list of " + employees.size() + " employees");
        logger.closeHandler();
        return employees;
    }
}
