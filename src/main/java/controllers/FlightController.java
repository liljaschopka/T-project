package controllers;

import flight.FlightInventoryInterface;
import model.Flight;

import java.time.LocalDate;
import java.util.List;

public class FlightController implements FlightInventoryInterface {
    // eigum við ekki að eyða þessu bara?
    public void addFlight(Flight flight) {
        
    }

    public boolean removeFlight(int flightID) {
        return false;
    }

    public List<Flight> searchFlight(String origin, String destination, LocalDate date) {
        return null;
    }

    public Flight searchFlightByID(int flightID) {
        return null;
    }
}
