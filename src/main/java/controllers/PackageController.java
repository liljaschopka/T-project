package controllers;

import model.*;

import java.time.LocalDateTime;
import java.util.List;

/******************************************************************************
 *  Nafn    : Lilja Kolbrún Schopka
 *  T-póstur: lks17@hi.is
 *
 *  Lýsing  :
 *
 *
 *
 *
 *****************************************************************************/
public class PackageController {
    private User user;
    private String origin;
    private String destination;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private int persons;

    private HotelController hotelController;
    private FlightController flightController;
    private DayTripController dayTripController;
    private BookingController bookingController;

    private Cart cart = new Cart();

    public PackageController(User user, String origin, String destination,
                             LocalDateTime checkIn, LocalDateTime checkOut, int persons,
                             HotelController hotelController, FlightController flightController,
                             DayTripController dayTripController, BookingController bookingController) {
        this.user = user;
        this.origin = origin;
        this.destination = destination;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.persons = persons;
        this.hotelController = hotelController;
        this.flightController = flightController;
        this.dayTripController = dayTripController;
        this.bookingController = bookingController;

    }

    private boolean validDates(LocalDateTime checkIn, LocalDateTime checkOut) {
        return checkIn.isBefore(checkOut);
    }

    private boolean validODP(String origin, String destination, int persons) {
        return origin != null && destination != null && persons != 0;
    }

    public void setUser(String name, String email, PaymentInfo paymentInfo, Booking[] bookings) {
        this.user = new User(name, email, paymentInfo, bookings);
    }


    public List<Hotel> findAvailableHotels(LocalDateTime checkIn, LocalDateTime checkOut, int persons,
                                           String destination) {
        // implementa
        return null;
    }

    public List<Flight> findAvailableFlights(LocalDateTime checkIn, LocalDateTime checkOut, int persons,
                                             String destination, String origin, int capacity) {
        // implementa
        return null;

    }

    public List<Daytrip> findAvailableDayTrips(LocalDateTime checkIn, LocalDateTime checkOut,
                                               int capacity, String location) {
        // implementa
        return null;

    }

    /* public static Cart getCart() {
        return cart;
    }

    public static User getUser() {
        return user;
    } */

    public static void main(String[] args) {

    }
}
