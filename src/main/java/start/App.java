package start;

import org.example.EmployeeDTO;
import org.example.EmployeeManager;
import org.example.database.ConnectionManager;
import org.example.database.EmployeesDAO;

import java.sql.Connection;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Loader.start();


        Connection connection = ConnectionManager.getConnection();
        EmployeesDAO employeesDAO = new EmployeesDAO(connection);

        List<EmployeeDTO> employees = EmployeeManager.getEmployees();


        // divide list into sublists
        List<EmployeeDTO> list1 = employees.subList(0, 10000);
        List<EmployeeDTO> list2 = employees.subList(10001, 20000);
        List<EmployeeDTO> list3 = employees.subList(20001, 30000);
        List<EmployeeDTO> list4 = employees.subList(30001, 40000);
        List<EmployeeDTO> list5 = employees.subList(40001, 50000);
        List<EmployeeDTO> list6 = employees.subList(50001, employees.size() - 1);


        DatabaseThread thread1 = new DatabaseThread(list1, employeesDAO);
        DatabaseThread thread2 = new DatabaseThread(list2, employeesDAO);
        DatabaseThread thread3 = new DatabaseThread(list3, employeesDAO);

        thread1.start();
        thread2.start();
        thread3.start();


    }
}
