package com.example.tproject;

import controllers.BookingController;
import controllers.PackageController;
import daytrip.model.Reservation;
import daytrip.model.Tour;
import flight.Flight;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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

    @FXML
    private Button fxCancel;

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
                bookingsListView.getItems().add("Hotel Booking ID: " + booking.getBookingID() + ", Dates: " + booking.getCheckIn() + " - " + booking.getCheckOut() + " at " + booking.getHotel().getName() + ", number of guests: " + booking.getPersons());
            }
        }
        for (flight.Booking booking : flightBookings) {
            for (Flight flight : booking.getFlights()) {
                bookingsListView.getItems().add("Flight ID: " + flight.getFlightID() + " ,details: " + flight.getFlightDetails());
            }
        }

        for (Reservation reservation : tourReservations) {
            Tour tour = packageController.getTourDetails(bookingController, reservation.getTourID());
            bookingsListView.getItems().add("Tour Reservation ID: " + tour.getTourID() + ", Date: " + reservation.getDateBooked() + ", Name: " + tour.getName());
        }
    }
    @FXML
    private void handleCancelBooking() {
        int selectedIndex = bookingsListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            String selected = (String) bookingsListView.getItems().get(selectedIndex);
            boolean success = processCancellation(selected);
            if (success) {
                bookingsListView.getItems().remove(selectedIndex);
                showAlert("Booking Cancelled", "The booking has been successfully cancelled.", Alert.AlertType.INFORMATION);
                // Update the list of bookings displayed
                User user = DataManager.getInstance().getCurrentUser(); // Assuming this is how you get the current user
                updateListViewWithBookings(user);
            } else {
                showAlert("Cancellation Failed", "Could not cancel the booking. Please try again.", Alert.AlertType.ERROR);
            }
        } else {
            showAlert("No Selection", "Please select a booking to cancel.", Alert.AlertType.WARNING);
        }
    }
    private void updateListViewWithBookings(User user) {
        // Clear the existing items in the list view
        bookingsListView.getItems().clear();

        List<hotel.model.Booking> hotelBookings = bookingController.findHotelBookings(user);
        List<flight.Booking> flightBookings = bookingController.findFlightBookings(user);
        List<Reservation> tourReservations = bookingController.findDaytripBookings(user);

        if (hotelBookings.isEmpty() && flightBookings.isEmpty() && tourReservations.isEmpty()) {
            bookingsListView.getItems().add("No bookings found.");
        } else {
            hotelBookings.forEach(booking -> bookingsListView.getItems().add(booking));
            flightBookings.forEach(booking -> booking.getFlights().forEach(flight -> bookingsListView.getItems().add(flight)));
            tourReservations.forEach(reservation -> bookingsListView.getItems().add(reservation));
        }
    }

    private boolean processCancellation(String selected) {
        try {
            if (selected.startsWith("Hotel Booking ID: ")) {
                int bookingID = Integer.parseInt(selected.substring(selected.indexOf(':') + 1, selected.indexOf(',')).trim());
                return bookingController.cancelHotelBooking(bookingID);
            } else if (selected.startsWith("Tour Reservation ID: ")) {
                int reservationID = Integer.parseInt(selected.substring(selected.indexOf(':') + 1, selected.indexOf(',')).trim());
                return bookingController.cancelTourBooking(reservationID);
            } else if (selected.startsWith("Flight ID: ")) {
                int flightID = Integer.parseInt(selected.substring(selected.indexOf(':') + 1, selected.indexOf(',')).trim());
                return bookingController.cancelFlightBooking(flightID);
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Failed to parse booking ID.", Alert.AlertType.ERROR);
            return false;
        }
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
