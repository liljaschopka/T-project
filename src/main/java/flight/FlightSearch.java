package flight;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FlightSearch {
    public Flight findFlightById(String flightID, String filepath) {
        try {
            Scanner scanner = new Scanner(new File(filepath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                // No need to parse the ID as an integer if it's a string like "XXnnn"
                if (data[0].equals(flightID)) {
                    // Parse other data as needed and return a new Flight object
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filepath);
            e.printStackTrace();
        }
        return null; // No flight found with the given ID
    }

    public static void main(String[] args) {
        FlightSearch search = new FlightSearch();
        // Replace '1' with a String that matches the flight ID format in your file
        Flight flight = search.findFlightById("AA123", "flights.txt");
        if (flight != null) {
            System.out.println(flight.toString());
        } else {
            System.out.println("No flight found with the given ID.");
        }
    }
}


