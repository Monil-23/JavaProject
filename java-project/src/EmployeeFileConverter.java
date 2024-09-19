import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EmployeeFileConverter {
    public static void main(String[] args) {
        ArrayList<Employee> employees = loadData("employeeDb.txt");

        saveDataToFile("employeeDb.dat", employees);
    }

    public static ArrayList<Employee> loadData(String filename) {
        ArrayList<Employee> employeeList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\t"); // Assuming tab-delimited file
                if (data.length == 11) {
                    String firstName = data[0];
                    String lastName = data[1];
                    String phoneNumber = data[2];
                    String address = data[3];
                    String city = data[4];
                    String state = data[5];
                    int employeeId = Integer.parseInt(data[6]);
                    String employeeTitle = data[7];
                    String department = data[8];
                    double salary = Double.parseDouble(data[9]);
                    Date hireDate = dateFormat.parse(data[10]);

                    Employee employee = new Employee(firstName, lastName, phoneNumber, address, 
                                                    city, state, employeeId, employeeTitle, 
                                                    department, salary, hireDate);
                    employeeList.add(employee);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading data from the file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing data: " + e.getMessage());
        }

        return employeeList;
    }

    public static void saveDataToFile(String filename, ArrayList<Employee> employeeList) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(employeeList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
