/*
 ==========================================================================================================
                                   OFFICE MANAGEMENT SYSTEM (OMS)
 ==========================================================================================================
    Course        : Object-Oriented Programming (OOP)
    Assignment    : Group Assignment 4
    Project Name  : OFFICE MANAGEMENT SYSTEM (OMS)

    Group Members :
        1. Member 1
        2. Member 2
        3. Member 3

 ==========================================================================================================
    PROJECT IDEA:
    This project simulates a simple Office Management System using real-world concepts.
    It manages different types of employees such as Manager and Staff, using OOP concepts
    including inheritance, abstraction, polymorphism, composition, and interfaces.

 ==========================================================================================================
    ASSIGNMENT 1 SUMMARY:
      ✔ Classes & Objects
      ✔ Encapsulation
      ✔ Constructors
      ✔ Data Handling

 ==========================================================================================================
    ASSIGNMENT 2 SUMMARY:
      ✔ Abstract Class (Employee)
      ✔ Inheritance (Manager & Staff)
      ✔ Method Overriding
      ✔ Method Overloading (Added)
      ✔ Upcasting / Downcasting
      ✔ Department class added

 ==========================================================================================================
    ASSIGNMENT 3 NEW ADDITIONS:
      ✔ Interface: WorkReport
      ✔ Composition: Employee HAS-A Address
      ✔ Menu system for user interaction
      ✔ Viva-based improvements

 ==========================================================================================================
    ASSIGNMENT 4 NEW ADDITIONS:
      ✔ Graphical User Interface (GUI) using Java Swing
      ✔ All inputs moved to GUI
      ✔ Save and Clear buttons added
      ✔ Can handle multiple employees
      ✔ Demonstrates OOP concepts through GUI

 ==========================================================================================================
*/

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

// ===================== INTERFACE =====================
interface WorkReport {
    public void generateReport();
    public void submitReport();
}

// ===================== COMPOSITION CLASS =====================
class Address {
    String city, country;

    public Address(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public void showAddress() {
        System.out.println("Address: " + city + ", " + country);
    }
}

// ===================== ABSTRACT PARENT CLASS =====================
abstract class Employee {
    protected int id;
    protected String name;
    protected int age;
    protected double salary;
    protected Address address; // Composition (HAS-A)

    public abstract void takeInput();
    public abstract void displayInfo();
}

// ===================== MANAGER CLASS =====================
class Manager extends Employee implements WorkReport {
    private String departmentName;
    private double bonus;

    @Override
    public void takeInput() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nEnter Manager Details:");
        System.out.print("Enter ID: ");
        id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        name = sc.nextLine();
        System.out.print("Enter Age: ");
        age = sc.nextInt();
        System.out.print("Enter Salary: ");
        salary = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter Bonus: ");
        bonus = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter Department: ");
        departmentName = sc.nextLine();

        System.out.print("Enter City: ");
        String city = sc.nextLine();
        System.out.print("Enter Country: ");
        String country = sc.nextLine();

        address = new Address(city, country); // Fixed: Create Address object
    }

    // ===================== METHOD OVERLOADING ADDED =====================
    public void calculateSalary() { // Version 1
        salary += bonus;
    }

    public void calculateSalary(double extraBonus) { // Version 2 (overloaded)
        salary += bonus + extraBonus;
    }
    // ================================================================

    @Override
    public void displayInfo() {
        calculateSalary(); // calls normal salary calculation

        System.out.println("\n<|=== Manager Details ===|>");
        System.out.println("Name: " + name + "\nID: " + id +
                "\nAge: " + age + "\nDepartment: " + departmentName +
                "\nSalary (with bonus): " + salary);
        address.showAddress();
    }

    @Override
    public void generateReport() {
        System.out.println("Manager " + name + " is generating Admin Report...");
    }

    @Override
    public void submitReport() {
        System.out.println("Manager " + name + " submitted report to CEO.");
    }
}

// ===================== STAFF CLASS =====================
class Staff extends Employee implements WorkReport {
    private String role;
    private String shift;

    @Override
    public void takeInput() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nEnter Staff Details:");
        System.out.print("Enter ID: ");
        id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        name = sc.nextLine();
        System.out.print("Enter Age: ");
        age = sc.nextInt();
        System.out.print("Enter Salary: ");
        salary = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter Role: ");
        role = sc.nextLine();
        System.out.print("Enter Shift: ");
        shift = sc.nextLine();

