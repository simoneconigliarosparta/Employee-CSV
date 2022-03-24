package start;

import org.example.EmployeeDTO;
import org.example.database.EmployeesDAO;

import java.util.List;

public class DatabaseThread extends Thread {

    private List<EmployeeDTO> employees;
    private EmployeesDAO employeesDAO;

    public DatabaseThread(List<EmployeeDTO> employees, EmployeesDAO employeesDAO) {
        this.employees = employees;
        this.employeesDAO = employeesDAO;
    }

    public void insertDataIntoDb() {
        for (EmployeeDTO employee : employees) {
            employeesDAO.insert(employee);

        }
    }

    @Override
    public void run() {
        insertDataIntoDb();
    }
}
