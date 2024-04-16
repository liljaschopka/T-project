package com.example.tproject;

import controllers.BookingController;
import controllers.PackageController;
import daytrip.model.Reservation;
import daytrip.model.Tour;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Flight;
import model.User;

import java.util.List;

public class UserAreaController {
    @FXML
    private Label userNameLabel;
    @FXML
    private Label userEmailLabel;
    @FXML
    public ListView bookingsListView;

    private PackageController packageController = DateSelectorView.getPackageController();
    private BookingController bookingController = CartView.getBookingController();


    public void initData(User user) {

        bookingsListView.getItems().clear();

        userNameLabel.setText("User Name: " + user.getName());
        userEmailLabel.setText("Email: " + user.getEmail());


        List<hotel.model.Booking> hotelBookings = packageController.findHotelBookings(bookingController);
        List<flight.Booking> flightBookings = packageController.findFlightBookings(bookingController);
        List<Reservation> tourReservations = packageController.findTourReservations(bookingController);

        if (hotelBookings.isEmpty() && tourReservations.isEmpty() && flightBookings.isEmpty()) {
            bookingsListView.getItems().add("No bookings found");
        }
        for (hotel.model.Booking booking : hotelBookings) {
            if (booking.getHotel().getName() == null) {
                System.out.println("The hotel name is null");
            } else {
                bookingsListView.getItems().add("Dates: " + booking.getCheckIn() + " - " + booking.getCheckOut() + " at " + booking.getHotel().getName() + ", number of guests: " + booking.getPersons());
            }
        }
        for (flight.Booking booking : flightBookings) {
            for (Flight flight : booking.getFlights()) {
                bookingsListView.getItems().add("Flight details: " + flight.getFlightDetails());
            }
        }

        for (Reservation reservation : tourReservations) {
            Tour tour = packageController.getTourDetails(bookingController, reservation.getTourID());
            bookingsListView.getItems().add("Tour booking, Date: " + reservation.getDateBooked() + ", Name: " + tour.getName());
        }
    }
}
