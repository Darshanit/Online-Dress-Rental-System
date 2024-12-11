import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

// Dress Class
class Dress implements Serializable {
    private int dressID;
    private String name;
    private String description;
    private String size;
    private double rentalPrice;
    private boolean availability;
    private String designer;

    public Dress(int dressID, String name, String description, String size, double rentalPrice, boolean availability, String designer) {
        this.dressID = dressID;
        this.name = name;
        this.description = description;
        this.size = size;
        this.rentalPrice = rentalPrice;
        this.availability = availability;
        this.designer = designer;
    }

    public int getDressID() { return dressID; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getSize() { return size; }
    public double getRentalPrice() { return rentalPrice; }
    public boolean isAvailability() { return availability; }
    public String getDesigner() { return designer; }
    public void setAvailability(boolean availability) { this.availability = availability; }

    public void displayDressDetails() {
        System.out.println("Dress ID: " + dressID);
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Size: " + size);
        System.out.println("Rental Price: " + rentalPrice);
        System.out.println("Availability: " + (availability ? "Available" : "Not Available"));
        System.out.println("Designer: " + designer);
    }
}

class FormalDress extends Dress {
    private String occasion;

    public FormalDress(int dressID, String name, String description, String size, double rentalPrice, boolean availability, String designer, String occasion) {
        super(dressID, name, description, size, rentalPrice, availability, designer);
        this.occasion = occasion;
    }

    public String getOccasion() { return occasion; }

    @Override
    public void displayDressDetails() {
        super.displayDressDetails();
        System.out.println("Occasion: " + occasion);
    }
}

class CasualDress extends Dress {
    private String season;

    public CasualDress(int dressID, String name, String description, String size, double rentalPrice, boolean availability, String designer, String season) {
        super(dressID, name, description, size, rentalPrice, availability, designer);
        this.season = season;
    }

    public String getSeason() { return season; }

    @Override
    public void displayDressDetails() {
        super.displayDressDetails();
        System.out.println("Season: " + season);
    }
}

// Rental Class
class Rental implements Serializable {
    private int rentalID;
    private int dressID;
    private int customerID;
    private Date rentalStartDate;
    private Date rentalEndDate;
    private double totalCost;

    public Rental(int rentalID, int dressID, int customerID, Date rentalStartDate, Date rentalEndDate) {
        this.rentalID = rentalID;
        this.dressID = dressID;
        this.customerID = customerID;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
        calculateTotalCost();
    }

    private void calculateTotalCost() {
        long duration = rentalEndDate.getTime() - rentalStartDate.getTime();
        long days = Math.max(1, duration / (1000 * 60 * 60 * 24)); // At least one day
        totalCost = days * 50; // Flat rate of 50 per day
    }

    public int getRentalID() { return rentalID; }
    public int getDressID() { return dressID; }
    public int getCustomerID() { return customerID; }
    public Date getRentalStartDate() { return rentalStartDate; }
    public Date getRentalEndDate() { return rentalEndDate; }
    public double getTotalCost() { return totalCost; }

    public void displayRentalDetails() {
        System.out.println("Rental ID: " + rentalID);
        System.out.println("Dress ID: " + dressID);
        System.out.println("Customer ID: " + customerID);
        System.out.println("Rental Start Date: " + rentalStartDate);
        System.out.println("Rental End Date: " + rentalEndDate);
        System.out.println("Total Cost: " + totalCost);
    }
}

// Customer Class
class Customer implements Serializable {
    private int customerID;
    private String name;
    private String email;
    private String contactNumber;
    private String address;

