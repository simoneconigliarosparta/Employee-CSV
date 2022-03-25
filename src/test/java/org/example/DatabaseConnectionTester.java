package org.example;

import org.example.database.ConnectionManager;
import org.example.database.DatabaseConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DatabaseConnectionTester {

    @Test
    @DisplayName("Should return max connection")
    void shouldReturnMaxConnection() {

        DatabaseConnection databaseConnection = new DatabaseConnection(ConnectionManager.getConnection());
        databaseConnection.setMaxConnection(200);
        int maxConnection = databaseConnection.getMaxConnection();
        Assertions.assertEquals(maxConnection,200);
    }
}
