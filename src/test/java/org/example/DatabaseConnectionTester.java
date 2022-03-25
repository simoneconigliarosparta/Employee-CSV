package org.example;

import org.example.database.ConnectionManager;
import org.example.database.DatabaseConnection;
import org.example.database.PropertiesLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionTester {

    @Test
    @DisplayName("Test connection")
    void testConnection() {
        String url = PropertiesLoader.getProperties().getProperty("url");
        String username = PropertiesLoader.getProperties().getProperty("username");
        String password = PropertiesLoader.getProperties().getProperty("password");

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password) ;
        } catch (SQLException e) {

        }
        Assertions.assertTrue(connection != null);
    }

    @Test
    @DisplayName("Should return max connection")
    void shouldReturnMaxConnection() {

        DatabaseConnection databaseConnection = new DatabaseConnection(ConnectionManager.getConnection());
        databaseConnection.setMaxConnection(200);
        int maxConnection = databaseConnection.getMaxConnection();
        Assertions.assertEquals(maxConnection,200);
    }
}