    public Customer(int customerID, String name, String email, String contactNumber, String address) {
        this.customerID = customerID;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    public int getCustomerID() { return customerID; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getContactNumber() { return contactNumber; }
    public String getAddress() { return address; }

    public void displayCustomerDetails() {
        System.out.println("Customer ID: " + customerID);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Contact Number: " + contactNumber);
        System.out.println("Address: " + address);
    }
}

// RentalManager Class
class RentalManager implements Serializable {
    private List<Dress> dresses = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Rental> rentals = new ArrayList<>();

    public void addDress(Dress dress) { dresses.add(dress); }
    public void registerCustomer(Customer customer) { customers.add(customer); }
    public void createRental(Rental rental) { rentals.add(rental); }

    public void viewAvailableDresses() {
        for (Dress dress : dresses) {
            if (dress.isAvailability()) {
                dress.displayDressDetails();
            }
        }
    }

    public void displayRentalInfo() {
        for (Rental rental : rentals) {
            rental.displayRentalDetails();
        }
    }

    public void saveDataToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Dresses:");
            for (Dress dress : dresses) {
                writer.println("------------------------------------------------");
                writer.println("Dress ID: " + dress.getDressID());
                writer.println("Name: " + dress.getName());
                writer.println("Description: " + dress.getDescription());
                writer.println("Size: " + dress.getSize());
                writer.println("Rental Price: " + dress.getRentalPrice());
                writer.println("Availability: " + (dress.isAvailability() ? "Available" : "Not Available"));
                writer.println("Designer: " + dress.getDesigner());
                if (dress instanceof FormalDress) {
                    writer.println("Occasion: " + ((FormalDress) dress).getOccasion());
                } else if (dress instanceof CasualDress) {
                    writer.println("Season: " + ((CasualDress) dress).getSeason());
                }
                writer.println("------------------------------------------------");
            }

            writer.println("\nCustomers:");
            for (Customer customer : customers) {
                writer.println("------------------------------------------------");
                writer.println("Customer ID: " + customer.getCustomerID());
                writer.println("Name: " + customer.getName());
                writer.println("Email: " + customer.getEmail());
                writer.println("Contact Number: " + customer.getContactNumber());
                writer.println("Address: " + customer.getAddress());
                writer.println("------------------------------------------------");
            }

            writer.println("\nRentals:");
            for (Rental rental : rentals) {
                writer.println("------------------------------------------------");
                writer.println("Rental ID: " + rental.getRentalID());
                writer.println("Dress ID: " + rental.getDressID());
                writer.println("Customer ID: " + rental.getCustomerID());
                writer.println("Rental Start Date: " + rental.getRentalStartDate());
                writer.println("Rental End Date: " + rental.getRentalEndDate());
                writer.println("Total Cost: " + rental.getTotalCost());
                writer.println("------------------------------------------------");
            }
        } catch (IOException e) {
            System.out.println("Error: Unable to save data to file.");
        }
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RentalManager manager = new RentalManager();

        while (true) {
            System.out.println("Welcome to the Online Dress Rental System");
            System.out.println("1. Add Dress");
            System.out.println("2. Add Customer");
            System.out.println("3. Create Rental");
            System.out.println("4. View Available Dresses");
            System.out.println("5. View Rental Information");
            System.out.println("6. Save Data");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Dress ID: ");
                    int dressID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Dress Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter Size: ");
                    String size = scanner.nextLine();
                    System.out.print("Enter Rental Price: ");
                    double rentalPrice = scanner.nextDouble();
                    System.out.print("Is Available (true/false): ");
                    boolean availability = scanner.nextBoolean();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Designer: ");
                    String designer = scanner.nextLine();
                    System.out.print("Enter Dress Type (1 for Formal, 2 for Casual): ");
                    int type = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (type == 1) {
                        System.out.print("Enter Occasion: ");
                        String occasion = scanner.nextLine();
                        manager.addDress(new FormalDress(dressID, name, description, size, rentalPrice, availability, designer, occasion));
                    } else {
                        System.out.print("Enter Season: ");
                        String season = scanner.nextLine();
                        manager.addDress(new CasualDress(dressID, name, description, size, rentalPrice, availability, designer, season));
                    }
                    break;
                case 2:
                    System.out.print("Enter Customer ID: ");
                    int customerID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Customer Name: ");
                    String customerName = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Contact Number: ");
                    String contactNumber = scanner.nextLine();
                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();
                    manager.registerCustomer(new Customer(customerID, customerName, email, contactNumber, address));
                    break;
                case 3:
                    System.out.print("Enter Rental ID: ");
                    int rentalID = scanner.nextInt();
                    System.out.print("Enter Dress ID: ");
                    int rentalDressID = scanner.nextInt();
                    System.out.print("Enter Customer ID: ");
                    int rentalCustomerID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Rental Start Date (dd-MM-yyyy): ");
                    String startDateStr = scanner.nextLine();
                    System.out.print("Enter Rental End Date (dd-MM-yyyy): ");
                    String endDateStr = scanner.nextLine();
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        Date startDate = sdf.parse(startDateStr);
                        Date endDate = sdf.parse(endDateStr);
                        manager.createRental(new Rental(rentalID, rentalDressID, rentalCustomerID, startDate, endDate));
                    } catch (ParseException e) {
                        System.out.println("Error: Invalid date format.");
                    }
                    break;
                case 4:
                    manager.viewAvailableDresses();
                    break;
                case 5:
                    manager.displayRentalInfo();
                    break;
                case 6:
                    System.out.print("Enter filename to save data: ");
                    String filename = scanner.nextLine();
                    manager.saveDataToFile(filename);
                    break;
                case 7:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
