package flight;

import java.util.List;
public class BookingController {
    private FlightInventory flightInventory;

    public BookingController(FlightInventory flightInventory) {
        this.flightInventory = flightInventory;
    }

    public Booking createBooking(int flightID, int[] seatNumbers) {
        Flight flight = flightInventory.searchFlight(flightID);
        if (flight == null) {
            // Handle case where flight is not found
            System.out.println("Flight not found");
            return null;
        }
        Booking booking = new Booking(flight, seatNumbers);
        return booking;
    }

    public List<Flight> searchFlight(String origin, String destination) {
        return flightInventory.searchFlight(origin, destination);
    }

    public Flight searchFlightById(int flightID) {
        return flightInventory.searchFlight(flightID);
    }
}
