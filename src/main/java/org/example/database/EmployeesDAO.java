package org.example.database;

import org.example.employee.EmployeeDTO;

import java.sql.*;

public class EmployeesDAO {

    private Connection connection;
    private Statement statement;

    public EmployeesDAO(Connection connection) {
        this.connection = connection;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printAllEmployees() {
        try {
            ResultSet resultSet = statement.executeQuery(SQLQueries.SELECT_ALL);
            while (resultSet.next()) {
                String result  = "";
                for(int i = 1; i<=10; i++){
                    result = result.concat(resultSet.getString(i) + " ");
                }
                System.out.println(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(EmployeeDTO employee){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.INSERT);
            preparedStatement.setInt(1, employee.getEmpID());
            preparedStatement.setString(2, employee.getNamePrefix());
            preparedStatement.setString(3, employee.getFirstName());
            preparedStatement.setString(4, employee.getMiddleInitial());
            preparedStatement.setString(5, employee.getLastName());
            preparedStatement.setString(6, employee.getGender());
            preparedStatement.setString(7, employee.getEmail());
            preparedStatement.setDate(8, Date.valueOf(employee.getDob()));
            preparedStatement.setDate(9, Date.valueOf(employee.getDateOfJoining()));
            preparedStatement.setFloat(10, employee.getSalary());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