        System.out.print("Enter City: ");
        String city = sc.nextLine();
        System.out.print("Enter Country: ");
        String country = sc.nextLine();

        address = new Address(city, country);
    }

    @Override
    public void displayInfo() {
        System.out.println("\n<|=== Staff Details ===|>");
        System.out.println("Name: " + name + "\nID: " + id +
                "\nAge: " + age + "\nRole: " + role +
                "\nShift: " + shift + "\nSalary: " + salary);
        address.showAddress();
    }

    @Override
    public void generateReport() {
        System.out.println("Staff " + name + " is generating Daily Work Report...");
    }

    @Override
    public void submitReport() {
        System.out.println("Staff " + name + " submitted report to Manager.");
    }
}

// ===================== DEPARTMENT CLASS =====================
class Department {
    private int depId;
    private String depName;
    private int depMembers;

    public void takeDepartmentInput() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Department Details:");
        System.out.print("Enter Department ID:");
        depId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Department Name:");
        depName = sc.nextLine();

        System.out.print("Enter Number of Department Members:");
        depMembers = sc.nextInt();
    }

    public void showDetails() {
        System.out.println("\n<|=== Department Info ===|>");
        System.out.println("Department ID: " + depId + "\nName: " + depName +
                "\nMembers: " + depMembers);
    }
}

// ===================== SIMPLE GUI CLASS =====================
class OfficeManagementSystemGUI extends JFrame {
    
    // GUI Components
    private JTextField empIdField, empNameField, empAgeField, empSalaryField;
    private JTextField bonusField, deptField;
    private JTextField cityField, countryField;
    private JTextArea displayArea;
    
    // Store multiple employees
    private ArrayList<Employee> employees = new ArrayList<>();
    private Department dept = new Department();
    
    public OfficeManagementSystemGUI() {
        super("Office Management System - GUI"); // Fixed: Use super instead of creating new frame
        
        setSize(600, 550); // Slightly increased height
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set layout
        setLayout(new BorderLayout());
        
        // Create components
        createComponents();
        
        setVisible(true);
    }
    
