import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car {
    private String carId;
    private String brand;
    private String model;
    private double basePricePerDay;
    private boolean isAvailable;

    public Car(String carId, String brand, String model, double basePricePerDay, boolean isAvailable) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.basePricePerDay = basePricePerDay;
        this.isAvailable = isAvailable;
    }

    public String getCarId() {
        return carId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    // public double getBasePricePerDay() {
    // return basePricePerDay;
    // }

    public boolean isAvailable() {
        return isAvailable;
    }

    public double calculatePrice(int rentalDays) {
        return basePricePerDay * rentalDays;
    }

    public void rent() {
        isAvailable = false;
    }

    public void returnCar() {
        isAvailable = true;
    }
}

class Customer {
    private String customerId;
    private String name;

    Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

}

class Rental {
    private Car car;
    private Customer customer;
    private int days;

    Rental(Car car, Customer customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }

}

class CarRentalSystem {
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void rentCar(Car car, Customer customer, int days) {
        if (car.isAvailable()) {
            car.rent();
            rentals.add(new Rental(car, customer, days));
        } else {
            System.out.println("Car is not available for rent!");
        }
    }

    public void returnCar(Car car) {
        car.returnCar();
        Rental rentalToRemove = null;
        for (Rental rental : rentals) {
            if (rental.getCar() == car) {
                rentalToRemove = rental;
                break;

            }
        }
        if (rentalToRemove != null) {
            rentals.remove(rentalToRemove);
            System.out.println("Car returned Successfully.");
        } else {
            System.out.println("Car was not rented.");
        }
    }

    public void showCarStatus() {
    int available = 0;
    int rented = 0;

    for (Car car : cars) {
        if (car.isAvailable()) {
            available++;
        } else {
            rented++;
        }
    }

    System.out.println("\nCar Status:");
    System.out.println("Total Cars: " + cars.size());
    System.out.println("Available Cars: " + available);
    System.out.println("Rented Cars: " + rented);
}

// showRentedCars
public void showRentedCars() {
    System.out.println("\nRented Cars:");

    boolean found = false;

    for (Car car : cars) {
        if (!car.isAvailable()) {
            System.out.println(car.getCarId() + " - " + car.getBrand() + " " + car.getModel());
            found = true;
        }
    }

    if (!found) {
        System.out.println("No cars are currently rented.");
    }
}

    public void showRentalDetails() {
        if (rentals.isEmpty()) {
            System.out.println("\nNo cars are currently rented.");
            return;
        }

        System.out.println("\n<|--- Current Rental Details ---|>");

        for (Rental rental : rentals) {
            Car car = rental.getCar();
            Customer customer = rental.getCustomer();

            System.out.println("-----------------------------");
            System.out.println("Customer ID: " + customer.getCustomerId());
            System.out.println("Customer Name: " + customer.getName());
            System.out.println("Car ID: " + car.getCarId());
            System.out.println("Car: " + car.getBrand() + " " + car.getModel());
            System.out.println("Rental Days: " + rental.getDays());
        }
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=================================");
            System.out.println("|  Welcome to Car Rental System |");
            System.out.println("=================================");
            System.out.println("1.Rent a Car");
            System.out.println("2.Return a Car");
            System.out.println("3.Show Rental Details");
            System.out.println("4.Show Car Status");
            System.out.println("5.Show Rented Cars");
            System.out.println("6.Exit");
            System.out.print("Enter your Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                System.out.println("\n<|-- Rent a Car --|>\n");
                System.out.print("Enter your name: ");
                String customerName = scanner.nextLine();

                System.out.println("\nAvailable Cars:");
                for (Car car : cars) {
                    if (car.isAvailable()) {
                        // System.out.println(car.getCarId() + " - " + car.getBrand() + " " + car.getModel());
                        System.out.println(car.getCarId() + " - " + car.getBrand() + " " + car.getModel()
                        + " ($" + car.calculatePrice(1) + " per day)");

                    }
                }

                System.out.println("\nEnter the car ID you want to rent: ");
                String carId = scanner.nextLine();

                System.out.println("Enter the number of days for rental: ");
                int rentalDays = scanner.nextInt();
                scanner.nextLine(); // consume newline

                Customer newCustomer = new Customer("CUS" + (customers.size() + 1), customerName);
                addCustomer(newCustomer);

                Car selectedCar = null;
                for (Car car : cars) {
                    if (car.getCarId().equals(carId) && car.isAvailable()) {
                        selectedCar = car;
                        break;
                    }
                }

                if (selectedCar != null) {
                    double totalPrice = selectedCar.calculatePrice(rentalDays);
                    System.out.println("\n<|---Rental Information---|>\n");
                    System.out.println("Customer ID: " + newCustomer.getCustomerId());
                    System.out.println("Customer Name: " + newCustomer.getName());
                    System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
                    System.out.println("Rental Days: " + rentalDays);
                    System.out.printf("Total Price: $%.2f%n", totalPrice);

                    System.out.println("\nConfirm Rental (YES/NO): ");
                    String confirm = scanner.nextLine();

                    if (confirm.equalsIgnoreCase("YES")) {
                        rentCar(selectedCar, newCustomer, rentalDays);
                        System.out.println("\ncar rented Successfully.");
                    } else {
                        System.out.println("\nRental Canceled.");
                    }
                } else {
                    System.out.println("\nInvalid car selection or car not available for rent.");
                }

            } else if (choice == 2) {
                System.out.println("\n<|---Return a Car---|>");
                System.out.print("Enter the car ID you want to return: ");
                String carId = scanner.nextLine();

                Car carToReturn = null;
                for (Car car : cars) {
                    if (car.getCarId().equals(carId) && !car.isAvailable()) {
                        carToReturn = car;
                        break;
                    }
                }
                if (carToReturn != null) {
                    Customer customer = null;
                    for (Rental rental : rentals) {
                        if (rental.getCar() == carToReturn) {
                            customer = rental.getCustomer();
                            break;
                        }
                    }

                    if (customer != null) {
                        returnCar(carToReturn);
                        System.out.println("Car returned successfully by " + customer.getName());
                    } else {
                        System.out.println("Car was not rented or rental information is missing.");
                    }
                } else {
                    System.out.println("Invalid car ID or car is not rented.");
                }

            } else if (choice == 3) {
                showRentalDetails();
            } else if (choice == 4) {
                showCarStatus();
            } else if (choice == 5) {
                showRentedCars();
            } else if (choice == 6) {
                System.out.println("\nThank you for using the Car Rental System!");
                System.out.println("Exiting the System...");
                System.out.println();
                break;
            }
            else {
            System.out.println("Invalid choice. Please try again.");
            }

        }
    }
}

public class CarRentalManagementSystem {
    public static void main(String[] args) {
        CarRentalSystem rentalSystem = new CarRentalSystem();

        // Add the Cars to the System
        rentalSystem.addCar(new Car("C1", "Toyota", "Corolla", 50.0, true));
        rentalSystem.addCar(new Car("C2", "Honda", "Civic", 60.0, true));
        rentalSystem.addCar(new Car("C3", "Suzuki", "Swift", 40.0, true));
        // Start the Menu
        rentalSystem.menu();

    }
}
