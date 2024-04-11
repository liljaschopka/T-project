package flight;

import java.util.List;
public interface bookingControllerInterface {

    Booking createBooking(int flightID, int[] seatNumbers);

    List<Flight> searchFlight(String origin, String destination);

    Flight searchFlightById(int flightID);
}