    private void createComponents() {

    /* ================= TOP PANEL ================= */
    JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
    topPanel.add(new JLabel("Select Employee Type:"));

    JButton managerBtn = new JButton("Manager");
    JButton staffBtn = new JButton("Staff");

    topPanel.add(managerBtn);
    topPanel.add(staffBtn);

    add(topPanel, BorderLayout.NORTH);

    /* ================= CENTER PANEL ================= */
    JPanel centerPanel = new JPanel(new GridLayout(8, 2, 10, 10));

    centerPanel.add(new JLabel("Employee ID:"));
    empIdField = new JTextField(12);
    centerPanel.add(empIdField);

    centerPanel.add(new JLabel("Name:"));
    empNameField = new JTextField(12);
    centerPanel.add(empNameField);

    centerPanel.add(new JLabel("Age:"));
    empAgeField = new JTextField(12);
    centerPanel.add(empAgeField);

    centerPanel.add(new JLabel("Salary:"));
    empSalaryField = new JTextField(12);
    centerPanel.add(empSalaryField);

    centerPanel.add(new JLabel("Bonus (Manager):"));
    bonusField = new JTextField("0.0", 12);
    centerPanel.add(bonusField);

    centerPanel.add(new JLabel("Department:"));
    deptField = new JTextField("General", 12);
    centerPanel.add(deptField);

    centerPanel.add(new JLabel("City:"));
    cityField = new JTextField("Islamabad", 12);
    centerPanel.add(cityField);

    centerPanel.add(new JLabel("Country:"));
    countryField = new JTextField("Pakistan", 12);
    centerPanel.add(countryField);

    JPanel formWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
    formWrapper.add(centerPanel);

    add(formWrapper, BorderLayout.CENTER);

    /* ================= BUTTON PANEL ================= */
    JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 10, 10));

    JButton addEmployeeBtn = new JButton("Add Employee");
    JButton saveBtn = new JButton("Save Employee");
    JButton displayBtn = new JButton("Display Info");
    JButton reportBtn = new JButton("Generate Report");
    JButton deptBtn = new JButton("Show Department");
    JButton clearBtn = new JButton("Clear");
    JButton exitBtn = new JButton("Exit");

    buttonPanel.add(addEmployeeBtn);
    buttonPanel.add(saveBtn);
    buttonPanel.add(displayBtn);
    buttonPanel.add(reportBtn);
    buttonPanel.add(deptBtn);
    buttonPanel.add(clearBtn);
    buttonPanel.add(exitBtn);

    JPanel buttonWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    buttonWrapper.add(buttonPanel);

    /* ================= OUTPUT AREA ================= */
    displayArea = new JTextArea(12, 55);
    displayArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(displayArea);

    JPanel bottomPanel = new JPanel(new BorderLayout());
    bottomPanel.add(buttonWrapper, BorderLayout.NORTH);
    bottomPanel.add(scrollPane, BorderLayout.CENTER);

    add(bottomPanel, BorderLayout.SOUTH);

    /* ================= ACTION LISTENERS ================= */

    managerBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            bonusField.setEnabled(true);
            deptField.setEnabled(true);
            displayArea.append("Selected: Manager\n");
        }
    });

    staffBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            bonusField.setEnabled(false);
            deptField.setEnabled(false);
            displayArea.append("Selected: Staff\n");
        }
    });

    addEmployeeBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            addEmployee();
        }
    });

    saveBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            saveEmployee();
        }
    });

    displayBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            displayEmployee();
        }
    });

    reportBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            generateReport();
        }
    });

    deptBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            showDepartment();
        }
    });

    clearBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            clearFields();
            displayArea.setText("");
        }
    });

    exitBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    });
}

    
    private void addEmployee() {
        try {
            // Get values from fields
            int id = Integer.parseInt(empIdField.getText());
            String name = empNameField.getText();
            int age = Integer.parseInt(empAgeField.getText());
            double salary = Double.parseDouble(empSalaryField.getText());
            String city = cityField.getText();
            String country = countryField.getText();
            
            // Create Address
            Address address = new Address(city, country);
            
            Employee employee;
            
            // Check if bonus field is enabled (Manager selected)
            if (bonusField.isEnabled()) {
                // Create Manager
                Manager manager = new Manager();
                manager.id = id;
                manager.name = name;
                manager.age = age;
                manager.salary = salary;
                manager.address = address;
                employee = manager;
                
                displayArea.append("Manager created: " + name + " (ID: " + id + ")\n");
            } else {
                // Create Staff
                Staff staff = new Staff();
                staff.id = id;
                staff.name = name;
                staff.age = age;
                staff.salary = salary;
                staff.address = address;
                employee = staff;
                
                displayArea.append("Staff created: " + name + " (ID: " + id + ")\n");
            }
            
            // Add to list
            employees.add(employee);
            displayArea.append("Employee added to list!\n\n");
            
        } catch (NumberFormatException e) {
            displayArea.append("Error: Please enter valid numbers in ID, Age, Salary fields\n");
        }
    }
    
    private void saveEmployee() {
        if (employees.isEmpty()) {
            displayArea.append("No employees to save!\n");
            return;
        }
        
        displayArea.append("\n=== Saving All Employees ===\n");
        displayArea.append("Total employees saved: " + employees.size() + "\n");
        
        for (Employee emp : employees) {
            displayArea.append("- " + emp.name + " (ID: " + emp.id + ") - ");
            if (emp instanceof Manager) {
                displayArea.append("Manager\n");
            } else {
                displayArea.append("Staff\n");
            }
        }
        
        displayArea.append("All employees saved successfully!\n\n");
    }
    
    private void displayEmployee() {
        if (employees.isEmpty()) {
            displayArea.append("No employees added yet!\n");
            return;
        }
        
        displayArea.append("\n=== Displaying All Employees ===\n");
        
        for (Employee emp : employees) {
            displayArea.append("Name: " + emp.name + "\n");
            displayArea.append("ID: " + emp.id + "\n");
            displayArea.append("Age: " + emp.age + "\n");
            displayArea.append("Salary: " + emp.salary + "\n");
            
            // Show composition - Address
            if (emp.address != null) {
                displayArea.append("Address: " + emp.address.city + ", " + 
                                  emp.address.country + "\n");
            }
            
            // Show type using polymorphism
            if (emp instanceof Manager) {
                displayArea.append("Type: Manager (Polymorphism Example)\n");
            } else if (emp instanceof Staff) {
                displayArea.append("Type: Staff (Polymorphism Example)\n");
            }
            
            // Show interface implementation
            if (emp instanceof WorkReport) {
                displayArea.append("Interface: Implements WorkReport\n");
            }
            
            displayArea.append("-------------------\n");
        }
        
        displayArea.append("Total Employees: " + employees.size() + "\n\n");
    }
    
    private void generateReport() {
        if (employees.isEmpty()) {
            displayArea.append("No employees to generate report for!\n");
            return;
        }
        
        displayArea.append("\n=== Generating Reports ===\n");
        
        // Demonstrate polymorphism and interface
        for (Employee emp : employees) {
            displayArea.append("\nEmployee: " + emp.name + "\n");
            displayArea.append("Type: " + (emp instanceof Manager ? "Manager" : "Staff") + "\n");
            
            // Interface demonstration
            if (emp instanceof WorkReport) {
                if (emp instanceof Manager) {
                    displayArea.append("Report: Generates admin reports (Interface Example)\n");
                    displayArea.append("Report: Submits to CEO\n");
                } else {
                    displayArea.append("Report: Generates daily work reports (Interface Example)\n");
                    displayArea.append("Report: Submits to Manager\n");
                }
            }
        }
        
        displayArea.append("\n=== All Reports Generated ===\n\n");
    }
    
    private void showDepartment() {
        displayArea.append("\n=== Department Information ===\n");
        displayArea.append("Demonstrating Composition:\n");
        displayArea.append("Department: Human Resource\n");
        displayArea.append("Employees in this department:\n");
        
        if (!employees.isEmpty()) {
            for (Employee emp : employees) {
                displayArea.append("- " + emp.name + "\n");
            }
        } else {
            displayArea.append("No employees yet\n");
        }
        
        displayArea.append("==========================\n\n");
    }
    
    private void clearFields() {
        empIdField.setText("");
        empNameField.setText("");
        empAgeField.setText("");
        empSalaryField.setText("");
        bonusField.setText("0.0");
        deptField.setText("General");
        cityField.setText("Islamabad");
        countryField.setText("Pakistan");
    }
}

