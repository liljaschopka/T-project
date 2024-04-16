package controllers;

import com.example.tproject.DataManager;
import daytrip.controller.CustomerController;
import daytrip.controller.ReservationController;
import daytrip.controller.TourController;
import daytrip.dal.CustomerDAL;
import daytrip.dal.ReservationDAL;
import daytrip.dal.TourDAL;
import daytrip.model.Customer;
import daytrip.model.Reservation;
import daytrip.model.Tour;
import flight.BookingInventory;
import flight.Flight;
import flight.FlightInventory;
import model.Cart;
import model.User;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
public class BookingController {

    // controllers for Tour:
    private TourController tourController;
    private ReservationController reservationController;
    private CustomerController customerController;

    // controllers for Hotel:
    private hotel.controller.HotelController hotelController;

    // controllers for Flight:
    private flight.BookingController flightBookingController;

    public BookingController() {
        TourDAL tourDal = new TourDAL();
        ReservationDAL reservationDal = new ReservationDAL();
        tourController = new TourController(tourDal);
        reservationController = new ReservationController(reservationDal, tourController);
        hotelController = new hotel.controller.HotelController();
        CustomerDAL customerDAL = new CustomerDAL();
        customerController = new CustomerController(customerDAL, reservationDal);
        BookingInventory bookingInventory = new BookingInventory();
        FlightInventory flightInventory = null;
        try {
            flightInventory = new FlightInventory("Flights.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        flightBookingController = new flight.BookingController(bookingInventory, flightInventory);
    }

    /**
     * Creates a hotel booking in the hotel booking table
     *
     * @param user     the user making the booking
     * @param cart     the cart containing the hotel rooms to be booked
     * @param checkIn  the check-in date
     * @param checkOut the check-out date
     * @param persons  the number of persons
     */
    public void createHotelBooking(User user, Cart cart, LocalDate checkIn, LocalDate checkOut, int persons) {

        System.out.println("Creating a hotel booking for user: " + user.getName());
        System.out.println("The selected hotel: " + cart.getSelectedHotel().getName());
        hotel.model.Booking hotelRoomBooking = new hotel.model.Booking(checkIn, checkOut, persons, user, cart.getSelectedHotel(), cart.getSelectedHotelRooms());
        hotelController.createBooking(hotelRoomBooking);
        System.out.println("The hotel in the new booking: " + hotelRoomBooking.getHotel().getName());

    }

    public void createFlightBooking(User user, Cart cart) {
        List<Flight> selectedFlights = cart.getSelectedFlights();
        int userId = user.getId();

        List<Integer> flightIds = new java.util.ArrayList<>(List.of());
        for (Flight flight : selectedFlights) {
            System.out.println(flight.getFlightID());
            Integer flightId = flight.getFlightID();
            flightIds.add(flightId);
        }

        System.out.println(flightIds);
        flightBookingController.createBooking(userId, flightIds);
        System.out.println("Flight booking created successfully.");
    }

    /**
     * Creates a reservation in the tour reservation table for each tour in the cart.
     *
     * @param user the user making the booking
     * @param cart the cart containing the tours to be booked
     */
    public void createDayTripBooking(User user, Cart cart, int persons) {
        List<Tour> selectedTours = cart.getSelectedTours();

        // TODO: create booking by using the ReservationController from the D-team

        for (Tour tour : selectedTours) {
            int daytripId = tour.getTourID();
            String userName = user.getName();
            String userEmail = user.getEmail();
            LocalDate date = tour.getDate();

            boolean success = reservationController.makeReservation(daytripId, userName, userEmail, date, persons, Optional.empty());
            if (success) {
                System.out.println("Daytrip booking successful.");
                // bæta bookingID við BookingIds hjá user
                // user.addBookingId(String.valueOf(daytripId));
            } else {
                System.out.println("Daytrip booking failed. Please try again or check the tour availability.");
            }
        }
    }

    public List<hotel.model.Booking> findHotelBookings(User user) {
        return hotelController.getBookings(user);
    }

    public List<flight.Booking> findFlightBookings(User user) {
        return flightBookingController.searchBookingsByUserID(user.getId());
    }

    public List<Reservation> findDaytripBookings(User user) {
        List<Customer> allUsers = customerController.getAllCustomers();
        for (Customer customer : allUsers) {
            if (customer.getName().equals(user.getName()) && customer.getEmail().equals(user.getEmail())) {
                user.setId(customer.getId());
                System.out.println("New User ID: " + user.getId());
            }
        }
        return reservationController.getReservationsByCustomerId(user.getId());
    }

    public Tour getTourDetails(Integer tourID) {
        return tourController.getTourDetails(tourID);
    }

    public boolean cancelHotelBooking(int bookingID) {
        User user = DataManager.getInstance().getCurrentUser();
        if (user == null) {
            System.err.println("Operation failed: No user logged in.");
            return false;
        }

        try {
            List<hotel.model.Booking> allBookings = hotelController.getBookings(user);
            hotel.model.Booking bookingToCancel = allBookings.stream()
                    .filter(booking -> booking.getBookingID() == bookingID)
                    .findFirst()
                    .orElse(null);

            if (bookingToCancel != null) {
                hotelController.deleteBooking(bookingToCancel);
                System.out.println("Hotel booking cancelled successfully.");
                return true;
            } else {
                System.err.println("No booking found with ID: " + bookingID);
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error cancelling hotel booking: " + e.getMessage());
            return false;
        }
    }

    public boolean cancelFlightBooking(int bookingID) {
        try {
            flightBookingController.cancelBooking(bookingID);
            System.out.println("Flight booking cancelled successfully.");
            return true;
        } catch (Exception e) {
            System.err.println("Error cancelling flight booking: " + e.getMessage());
            return false;
        }
    }

    public boolean cancelTourBooking(int reservationID) {
        try {
            reservationController.cancelReservation(reservationID);
            System.out.println("Tour booking cancelled successfully.");
            return true;
        } catch (Exception e) {
            System.err.println("Error cancelling tour booking: " + e.getMessage());
            return false;
        }
    }
}
