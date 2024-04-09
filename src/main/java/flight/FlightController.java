package flight;

import java.util.List;

public class FlightController implements flightControllerInterface {
//TODO fá þennan controller frá flight
    public FlightController() {
        return;
    }

    @Override
    public flight.Flight searchFlight(int flightID) {
        return null;
    }

    @Override
    public void changeLuggageOption(int bookingID, LuggageOption luggageOption) {
        return;
    }

    @Override
    public void changeSeat(int bookingID, int newSeatNumber) {
       return;
    }

    @Override
    public List<Flight> filterByPrice(int minPrice, int maxPrice) {
        return null;
    }

    public static void main(String[] args) {
        FlightController controller = new FlightController();
    }
}
