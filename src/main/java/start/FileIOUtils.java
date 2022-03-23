package start;

import org.example.EmployeeDTO;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileIOUtils {
    static void readFile(String fileName) {
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            String line = bufferedReader.readLine();

            while (line != null) {
                String[] employeeData = line.split(",");
                EmployeeDTO employeeDTO = new EmployeeDTO(employeeData);
                line = bufferedReader.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
