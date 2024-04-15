package controllers;

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
import flight.FlightInventory;
import model.Cart;
import model.Flight;
import model.User;

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
    private BookingInventory bookingInventory;
    private FlightInventory flightInventory;
    private flight.BookingController flightBookingController;

    public BookingController() {
        TourDAL tourDal = new TourDAL();
        ReservationDAL reservationDal = new ReservationDAL();
        tourController = new TourController(tourDal);
        reservationController = new ReservationController(reservationDal, tourController);
        hotelController = new hotel.controller.HotelController();
        CustomerDAL customerDAL = new CustomerDAL();
        customerController = new CustomerController(customerDAL, reservationDal);
        // bookingInventory = new BookingInventory();
        // flightInventory = new FlightInventory("Flights.txt");
        // flightBookingController = new flight.BookingController(bookingInventory, flightInventory);
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

        // User þarf að vera eins hjá okkur og hjá Hotel hópnum svo þetta virki
        hotel.model.Booking hotelRoomBooking = new hotel.model.Booking(checkIn, checkOut, persons, user, cart.getSelectedHotel(), cart.getSelectedHotelRooms());
        hotelController.createBooking(hotelRoomBooking);

    }

    public void createFlightBooking(User user, Cart cart) {
        List<Flight> selectedFlights = cart.getSelectedFlights();

        // TODO: create booking by using the BookingController from the F-team
    }

    /**
     * Creates a reservation in the tour reservation table for each tour in the cart.
     *
     * @param user the user making the booking
     * @param cart the cart containing the tours to be booked
     */
    public void createDayTripBooking(User user, Cart cart) {
        List<Tour> selectedTours = cart.getSelectedTours();

        // TODO: create booking by using the ReservationController from the D-team

        for (Tour tour : selectedTours) {
            int daytripId = tour.getTourID();
            String userName = user.getName();
            String userEmail = user.getEmail();
            LocalDate date = tour.getDate();
            int participants = tour.getMaxParticipants();

            boolean success = reservationController.makeReservation(daytripId, userName, userEmail, date, participants, Optional.empty());
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
        System.out.println("User ID: " + user.getId());
        if (user.getId() == null) {
            return null;
        }
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

    public void cancelHotelBooking(hotel.model.Booking booking) {
        hotelController.deleteBooking(booking);
    }

    public void cancelFlightBooking(String bookingID) {

    }

    public void cancelDaytripBooking(Reservation reservation) {
        reservationController.cancelReservation(reservation.getReservationID());
    }
}
