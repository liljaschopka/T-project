package com.example.tproject;

import controllers.BookingController;
import controllers.PackageController;
import daytrip.model.Reservation;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
        userNameLabel.setText("User Name: " + user.getName());
        userEmailLabel.setText("Email: " + user.getEmail());

        if (bookingsListView != null) {
            List<hotel.model.Booking> hotelBookings = packageController.findHotelBookings(bookingController);
            List<flight.Booking> flightBookings = packageController.findFlightBookings(bookingController);
            List<Reservation> tourReservations = packageController.findTourReservations(bookingController);
            for (hotel.model.Booking booking : hotelBookings) {
                bookingsListView.getItems().add("Dates: " + booking.getCheckIn() + " - " + booking.getCheckOut() + " at " + booking.getHotel().getName() + ", number of guests: " + booking.getPersons());
            }
            for (flight.Booking booking : flightBookings) {
                bookingsListView.getItems().add("Flights: " + booking.getFlights().get(0).getFlightDetails() + " - " + booking.getFlights().get(1).getFlightDetails());
            }
            for (Reservation reservation : tourReservations) {
                bookingsListView.getItems().add("Date: " + reservation.getDateBooked() + ", Participants: " + reservation.getNumberOfParticipants());
            }
        } else {
            // Handle the case where bookingsListView is null (maybe log a warning)
            System.out.println("bookingsListView is null. Ensure it's properly initialized.");
        }
    }
}
