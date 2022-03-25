package org.example.database;

import java.sql.*;

public class DatabaseConnection {
    private Statement statement;

    private Connection connection;

    public DatabaseConnection(Connection connection) {
        this.connection = connection;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getMaxConnection() {
        int maxConnection = 0;
        try {
            ResultSet result = statement.executeQuery(SQLQueries.SELECT_MAX_CONNECTION);
            result.next();
            maxConnection = result.getInt(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxConnection;
    }

    public void setMaxConnection(int maxConnection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SET_MAX_CONNECTION);
            preparedStatement.setInt(1, maxConnection);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
