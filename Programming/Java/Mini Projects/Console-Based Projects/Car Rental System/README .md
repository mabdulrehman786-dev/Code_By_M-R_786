🚗 Car Rental Management System

A console-based Car Rental Management System built in Java that simulates the core functionalities of a real-world car rental service. The application allows users to rent cars, return cars, and view rental information through a structured menu-driven interface.
This project demonstrates the practical implementation of Object-Oriented Programming (OOP) concepts in Java, including class design, encapsulation, and modular programming.

📌 Project Overview

The Car Rental Management System is designed to manage basic car rental operations.
It keeps track of available cars, rented vehicles, customer details, and rental records.

The system allows users to:
-> Rent a car from the available vehicle list
-> Return a previously rented car
-> View detailed rental records
-> Check the overall status of the car inventory
-> Display currently rented cars

The application provides a simple yet effective console-based user interface that ensures smooth interaction with the system.

✨ Features

✔ Rent a car with customer details
✔ Return rented vehicles
✔ View all rental details
✔ Display system car statistics
✔ List currently rented cars
✔ Automatic price calculation based on rental days
✔ Menu-driven user interface
✔ Input validation for better user experience

🧱 System Architecture

The system is designed using multiple classes following Object-Oriented Programming principles.

🚘 Car
Represents a vehicle available for rent.
Attributes
Car ID
Brand
Model
Base price per day
Availability status
Responsibilities
Calculate rental price
Manage car availability

👤 Customer

Represents a customer renting a car.
Attributes
Customer ID
Customer name

📄 Rental

Represents a rental transaction between a car and a customer.

Attributes
Car information
Customer information
Rental duration

🏢 CarRentalSystem

The core system controller that manages:

Car inventory
Customer records
Rental transactions
System menu operations

▶ CarRentalManagementSystem

Contains the main method that initializes the program and starts the system.

⚙️ Technologies Used

Java
Object-Oriented Programming (OOP)
Java Collections (ArrayList)
Scanner Class for User Input

🧠 OOP Concepts Demonstrated

This project applies several important Object-Oriented Programming concepts:

Encapsulation – Protecting data using private variables and getters
Abstraction – Simplifying system interactions through classes
Modularity – Dividing the program into multiple classes
Reusability – Reusable and maintainable code structure

🚀 Future Improvements

Possible enhancements for this system include:
Search cars by brand or model
Customer rental history tracking
Data storage using files or databases
Graphical User Interface (GUI) using Java Swing or JavaFX
Online booking simulation

🎓 Educational Purpose

This project was created for learning and practicing Java programming and object-oriented design. It demonstrates how real-world systems can be modeled using structured classes and logical program flow.

⭐ Contributing

Contributions, suggestions, and improvements are welcome.
Feel free to fork the repository and submit pull requests.

📜 License

This project is open-source and available for educational use.
