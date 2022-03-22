package org.example.database;

import java.sql.*;
import java.time.LocalDate;

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
                System.out.println(resultSet.getInt(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
                System.out.println(resultSet.getString(4));
                System.out.println(resultSet.getString(5));
                System.out.println(resultSet.getString(6));
                System.out.println(resultSet.getDate(7));
                System.out.println(resultSet.getDate(8));
                System.out.println(resultSet.getFloat(9));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(int empID, String namePrefix, String firstName, String middleInitial, String lastName, String gender, String email, LocalDate dob, LocalDate dateOfJoining, float salary){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.INSERT);
            preparedStatement.setInt(1, empID);
            preparedStatement.setString(2, namePrefix);
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, middleInitial);
            preparedStatement.setString(5, lastName);
            preparedStatement.setString(6, gender);
            preparedStatement.setString(7, email);
            preparedStatement.setDate(7, Date.valueOf(dob));
            preparedStatement.setDate(8, Date.valueOf(dateOfJoining));
            preparedStatement.setFloat(9, salary);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
