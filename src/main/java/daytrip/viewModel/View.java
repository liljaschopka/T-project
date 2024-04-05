package daytrip.viewModel;

import daytrip.controller.TourController;
import daytrip.controller.ReservationController;
import daytrip.controller.CustomerController;
import daytrip.dal.CustomerDAL;
import daytrip.dal.ReservationDAL;
import daytrip.dal.TourDAL;
import daytrip.model.Customer;
import daytrip.model.Reservation;
import daytrip.model.Tour;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Optional;
import java.util.List;


/**
 * This class represents the user interface of the day trip booking application.
 * It allows users to interact with the application through a console-based menu system.
 */
public class View {
    private TourController tourController;
    private ReservationController reservationController;
    private CustomerController customerController;
    private Scanner scanner;


    /**
     * Constructs a View object with the necessary controllers for managing tours, reservations, and customers.
     *
     * @param tourController          A controller for tour-related operations.
     * @param reservationController   A controller for reservation-related operations.
     * @param customerController      A controller for customer-related operations.
     */
    public View(TourController tourController, ReservationController reservationController, CustomerController customerController) {
        this.tourController = tourController;
        this.reservationController = reservationController;
        this.customerController = customerController;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the user interaction loop, displaying a menu of options until the user chooses to exit.
     */
    public void run() {
        boolean exit = false;
        while (!exit) {
            System.out.println("-".repeat(50));
            System.out.println("Select an option:");
            System.out.println("1. View available tours");
            System.out.println("2. View all reservations");
            System.out.println("3. View all customers");
            System.out.println("4. View reservation details");
            System.out.println("5. View number of participants in a reservation");
            System.out.println("6. Search for a specific tour");
            System.out.println("7. Make a booking");
            System.out.println("8. Update customer information");
            System.out.println("9. Update reservation");
            System.out.println("10. Check if a reservation exists");
            System.out.println("11. Cancel reservation");
            System.out.println("12. Add a new customer");
            System.out.println("13. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAvailableTours();
                    break;
                case 2:
                    viewAllReservations();
                    break;
                case 3:
                    viewAllCustomers();
                    break;
                case 4:
                    viewReservationDetails();
                    break;
                case 5:
                    viewNumberOfParticipants();
                    break;
                case 6:
                    searchSpecificTour();
                    break;
                case 7:
                    makeBooking();
                    break;
                case 8:
                    updateCustomerInformation();
                    break;
                case 9:
                    updateReservation();
                    break;
                case 10:
                    checkReservationExists();
                    break;
                case 11:
                    cancelReservation();
                    break;
                case 12:
                    addNewCustomer();
                    break;
                case 13:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Displays all available tours that can be booked.
     */
    private void viewAvailableTours() {
        System.out.println("Available tours are:");
        List<Tour> tours = tourController.getAvailableTours();

        if (tours.isEmpty()) {
            System.out.println("No available tours at the moment.");
            return;
        }

        for (Tour tour : tours) {
            System.out.println("ID: " + tour.getTourID() + ", Name: " + tour.getName() +
                    ", Location: " + tour.getLocation() + ", Date: " + tour.getDate() +
                    ", Available Seats: " + tour.getMaxParticipants() + ", Price: $" + tour.getPrice());
        }
    }

    /**
     * Displays a specific tour by search criteria: location, date and participants.
     */
    private void searchSpecificTour() {
        System.out.println("Enter location:");
        String location = scanner.nextLine().trim();

        System.out.println("Enter date (yyyy-MM-dd):");
        LocalDate date = LocalDate.parse(scanner.nextLine().trim(), DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.println("Enter number of participants:");
        int participants = Integer.parseInt(scanner.nextLine().trim());

        List<Tour> foundTours = tourController.searchTours(location, date, participants);

        if (foundTours.isEmpty()) {
            System.out.println("No tours found matching the criteria.");
        } else {
            System.out.println("Found tours:");
            foundTours.forEach(tour -> {
                System.out.println("ID: " + tour.getTourID() + ", Name: " + tour.getName() +
                        ", Location: " + tour.getLocation() + ", Date: " + tour.getDate() +
                        ", Max Participants: " + tour.getMaxParticipants() + ", Price: $" + tour.getPrice());
            });
        }
    }


    /**
     * Displays all reservations in the system.
     */
    private void viewAllReservations() {
        System.out.println("All reservations:");
        List<Reservation> reservations = reservationController.getAllReservations();

        if (reservations.isEmpty()) {
            System.out.println("There are no reservations.");
            return;
        }

        for (Reservation reservation : reservations) {
            System.out.println("Reservation ID: " + reservation.getReservationID() +
                    ", Customer ID: " + reservation.getCustomerID() +
                    ", Tour ID: " + reservation.getTourID() +
                    ", Number of Participants: " + reservation.getNumberOfParticipants() +
                    ", Date Booked: " + reservation.getDateBooked());
        }
    }

    /**
     * Displays all customers in the system.
     */
    private void viewAllCustomers(){
        System.out.println("All customers:");
        List<Customer> customers = customerController.getAllCustomers();

        if (customers.isEmpty()){
            System.out.println("There are no customers.");
            return;
        }

        for (Customer customer : customers){
            System.out.println("Customer ID: " + customer.getId() +
                    ", Name: " + customer.getName() +
                    ", Email: " + customer.getEmail());
        }
    }

    /**
     * Updates the details of an existing reservation.
     * The user is prompted to enter a reservation ID, a new number of participants, and a new reservation date.
     * If the reservation exists and the update is successful, a confirmation message is displayed;
     * otherwise, an error message is shown.
     */
    private void updateReservation() {
        System.out.println("Enter reservation ID to update:");
        int reservationId = Integer.parseInt(scanner.nextLine());

        if (!reservationController.isReservationExists(reservationId)) {
            System.out.println("Reservation does not exist.");
            return;
        }

        System.out.println("Enter new number of participants:");
        int numberOfParticipants = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter new date of reservation (yyyy-MM-dd):");
        LocalDate date = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);

        boolean success = reservationController.updateReservation(reservationId, numberOfParticipants, date);
        if (success) {
            System.out.println("Reservation updated successfully.");
        } else {
            System.out.println("Failed to update reservation.");
        }
    }

    /**
     * Displays the details of a specific reservation.
     * The user is prompted to enter a reservation ID to view its details.
     * If the reservation exists, its details are displayed;
     * otherwise, a message indicating that no reservation was found is shown.
     */
    private void viewReservationDetails() {
        System.out.println("Enter reservation ID to view details:");
        int reservationId = Integer.parseInt(scanner.nextLine());

        Reservation reservation = reservationController.getReservationDetails(reservationId);
        if (reservation != null) {
            System.out.println("Reservation Details:");
            System.out.println("Reservation ID: " + reservation.getReservationID());
            System.out.println("Customer ID: " + reservation.getCustomerID());
            System.out.println("Tour ID: " + reservation.getTourID());
            System.out.println("Number of Participants: " + reservation.getNumberOfParticipants());
            System.out.println("Date Booked: " + reservation.getDateBooked());
        } else {
            System.out.println("No reservation found with the provided ID.");
        }
    }

    /**
     * Checks if a reservation exists.
     * The user is prompted to enter a reservation ID to check for its existence.
     * A message is displayed indicating whether the reservation exists or not.
     */
    private void checkReservationExists() {
        System.out.println("Enter reservation ID to check existence:");
        int reservationId = Integer.parseInt(scanner.nextLine());

        boolean exists = reservationController.isReservationExists(reservationId);
        if (exists) {
            System.out.println("Reservation exists.");
        } else {
            System.out.println("Reservation does not exist.");
        }
    }


    /**
     * Displays the number of participants for a specific reservation.
     * The user is prompted to enter a reservation ID to view the number of participants.
     * If the reservation exists, the number of participants is displayed;
     * otherwise, a message indicating that the reservation does not exist is shown.
     */
    private void viewNumberOfParticipants() {
        System.out.println("Enter reservation ID to view number of participants:");
        int reservationId = Integer.parseInt(scanner.nextLine());

        if (!reservationController.isReservationExists(reservationId)) {
            System.out.println("Reservation does not exist.");
            return;
        }

        int numberOfParticipants = reservationController.getNumberOfParticipants(reservationId);
        System.out.println("Number of participants for reservation ID " + reservationId + ": " + numberOfParticipants);
    }

    /**
     * Handles the process of making a new booking.
     */
    private void makeBooking() {
        System.out.println("Enter tour ID:");
        int tourId = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter booking date (yyyy-MM-dd):");
        String dateString = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.println("Enter number of participants:");
        int participants = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter customer name:");
        String customerName = scanner.nextLine();

        System.out.println("Enter customer email:");
        String customerEmail = scanner.nextLine();

        boolean success = reservationController.makeReservation(tourId, customerName, customerEmail, date, participants, Optional.empty());
        if (success) {
            System.out.println("Booking successful.");
        } else {
            System.out.println("Booking failed. Please try again or check the tour availability.");
        }
    }

    private void addNewCustomer() {
        System.out.println("Enter customer name:");
        String name = scanner.nextLine();

        System.out.println("Enter customer email:");
        String email = scanner.nextLine();

        boolean success = customerController.addNewCustomer(name, email, null); // Adjust according to your constructor

        if (success) {
            System.out.println("New customer added successfully.");
        } else {
            System.out.println("Failed to add new customer.");
        }
    }

    /**
     * Handles the updating of customer information.
     */
    private void updateCustomerInformation() {
        System.out.println("Enter customer ID:");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter new name:");
        String newName = scanner.nextLine();

        System.out.println("Enter new email:");
        String newEmail = scanner.nextLine();

        boolean success = customerController.updateCustomerDetails(customerId, newName, newEmail);
        if (success) {
            System.out.println("Customer information updated successfully.");
        } else {
            System.out.println("Failed to update customer information.");
        }
    }

    /**
     * Handles the cancellation of an existing reservation.
     */
    private void cancelReservation() {
        System.out.println("Enter reservation ID:");
        int reservationId = scanner.nextInt();
        scanner.nextLine();

        boolean success = reservationController.cancelReservation(reservationId);
        if (success) {
            System.out.println("Reservation canceled successfully.");
        } else {
            System.out.println("Failed to cancel reservation. Please check the reservation ID and try again.");
        }
    }


    public static void main(String[] args) {
        TourDAL tourDal = new TourDAL();
        ReservationDAL reservationDal = new ReservationDAL();
        CustomerDAL customerDal = new CustomerDAL();

        TourController tourController = new TourController(tourDal);
        ReservationController reservationController = new ReservationController(reservationDal, tourController);
        CustomerController customerController = new CustomerController(customerDal, reservationDal);

        View view = new View(tourController, reservationController, customerController);
        view.run();
    }
}
