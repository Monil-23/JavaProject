import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import java.text.SimpleDateFormat;


public class EmployeeView extends Application {
    private EmployeeController controller;
    private TextField firstNameF, lastNameF, phoneNumberF, addressF, cityF, stateF, employeeIdF, employeeTitleF, departmentF, salaryF, hireDateF;
    private Button newBT, editBT, saveBT, deleteBT, prevBT, nextBT;
    private int currentState;

    
    public EmployeeView(EmployeeController controller) {
        this.controller = controller;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Employee Data");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        setupForm(grid);
        currentState = 0;
        updateState();

        // Display the first employee if available
        displayCurrentEmployee();

        Scene scene = new Scene(grid, 700, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        updateState(); // Initialize UI state
    }

    private void setupForm(GridPane grid) {

        // TextFields for employee attributes
        firstNameF = new TextField();
        lastNameF = new TextField();
        phoneNumberF = new TextField();
        addressF = new TextField();
        cityF = new TextField();
        stateF = new TextField();
        employeeIdF = new TextField();
        employeeTitleF = new TextField();
        departmentF = new TextField();
        salaryF = new TextField();
        hireDateF = new TextField();
        hireDateF.setPromptText("YYYY-MM-DD");


        // Adding labels and fields to the grid
        addLabelAndField(grid, "First Name:", firstNameF, 0);
        addLabelAndField(grid, "Last Name:", lastNameF, 1);
        addLabelAndField(grid, "Phone Number:", phoneNumberF, 2);
        addLabelAndField(grid, "Address:", addressF, 3);
        addLabelAndField(grid, "City:", cityF, 4);
        addLabelAndField(grid, "State:", stateF, 5);
        addLabelAndField(grid, "Employee ID:", employeeIdF, 6);
        addLabelAndField(grid, "Employee Title:", employeeTitleF, 7);
        addLabelAndField(grid, "Department:", departmentF, 8);
        addLabelAndField(grid, "Salary:", salaryF, 9);
        addLabelAndField(grid, "Hiring Date:", hireDateF, 10);

           
        // buttons
        newBT = new Button("New");
        editBT = new Button("Edit");
        saveBT = new Button("Save");
        deleteBT = new Button("Delete");
        prevBT = new Button("<");
        nextBT = new Button(">");

        saveBT.setDisable(true); // initially disable

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn.getChildren().addAll(prevBT, newBT, editBT, saveBT, deleteBT, nextBT);
        grid.add(hbBtn, 1, 12);

        setupEventHandlers();
    }

    private void addLabelAndField(GridPane grid, String labelText, TextField textField, int row) {
        Label label = new Label(labelText);
        grid.add(label, 0, row);
        grid.add(textField, 1, row);
    }

    private void setupEventHandlers() {
        // Event handlers for buttons
        newBT.setOnAction(e -> handleNew());
        editBT.setOnAction(e -> handleEdit());
        saveBT.setOnAction(e -> handleSave());
        deleteBT.setOnAction(e -> handleDelete());
        prevBT.setOnAction(e -> navigatePrevious());
        nextBT.setOnAction(e -> navigateNext());
    }

    private void handleNew() {
        if (currentState == 1) { 
            currentState = 0; // Switch back to Read state
        } else {
            clearFields();
            currentState = 1; // Switch to Create state
        }
        updateState();
    }

    private void handleEdit() {
        if (currentState == 2) { 
            currentState = 0; // Switch back to Read state
        } else {
            currentState = 2; // Switch to Update state
        }
        updateState();
    }

    private void handleSave() {
        try {
            // Retrieve data from text fields
            String firstName = firstNameF.getText();
            String lastName = lastNameF.getText();
            String phoneNumber = phoneNumberF.getText();
            String address = addressF.getText();
            String city = cityF.getText();
            String state = stateF.getText();
            int employeeId = Integer.parseInt(employeeIdF.getText());
            String employeeTitle = employeeTitleF.getText();
            String department = departmentF.getText();
            double salary = Double.parseDouble(salaryF.getText());
            String hireDateStr = hireDateF.getText(); 
    
            // Validate inputs (basic example, expand as needed)
            if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() ||  address.isEmpty() || city.isEmpty() || 
                state.isEmpty() || employeeId <=0 || employeeTitle.isEmpty() || department.isEmpty() || salary <= 0 || hireDateStr.isEmpty()) {
                // show an error message
                System.out.println("Error: All fields are required and cannot be empty.");
                return;
            }
    
            
            if (currentState == 1) { // Create state
                controller.createEmployee(firstName, lastName, phoneNumber, address, city, state, employeeId, employeeTitle, department, salary, hireDateStr);
            } else if (currentState == 2) { // Update state
                int index = controller.getCurrentEmployeeIndex();
                controller.updateEmployee(index, firstName, lastName, phoneNumber, address, city, state, employeeId, employeeTitle, department, salary, hireDateStr);
            }
    
            currentState = 0; // Switch back to Read state
            updateState();
        } catch (NumberFormatException e) {
            // Handle number format exceptions
            System.out.println("Error: Invalid number format");
        }
    }
    
    