// ===================== MAIN CLASS =====================
public class OMS_A4_FINAL_Updated {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("==========================================");
        System.out.println("   OFFICE MANAGEMENT SYSTEM");
        System.out.println("==========================================");
        System.out.println("Choose Mode:");
        System.out.println("1. Console Mode");
        System.out.println("2. GUI Mode");
        System.out.print("Enter your choice (1 or 2): ");
        
        int choice = sc.nextInt();
        
        if (choice == 1) {
            // Run console mode
            runConsoleMode();
        } else if (choice == 2) {
            // Run GUI mode
            new OfficeManagementSystemGUI();
        } else {
            System.out.println("Invalid choice! Running GUI mode by default.");
            new OfficeManagementSystemGUI();
        }
    }
    
    private static void runConsoleMode() {
        Scanner sc = new Scanner(System.in);
        Employee manager = null;
        Employee staff = null;
        Department dept = new Department();

        while (true) {
            System.out.println("\n========== OFFICE MANAGEMENT SYSTEM ==========");
            System.out.println("1. Add Manager");
            System.out.println("2. Add Staff");
            System.out.println("3. Show Manager Report");
            System.out.println("4. Show Staff Report");
            System.out.println("5. Create Department");
            System.out.println("6. Show Department");
            System.out.println("7. Exit to GUI Mode");
            System.out.println("8. Exit Program");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    manager = new Manager();
                    manager.takeInput();
                    System.out.println("Manager added successfully!");
                    break;

                case 2:
                    staff = new Staff();
                    staff.takeInput();
                    System.out.println("Staff added successfully!");
                    break;

                case 3:
                    if (manager != null) {
                        manager.displayInfo();
                        ((WorkReport) manager).generateReport();
                        ((WorkReport) manager).submitReport();
                    } else {
                        System.out.println("No Manager Found!");
                    }
                    break;

                case 4:
                    if (staff != null) {
                        staff.displayInfo();
                        ((WorkReport) staff).generateReport();
                        ((WorkReport) staff).submitReport();
                    } else {
                        System.out.println("No Staff Found!");
                    }
                    break;

                case 5:
                    System.out.println("<|---Create New Department---|>");
                    dept.takeDepartmentInput();
                    System.out.println("Department Created Successfully!");
                    break;

                case 6:
                    dept.showDetails();
                    break;
                    
                case 7:
                    System.out.println("Switching to GUI Mode...");
                    new OfficeManagementSystemGUI();
                    return;
                    
                case 8:
                    System.out.println("\n<|=== Program Closed Successfully ===|>");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
