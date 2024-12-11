Online Dress Rental System

Welcome to the Online Dress Rental System, a Java-based application that facilitates managing dress rentals efficiently. This project supports operations for adding dresses, registering customers, creating rentals, viewing available dresses, and saving data to a file.

Features

Dress Management: Add formal or casual dresses with details like size, rental price, and designer.

Customer Management: Register customers with contact information.

Rental Management: Create rentals with a calculated total cost based on the rental duration.

Data Persistence: Save dress, customer, and rental details to a file.

Interactive CLI: A menu-driven interface for user interaction.

Project Structure

Dress Class: Represents a dress with attributes like ID, name, description, size, rental price, and availability.

Subclasses: FormalDress (with occasion) and CasualDress (with season).

Customer Class: Represents a customer with details such as ID, name, email, and address.

Rental Class: Represents a rental transaction, including start and end dates, and calculates the total cost.

RentalManager Class: Manages collections of dresses, customers, and rentals.

Main Class: Entry point for the application, providing the interactive CLI.

Requirements

Java Development Kit (JDK) 8 or higher

Basic understanding of Java and object-oriented programming

How to Run

Clone the Repository:

git clone https://github.com/your-username/online-dress-rental.git
cd online-dress-rental

Compile the Code:

javac Main.java

Run the Application:

java Main

Follow the Menu Instructions:
Use the menu options to add dresses, customers, create rentals, view data, and save it.

Usage Instructions

Menu Options

Add Dress: Add a new dress to the inventory. Specify whether it is formal or casual and provide all required details.

Add Customer: Register a customer by providing details like ID, name, email, contact number, and address.

Create Rental: Create a rental by linking a dress and a customer, and provide rental dates.

View Available Dresses: Displays a list of dresses that are currently available for rent.

View Rental Information: Displays details of all rental transactions.

Save Data: Saves the current state of the system to a file.

Exit: Closes the application.

Example Data

Dress:

ID: 1

Name: Elegant Gown

Type: Formal

Occasion: Wedding

Rental Price: $100/day

Customer:

ID: 101

Name: Jane Doe

Email: jane.doe@example.com

Rental:

Rental ID: 501

Dress ID: 1

Customer ID: 101

Dates: 10-Dec-2024 to 12-Dec-2024

Contributing

Contributions are welcome! Please fork the repository, make your changes, and submit a pull request.

License

This project is licensed under the MIT License. See the LICENSE file for details.

Contact

For questions or support, please contact [sdarshanit@gmail.com].

Thank you for using the Online Dress Rental System!

