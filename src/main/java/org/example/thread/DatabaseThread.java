package org.example.thread;

import org.example.employee.EmployeeDTO;
import org.example.database.EmployeesDAO;
import org.example.logger.EmployeeLogger;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;

public class DatabaseThread extends Thread {

    private List<EmployeeDTO> employees;
    private EmployeesDAO employeesDAO;
    private Connection connection;
    EmployeeLogger logger = new EmployeeLogger();

    public DatabaseThread(List<EmployeeDTO> employees, EmployeesDAO employeesDAO, Connection connection) {
        this.employees = employees;
        this.employeesDAO = employeesDAO;
        this.connection = connection;
        logger.setupLogger();

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
