package org.example.start;

import org.example.display.DisplayManager;
import org.example.employee.EmployeeDTO;
import org.example.employee.EmployeeFilter;
import org.example.file_utils.FileReader;
import org.example.file_utils.FilePath;
import org.example.thread.DatabaseThread;
import org.example.thread.ThreadManager;

import java.util.List;

public class Loader {
    public static void start() {

        List<EmployeeDTO> fullList = FileReader.readFile(FilePath.EMPLOYEE_RECORD_LARGE);
        List<EmployeeDTO> filteredList = EmployeeFilter.removeDuplicates(fullList);
        List<DatabaseThread> threadsToRun = ThreadManager.setThread(150, filteredList);
        DisplayManager.printResult(threadsToRun);
    }
}