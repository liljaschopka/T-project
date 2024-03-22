package controllers;

import model.*;

import java.time.LocalDateTime;
import java.util.Comparator;
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

    private HotelControllerInterface hotelController;
    private FlightController flightController;
    private DayTripController dayTripController;
    private BookingController bookingController;

    private Cart cart = new Cart();

    public PackageController(User user, String origin, String destination,
                             LocalDateTime checkIn, LocalDateTime checkOut, int persons,
                             HotelControllerInterface hotelController, FlightController flightController,
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


    public List<Hotel> findAvailableHotels() {

        if (!validDates(checkIn, checkOut)) {
            throw new IllegalArgumentException("Invalid dates");
        }

        if (!validODP(origin, destination, persons)) {
            throw new IllegalArgumentException("Invalid origin, destination or persons");
        }

        List<Hotel> hotels = hotelController.searchForHotels(destination, checkIn, checkOut, persons);

        if (hotels.isEmpty()) {
            throw new IllegalArgumentException("No hotels found");
        }

        hotels.sort(Comparator.comparingInt(Hotel::getPrice));

        return hotels;
    }

    public List<HotelRoom> getAvailableRooms(Hotel hotel) {
        List<HotelRoom> availabeRooms = hotelController.getAvailableRooms(hotel, persons);

        if (availabeRooms.isEmpty()) {
            throw new IllegalArgumentException("No rooms found");
        }

        return availabeRooms;
    }

    public List<Flight> findAvailableFlights() {
        if (!validDates(checkIn, checkOut)) {
            throw new IllegalArgumentException("Invalid dates");
        }
        if (!validODP(origin, destination, persons)) {
            throw new IllegalArgumentException("Invalid origin, destination or persons");
        }

        List<Flight> flights = flightController.searchForFlights(destination, origin, checkIn, checkOut, persons);

        if (flights.isEmpty()) {
            throw new IllegalArgumentException("No flights found");
        }

        flights.sort(Comparator.comparingInt(Flight::getPrice));

        return flights;

    }

    public List<Daytrip> findAvailableDayTrips() {
        if (!validDates(checkIn, checkOut)) {
            throw new IllegalArgumentException("Invalid dates");
        }
        if (!validODP(origin, destination, persons)) {
            throw new IllegalArgumentException("Invalid origin, destination or persons");
        }

        List<Daytrip> daytrips = dayTripController.searchForDaytrips(destination, checkIn, checkOut, persons);

        if (daytrips.isEmpty()) {
            throw new IllegalArgumentException("No daytrips found");
        }

        daytrips.sort(Comparator.comparingInt(Daytrip::getPrice));

        return daytrips;

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
