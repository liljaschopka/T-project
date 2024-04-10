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
    private ListView<String> bookingsListView;

    // Method to initialize data in the user area
    public void initData(User user) {
        userNameLabel.setText("User Name: " + user.getName());
        userEmailLabel.setText("Email: " + user.getEmail());
        bookingsListView.getItems().setAll(user.getBookingDetails()); // Assumes User class has a getBookingDetails method returning List<String>
    }
}