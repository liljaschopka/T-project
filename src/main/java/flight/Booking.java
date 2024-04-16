package flight;

import model.Flight;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Booking {
    private int bookingID;
    private int userID;
    private LocalDate bookingDate;
    private List<Flight> flights;

    public Booking(int bookingID, int userID, LocalDate bookingDate, List<Flight> flights) {
        this.bookingID = bookingID;
        this.userID = userID;
        this.bookingDate = bookingDate;
        this.flights = new ArrayList<>(flights);
    }

    public void cancelBooking() {
    }

    public void modifyBooking(List<Flight> newFlights) {
        this.flights = new ArrayList<>(newFlights);
    }

    public void addFlight(Flight newFlight) {
        this.flights.add(newFlight);
    }

    public void removeFlight(Flight flightToRemove) {
        this.flights.remove(flightToRemove);
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public List<Flight> getFlights() {
        return new ArrayList<>(flights);
    }

    public void setFlights(List<Flight> flights) {
        this.flights = new ArrayList<>(flights);
    }

}
