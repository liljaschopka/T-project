package flight;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StubFlightInventory implements FlightInventory {
    private Map<Integer, Flight> flights = new HashMap<>();

    public void addStubFlight(Flight flight) {
        flights.put(flight.getFlightID(), flight);
    }

    @Override
    public Flight searchFlight(int flightID) {
        return flights.get(flightID);
    }

    @Override
    public void addFlight(Flight flight) {

    }

    @Override
    public boolean removeFlight(int flightID) {
        return false;
    }

    @Override
    public List<Flight> searchFlight(String origin, String destination) {
        return null;
    }
}
