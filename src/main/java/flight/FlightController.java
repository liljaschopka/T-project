package flight;

import model.Flight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FlightController {

    private final FlightInventoryInterface flightInventory;
    private final Map<String, User> users;

    public FlightController(FlightInventoryInterface flightInventory) {
        this.flightInventory = flightInventory;
        this.users = new HashMap<>();
    }

    public void bookFlight(int flightID) {
        Flight flight = flightInventory.searchFlightByID(flightID);
        if (flight != null) {
            if (flight.bookSeat()) {
                System.out.println("Seat booked successfully on Flight ID: " + flightID);
            } else {
                System.out.println("No seats available on Flight ID: " + flightID);
            }
        } else {
            System.out.println("Flight ID not found: " + flightID);
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nAvailable commands: ");
            System.out.println("1 - Add Flight");
            System.out.println("2 - Remove Flight");
            System.out.println("3 - Search Flights");
            System.out.println("4 - Book Seat on Flight");
            System.out.println("0 - Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addFlight(scanner);
                    break;
                case 2:
                    removeFlight(scanner);
                    break;
                case 3:
                    searchFlights(scanner);
                    break;
                case 4:
                    System.out.print("Enter Flight ID to book a seat: ");
                    int flightID = scanner.nextInt();
                    bookFlight(flightID);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addFlight(Scanner scanner) {
        System.out.println("Enter flight details (ID, Origin, Destination, Duration, Date, Departure Time, Arrival Time, Price, Total Seats, Available Seats): ");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Origin: ");
        String origin = scanner.nextLine();

        System.out.print("Destination: ");
        String destination = scanner.nextLine();

        System.out.print("Duration (minutes): ");
        int duration = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.print("Departure Time (YYYY-MM-DDTHH:MM): ");
        String departureTimeStr = scanner.nextLine();
        LocalDateTime departureTime = LocalDateTime.parse(departureTimeStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        System.out.print("Arrival Time (YYYY-MM-DDTHH:MM): ");
        String arrivalTimeStr = scanner.nextLine();
        LocalDateTime arrivalTime = LocalDateTime.parse(arrivalTimeStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        System.out.print("Price: ");
        int price = scanner.nextInt();

        System.out.print("Total Seats: ");
        int totalSeats = scanner.nextInt();

        System.out.print("Available Seats: ");
        int availableSeats = scanner.nextInt();

        Flight flight = new Flight(id, origin, destination, duration, date, departureTime, arrivalTime, price, totalSeats, availableSeats);
        flightInventory.addFlight(flight);
        System.out.println("Flight added successfully.");
    }


    private void removeFlight(Scanner scanner) {
        System.out.print("Enter flight ID to remove: ");
        int id = scanner.nextInt();
        if (flightInventory.removeFlight(id)) {
            System.out.println("Flight removed successfully.");
        } else {
            System.out.println("Flight not found.");
        }
    }

    private void searchFlights(Scanner scanner) {
        System.out.print("Enter origin: ");
        String origin = scanner.nextLine();
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();
        System.out.print("Enter departure date (YYYY-MM-DD): ");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        String departureDateStr = scanner.nextLine();
        LocalDate departureDate = LocalDate.parse(departureDateStr, formatter);

        List<Flight> flights = flightInventory.searchFlight(origin, destination, departureDate);
        if (flights.isEmpty()) {
            System.out.println("No flights found.");
        } else {
            flights.forEach(flight -> System.out.println(flight.toString()));
        }
    }
}
