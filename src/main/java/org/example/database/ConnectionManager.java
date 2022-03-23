package org.example.database;

import org.example.EmployeeDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class ConnectionManager {

    private static Connection connection;

    public static Connection getConnection() {

        String url = PropertiesLoader.getProperties().getProperty("url");
        String username = PropertiesLoader.getProperties().getProperty("username");
        String password = PropertiesLoader.getProperties().getProperty("password");

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection connection = ConnectionManager.getConnection();
        EmployeesDAO employeesDAO = new EmployeesDAO(connection);
        LocalDate dob = LocalDate.of(1988,12,19);
        LocalDate dateOfJoining = LocalDate.of(2020,12,10);
        employeesDAO.insert(02,"Mr", "Simone", "none","Conigliaro", "M", "lalala@gmail.com", dob, dateOfJoining, 20000F);
        employeesDAO.printAllEmployees();
        ConnectionManager.closeConnection();
        
    }
}
