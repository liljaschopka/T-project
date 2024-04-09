package flight;

import java.util.List;
public interface flightControllerInterface {
    Flight searchFlight(int flightID);

    void changeLuggageOption(int bookingID, LuggageOption luggageOption);

    void changeSeat(int bookingID, int newSeatNumber);

    List<Flight> filterByPrice(int minPrice, int maxPrice);
}
