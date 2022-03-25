package org.example;

import org.example.database.ConnectionManager;
import org.example.database.EmployeesDAO;
import org.example.employee.EmployeeDTO;
import org.example.employee.EmployeeFilter;
import org.example.file_utils.FilePath;
import org.example.file_utils.FileReader;
import org.example.thread.DatabaseThread;
import org.example.thread.ThreadManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PerformanceMultiThreadTester {

    private static List<EmployeeDTO> filteredList = new ArrayList<>();

    @BeforeAll
    public static void loadData(){
        List<EmployeeDTO> fullList = FileReader.readFile(FilePath.EMPLOYEE_RECORD_LARGE);
        filteredList = EmployeeFilter.removeDuplicates(fullList);
    }

    @Test
    @DisplayName("Check speed insertion multi thread")
    void checkSpeedInsertionMultiThread() {

        final int NUM_THREADS = 150;

        List<DatabaseThread> listThreadsToRun = ThreadManager.setThread(NUM_THREADS, filteredList);

        long start = System.nanoTime();

        for (DatabaseThread t: listThreadsToRun){
            t.start();
        }

        for (DatabaseThread t: listThreadsToRun){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                t.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        long end = System.nanoTime();
        long time = (end - start) / 1000000000;
        System.out.println("Time taken: " + time + " seconds");
    }
}
