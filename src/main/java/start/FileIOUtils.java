package start;

import org.example.EmployeeDTO;
import org.example.EmployeeManager;
import org.example.database.ConnectionManager;
import org.example.database.EmployeesDAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;

public class FileIOUtils {
    static void readFile(String fileName) {
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            String line = bufferedReader.readLine();

            EmployeeDTO employeeDTO;

            EmployeeManager employeeManager = new EmployeeManager();

            long start = System.nanoTime();

            while (line != null) {
                String[] employeeData = line.split(",");
                employeeDTO  = new EmployeeDTO(employeeData);
                employeeManager.addEmployee(employeeDTO);
                line = bufferedReader.readLine();
            }

            Connection connection = ConnectionManager.getConnection();
            EmployeesDAO employeesDAO = new EmployeesDAO(connection);
            for (int i = 0; i < employeeManager.getEmployees().size(); i ++) {
                employeesDAO.insert(employeeManager.getEmployees().get(i).getEmpID(),employeeManager.getEmployees().get(i).getNamePrefix(), employeeManager.getEmployees().get(i).getFirstName(), employeeManager.getEmployees().get(i).getMiddleInitial(),employeeManager.getEmployees().get(i).getLastName(), employeeManager.getEmployees().get(i).getGender(), employeeManager.getEmployees().get(i).getEmail(), employeeManager.getEmployees().get(i).getDob(), employeeManager.getEmployees().get(i).getDateOfJoining(), employeeManager.getEmployees().get(i).getSalary());
            }

            long end = System.nanoTime();

            System.out.println("Time taken: " + (end - start) + " nanoseconds");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
