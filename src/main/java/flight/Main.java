package flight;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            System.out.println("Please provide origin, destination, and date in the format 'YYYY-MM-DD'.");
            return;
        }

        String filePath = "Flights.txt";
        FlightInventory flightInventory = new FlightInventory(filePath);

        String origin = args[0];
        String destination = args[1];
        LocalDate date;
        try {
            date = LocalDate.parse(args[2], DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use 'YYYY-MM-DD'.");
            return;
        }

        List<Flight> foundFlights = flightInventory.searchFlight(origin, destination, date);

        if (foundFlights.isEmpty()) {
            System.out.println("No flights found.");
        } else {
            for (Flight flight : foundFlights) {
                System.out.println(flight);
                // Assume booking the first found flight for simplicity
                if (flightInventory.bookFlight(flight.getFlightID())) {
                    System.out.println("Flight booked successfully. Remaining seats: " + flight.getAvailableSeats());
                } else {
                    System.out.println("Failed to book flight. No seats available.");
                }
                break; // Break after attempting to book the first flight
            }
        }
    }
}

