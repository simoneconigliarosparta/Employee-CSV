package org.example.thread;

import org.example.employee.EmployeeDTO;
import org.example.database.EmployeesDAO;

import java.sql.Connection;
import java.util.List;

public class DatabaseThread extends Thread {

    private List<EmployeeDTO> employees;
    private EmployeesDAO employeesDAO;
    private Connection connection;

    public DatabaseThread(List<EmployeeDTO> employees, EmployeesDAO employeesDAO, Connection connection) {
        this.employees = employees;
        this.employeesDAO = employeesDAO;
        this.connection = connection;

    }

    public void insertDataIntoDb() {
        for (EmployeeDTO employee : employees) {
            employeesDAO.batchInsert(employee);
        }
        employeesDAO.executeBatchInsert();
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void run() {
        insertDataIntoDb();
    }
}
