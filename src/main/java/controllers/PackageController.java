package controllers;

import daytrip.controller.TourController;
import daytrip.model.Tour;
import model.*;

import java.time.LocalDate;
import java.util.Collections;
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
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int persons;
    private Cart cart = new Cart();

    public PackageController(User user, String origin, String destination,
                             LocalDate checkIn, LocalDate checkOut, int persons) {
        this.user = user;
        this.origin = origin;
        this.destination = destination;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.persons = persons;
    }

    private boolean validDates(LocalDate checkIn, LocalDate checkOut) {
        return checkIn.isBefore(checkOut);
    }

    private boolean validODP(String origin, String destination, int persons) {
        return origin != null && destination != null && persons != 0;
    }

    public void setUser(String name, String email, PaymentInfo paymentInfo, List<String> bookingIDs) {
        this.user = new User(null, name, email, paymentInfo, bookingIDs);
    }


   /* public List<Hotel> findAvailableHotels(HotelControllerInterface hotelController) {

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

        //hotels.sort(Comparator.comparingInt(Hotel::getPrice));

        return hotels;
    }*/

    //þurfti að breyta findavailablehotels aðeins svo ég gæti keyrt með tóman lista af hotelum:
    public List<Hotel> findAvailableHotels(HotelControllerInterface hotelController) {
        if (!validDates(checkIn, checkOut)) {
            System.out.println("Invalid dates provided");
            return Collections.emptyList();
        }

        if (!validODP(origin, destination, persons)) {
            System.out.println("Invalid origin, destination, or number of persons");
            return Collections.emptyList();
        }

        List<Hotel> hotels = hotelController.searchForHotels(destination, checkIn, checkOut, persons);

        if (hotels == null || hotels.isEmpty()) {
            System.out.println("No hotels found for the specified criteria");
            return Collections.emptyList();
        }

        // hotels.sort(Comparator.comparingInt(Hotel::getPrice));

        return hotels;
    }


    public List<HotelRoom> getAvailableRooms(Hotel hotel, HotelControllerInterface hotelController) {
        List<HotelRoom> availabeRooms = hotelController.getAvailableRooms(hotel, persons);

        if (availabeRooms.isEmpty()) {
            throw new IllegalArgumentException("No rooms found");
        }

        availabeRooms.sort(Comparator.comparingInt(HotelRoom::getPrice));

        return availabeRooms;
    }

    public List<Flight> findAvailableFlights(FlightController flightController) {
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

    public List<Tour> findAvailableDayTrips(TourController tourController) {
        if (!validDates(checkIn, checkOut)) {
            throw new IllegalArgumentException("Invalid dates");
        }
        if (!validODP(origin, destination, persons)) {
            throw new IllegalArgumentException("Invalid origin, destination or persons");
        }

        List<Tour> foundTours = tourController.searchTours(destination, checkIn, persons);

        if (foundTours.isEmpty()) {
            throw new IllegalArgumentException("No daytrips found");
        }

        foundTours.sort(Comparator.comparingDouble(Tour::getPrice));

        return foundTours;

    }

    public void createBooking(BookingController bookingController) {
        if (user != null && !cart.isCartEmpty()) {
            bookingController.createHotelBooking(user, cart);
            bookingController.createFlightBooking(user, cart);
            bookingController.createDayTripBooking(user, cart);
        } else
            throw new IllegalArgumentException("You have to be logged in to make a booking");

    }

    public void clearSelection() {
        cart.emptyCart();
        origin = null;
        destination = null;
        checkIn = null;
        checkOut = null;
        persons = 0;
        // user = null; viljum við þetta?
    }

    public int getPersons() {
        return persons;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public Cart getCart() {
        return cart;
    }

    public User getUser() {
        return user;
    }

}

