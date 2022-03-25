package org.example.database;

import org.example.employee.EmployeeDTO;

import java.sql.*;

public class EmployeesDAO {

    private Connection connection;
    private Statement statement;
    private PreparedStatement batchInsertStatement;

    public EmployeesDAO(Connection connection) {
        this.connection = connection;
        try {
            statement = connection.createStatement();
            batchInsertStatement = connection.prepareStatement(SQLQueries.INSERT);
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

    public void batchInsert(EmployeeDTO employee){
        try {
            batchInsertStatement.setInt(1, employee.getEmpID());
            batchInsertStatement.setString(2, employee.getNamePrefix());
            batchInsertStatement.setString(3, employee.getFirstName());
            batchInsertStatement.setString(4, employee.getMiddleInitial());
            batchInsertStatement.setString(5, employee.getLastName());
            batchInsertStatement.setString(6, employee.getGender());
            batchInsertStatement.setString(7, employee.getEmail());
            batchInsertStatement.setDate(8, Date.valueOf(employee.getDob()));
            batchInsertStatement.setDate(9, Date.valueOf(employee.getDateOfJoining()));
            batchInsertStatement.setFloat(10, employee.getSalary());
            batchInsertStatement.addBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeBatchInsert(){
        try {
            batchInsertStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
