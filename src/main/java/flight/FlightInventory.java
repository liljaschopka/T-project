package flight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class FlightInventory implements FlightInventoryInterface {
    private List<Flight> flights = new ArrayList<>();
    private Random random = new Random();

    public FlightInventory(String filePath) throws IOException {
        loadFlights(filePath);
    }

    @Override
    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    @Override
    public boolean removeFlight(int flightID) {
        return flights.removeIf(flight -> flight.getFlightID() == flightID);
    }

    public List<Flight> searchFlight(String origin, String destination, LocalDate date) {
        return flights.stream()
                .filter(flight -> flight.getOrigin().equalsIgnoreCase(origin) &&
                        flight.getDestination().equalsIgnoreCase(destination) &&
                        flight.getDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public Flight searchFlightByID(int flightID) {
        return flights.stream()
                .filter(flight -> flight.getFlightID() == flightID)
                .findFirst()
                .orElse(null); // Return null if no flight matches the ID
    }
    
    public boolean bookFlight(int flightID) {
        for (Flight flight : flights) {
            if (flight.getFlightID() == flightID) {
                return flight.bookSeat();
            }
        }
        return false; 
    }
    
    private void loadFlights(String filePath) throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
        if (is == null) {
            throw new FileNotFoundException("Resource not found: " + filePath);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        List<String> lines = reader.lines().collect(Collectors.toList());

        for (String line : lines) {
            String[] data = line.split(",");
            if (data.length != 7) {  // Ensure that there are exactly 7 elements in the split array
                System.out.println("Skipping invalid line: " + line);
                continue;
            }
            int flightID = Integer.parseInt(data[0]);
            String origin = data[1];
            String destination = data[2];
            int duration = Integer.parseInt(data[3]);
            LocalDateTime departureDateTime = LocalDateTime.parse(data[4], dateTimeFormatter);
            LocalDateTime arrivalDateTime = LocalDateTime.parse(data[5], dateTimeFormatter);
            int price = Integer.parseInt(data[6]);

            // Assuming totalSeats need to be defined; set it to a predetermined number or modify the data model to include it
            int totalSeats = 150;  // This could be a default value or parsed if included in data
            int availableSeats = totalSeats;  // Available seats starts equal to total seats

            Flight flight = new Flight(
                    flightID,
                    origin,
                    destination,
                    duration,
                    departureDateTime.toLocalDate(),
                    departureDateTime,
                    arrivalDateTime,
                    price,
                    totalSeats,
                    availableSeats
            );
            flights.add(flight);
        }
    }


}
