# JavaProject 

# Human Resources Application

This JavaFX application is designed to manage Human Resources employee data using CRUD (Create, Read, Update, Delete) operations, following the MVC (Model-View-Controller) design pattern. The application features an `Employee` class that includes essential employee attributes such as name, contact details, job title, department, salary, and hire date.

## Features

- **CRUD Operations**: Create, view, update, and delete employee records.
- **State Management**: The application provides distinct states for handling each CRUD operation, ensuring smooth transitions between them. For instance, when creating a new employee, the interface adjusts to allow input, disables navigation buttons, and changes button labels.
- **Data Serialization**: Employee data is loaded from a binary file containing serialized employee objects. A utility class (`EmployeeFileConverter`) is used to convert tab-delimited text data into serialized objects for storage.
- **Navigation**: Users can browse through employee records using "Next" and "Previous" buttons with graceful handling at the beginning or end of the list.

## MVC Design

The project implements the MVC pattern, separating concerns:

- **Model**: The `Employee` class, with constructors, accessors, and overrides for `toString`, `hashCode`, and `equals` methods.
- **View**: The JavaFX GUI presents the employee data and allows interaction with the application states.
- **Controller**: Manages the flow between the View and Model, enabling state changes and user interactions with the data.

## Output Images

Screenshots of the application in action are available in the `output-images` folder for a visual overview.
