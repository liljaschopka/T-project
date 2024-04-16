package flight;

import java.time.LocalDate;
import java.util.List;

public interface FlightInventoryInterface {
    void addFlight(Flight flight);

    boolean removeFlight(int flightID);

    List<Flight> searchFlight(String origin, String destination, LocalDate date);

    Flight searchFlightByID(int flightID);
}