    private void handleDelete() {
        if (currentState == 3){
            controller.deleteEmployee(controller.getCurrentEmployeeIndex());
            currentState = 0; 
        } else {
            currentState = 3;
        }
        updateState();
    }

    private void navigatePrevious() {
        if (currentState == 3) {
            currentState = 0; 
        } else {
            controller.navigatePrevious();
        }
        updateState();
    }

    private void navigateNext() {
        if (currentState == 3) {
            currentState = 0; 
        } else {
            controller.navigateNext();
        }
        updateState();
    }

    private void updateState() {
        switch (currentState) {
            case 0: //Read
            
                newBT.setText("New");
                newBT.setStyle(""); 
                saveBT.setDisable(true);
                newBT.setText("New");
                editBT.setText("Edit");
                editBT.setText("Edit");
                editBT.setStyle(""); 
                deleteBT.setText("Delete");
                deleteBT.setStyle(""); 
                newBT.setDisable(false);
                editBT.setDisable(false);
                deleteBT.setDisable(false);
                prevBT.setDisable(false);
                nextBT.setDisable(false);
                displayCurrentEmployee(); // Display current employee details
                break;
    
        case 1: // Create
            saveBT.setDisable(false);
            newBT.setText("Cancel New");
            newBT.setStyle("-fx-text-fill: red;"); // Set Cancel button to red
            editBT.setDisable(true);
            deleteBT.setDisable(true);
            prevBT.setDisable(true);
            nextBT.setDisable(true);
            clearFields(); 
            break;
    
        case 2: // Update
            saveBT.setDisable(false);
            editBT.setText("Cancel Edit");
            editBT.setStyle("-fx-text-fill: red;"); // Set Cancel Edit button to red
            newBT.setDisable(true);
            deleteBT.setDisable(true);
            prevBT.setDisable(true);
            nextBT.setDisable(true);
        
        break;
    
                case 3: // Delete
                deleteBT.setText("Yes, delete");
                deleteBT.setStyle("-fx-text-fill: red;"); 
                saveBT.setDisable(true);
                newBT.setDisable(true);
                editBT.setDisable(true);
                prevBT.setDisable(false);  
                nextBT.setDisable(false);  
                break;
            
        }
    }

    private void displayCurrentEmployee() {
        Employee currentEmployee = controller.getCurrentEmployee();
        if (currentEmployee != null) {
    
            firstNameF.setText(currentEmployee.getFirstName());
            lastNameF.setText(currentEmployee.getLastName());
            phoneNumberF.setText(currentEmployee.getPhoneNumber());
            addressF.setText(currentEmployee.getAddress());
            cityF.setText(currentEmployee.getCity());
            stateF.setText(currentEmployee.getState());
            employeeIdF.setText(String.valueOf(currentEmployee.getEmployeeId()));
            employeeTitleF.setText(currentEmployee.getEmployeeTitle());
            departmentF.setText(currentEmployee.getDepartment());
            salaryF.setText(String.valueOf(currentEmployee.getSalary()));
    
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // date format
            hireDateF.setText(currentEmployee.getHireDate() != null ? dateFormat.format(currentEmployee.getHireDate()) : "");
        } else {
            clearFields(); // Clear fields if no current employee is available
        }
    }
    

    private void clearFields() {
        firstNameF.clear();
        lastNameF.clear();
        phoneNumberF.clear();
        addressF.clear();
        cityF.clear();
        stateF.clear();
        employeeIdF.clear();
        employeeTitleF.clear();
        departmentF.clear();
        salaryF.clear();
        hireDateF.clear();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
