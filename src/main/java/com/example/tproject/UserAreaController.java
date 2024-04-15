package com.example.tproject;

import controllers.BookingController;
import controllers.PackageController;
import daytrip.model.Reservation;
import flight.Booking;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.User;

public class UserAreaController {
    @FXML
    private Label userNameLabel;
    @FXML
    private Label userEmailLabel;
    @FXML
    public ListView<Reservation> tourBookingsListView;
    @FXML
    public ListView<Booking> flightBookingsListView;
    @FXML
    public ListView<hotel.model.Booking> hotelBookingsListView;

    private PackageController packageController = DateSelectorView.getPackageController();
    private BookingController bookingController = new BookingController();


    public void initData(User user) {
        userNameLabel.setText("User Name: " + user.getName());
        userEmailLabel.setText("Email: " + user.getEmail());

        // Ensure bookingsListView is not null before accessing its methods
        if (tourBookingsListView != null) {
            tourBookingsListView.getItems().setAll(packageController.findTourReservations(bookingController));
        }
        if (flightBookingsListView != null) {
            flightBookingsListView.getItems().setAll(packageController.findFlightBookings(bookingController));
        }
        if (hotelBookingsListView != null) {
            hotelBookingsListView.getItems().setAll(packageController.findHotelBookings(bookingController));
        } else {
            // Handle the case where bookingsListView is null (maybe log a warning)
            System.out.println("bookingsListView is null. Ensure it's properly initialized.");
        }
    }
}
