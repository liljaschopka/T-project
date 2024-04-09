package flight;

import java.util.List;

public interface FlightInventory {
    Flight searchFlight(int flightID);
    void addFlight(Flight flight);

    boolean removeFlight(int flightID);

    List<Flight> searchFlight(String origin, String destination);
}
