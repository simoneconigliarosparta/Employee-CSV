package org.example.file_utils;

import org.example.employee.EmployeeDTO;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    public static List<EmployeeDTO> readFile(String fileName) {

        List<EmployeeDTO> employees = new ArrayList<>();

        try {
            java.io.FileReader fileReader = new java.io.FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            String line = bufferedReader.readLine();

            while (line != null) {
                String[] employeeData = line.split(",");
                EmployeeDTO employeeDTO = new EmployeeDTO(employeeData);
                employees.add(employeeDTO);
                line = bufferedReader.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees;
    }
}
