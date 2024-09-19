import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Employee implements Serializable {
    // Data fields
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private int employeeId;
    private String employeeTitle;
    private String department;
    private double salary;
    private Date hireDate;

    // Constructor
    public Employee(String firstName, String lastName, String phoneNumber, String address, 
                    String city, String state, int employeeId, String employeeTitle, 
                    String department, double salary, Date hireDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.state = state;
        this.employeeId = employeeId;
        this.employeeTitle = employeeTitle;
        this.department = department;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    // Accessors (getters)
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeTitle() {
        return employeeTitle;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public Date getHireDate() {
        return hireDate;
    }


    // Mutators (setters)
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmployeeTitle(String employeeTitle) {
        this.employeeTitle = employeeTitle;
    }

    public void setDepartment(String department){
        this.department = department;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setHireDate(Date hirDate){
        this.hireDate = hirDate;
    }


    // Override equals method to compare Employee objects
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId &&
                Double.compare(employee.salary, salary) == 0 &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(phoneNumber, employee.phoneNumber) &&
                Objects.equals(address, employee.address) &&
                Objects.equals(city, employee.city) &&
                Objects.equals(state, employee.state) &&
                Objects.equals(employeeTitle, employee.employeeTitle) &&
                Objects.equals(department, employee.department) &&
                Objects.equals(hireDate, employee.hireDate);
    }

    // Override toString method to get a string representation of the Employee object
    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", employeeId=" + employeeId +
                ", employeeTitle='" + employeeTitle + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDate +
                '}';
    }

    // Override hashCode method to generate a hash code for Employee objects
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phoneNumber, address, city, state, 
                            employeeId, employeeTitle, department, salary, hireDate);
    }
}








