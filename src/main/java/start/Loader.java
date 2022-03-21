package start;

import java.io.BufferedReader;
import java.io.FileReader;

public class Loader {
    public static void start() {
        FileIOUtils.readFile("src/main/resources/EmployeeRecords.csv");
    }



}
