package controllers;

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
    public ListView<Object> bookingsListView;

    // Method to initialize data in the user area
    public void initData(User user) {
        userNameLabel.setText("User Name: " + user.getName());
        userEmailLabel.setText("Email: " + user.getEmail());

        // Ensure bookingsListView is not null before accessing its methods
        if (bookingsListView != null) {
            bookingsListView.getItems().setAll(user.getBookingDetails());
        } else {
            // Handle the case where bookingsListView is null (maybe log a warning)
            System.out.println("bookingsListView is null. Ensure it's properly initialized.");
        }
    }
}
