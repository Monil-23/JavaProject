/*************************************************************************************************
* 
*  __  ___            _ _     _____ _           _     
*  |  \/  |           (_) |   /  ___| |         | |    
*  | .  . | ___  _ __  _| |   \ `--.| |__   __ _| |__  
*  | |\/| |/ _ \| '_ \| | |    `--. \ '_ \ / _` | '_ \ 
*  | |  | | (_) | | | | | |   /\__/ / | | | (_| | | | |
*  \_|  |_/\___/|_| |_|_|_|   \____/|_| |_|\__,_|_| |_|
*                                                   
* 
* Project Description: This is a Employee-MVC GUI application which performs CRUD operations.
**************************************************************************************************/


import javafx.application.Application;
import javafx.stage.Stage;

public class EmployeeApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create an instance of the EmployeeController
        EmployeeController controller = new EmployeeController();

        // Load data from the serialized file (if needed)
         controller.loadDataFromFile("employeeDb.dat");

        // passing the controller to EmployeeView
        EmployeeView view = new EmployeeView(controller); // Modify EmployeeView to accept controller
        view.start(primaryStage); // calling start method in EmployeeView
    }

    public static void main(String[] args) {
        launch(args); 
    }
}
