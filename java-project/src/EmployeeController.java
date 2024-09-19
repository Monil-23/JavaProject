import java.io.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class EmployeeController {
    private ArrayList<Employee> employeeList;
    private int currentEmployeeIndex;

    public EmployeeController() {
        employeeList = new ArrayList<>();
        currentEmployeeIndex = -1;
        loadDataFromFile("employeeDb.dat"); // Load data when the controller is created

        if (!employeeList.isEmpty()) {
            currentEmployeeIndex = 0;
        }
    }

    public void createEmployee(String firstName, String lastName, String phoneNumber, String address, 
                               String city, String state, int employeeId, String employeeTitle, 
                               String department, double salary, String hireDateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Employee employee = new Employee(firstName, lastName, phoneNumber, address, 
                                             city, state, employeeId, employeeTitle, 
                                             department, salary, dateFormat.parse(hireDateStr));
            employeeList.add(employee);
            currentEmployeeIndex = employeeList.size() - 1;
        } catch (Exception e) {
            System.err.println("Error creating employee: " + e.getMessage());
        }
    }

    public void updateEmployee(int index, String firstName, String lastName, String phoneNumber, String address, 
                               String city, String state, int employeeId, String employeeTitle, 
                               String department, double salary, String hireDateStr) {
        if (index >= 0 && index < employeeList.size()) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Employee employee = employeeList.get(index);
                employee.setFirstName(firstName);
                employee.setLastName(lastName);
                employee.setPhoneNumber(phoneNumber);
                employee.setAddress(address);
                employee.setCity(city);
                employee.setState(state);
                employee.setEmployeeId(employeeId);
                employee.setEmployeeTitle(employeeTitle);
                employee.setDepartment(department);
                employee.setSalary(salary);
                employee.setHireDate(dateFormat.parse(hireDateStr));
            } catch (Exception e) {
                System.err.println("Error updating employee: " + e.getMessage());
            }
        }
    }

    public void deleteEmployee(int index) {
        if (index >= 0 && index < employeeList.size()) {
            employeeList.remove(index);
            if (currentEmployeeIndex > index) {
                currentEmployeeIndex--;
            } else if (currentEmployeeIndex == index) {
                if (currentEmployeeIndex >= employeeList.size()) {
                    currentEmployeeIndex = employeeList.size() - 1;
                }
            }
        }
    }

    public Employee getCurrentEmployee() {
        if (currentEmployeeIndex >= 0 && currentEmployeeIndex < employeeList.size()) {
            return employeeList.get(currentEmployeeIndex);
        }
        return null;
    }

    public int getCurrentEmployeeIndex() {
        return currentEmployeeIndex;
    }

    public void navigateNext() {
        if (currentEmployeeIndex < employeeList.size() - 1) {
            currentEmployeeIndex++;
        }
    }

    public void navigatePrevious() {
        if (currentEmployeeIndex > 0) {
            currentEmployeeIndex--;
        }
    }

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    public void saveDataToFile(String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(employeeList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadDataFromFile(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            Object obj = inputStream.readObject();
            if (obj instanceof ArrayList) {
                employeeList = (ArrayList<Employee>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
