package com.example.tproject;

import controllers.PackageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import model.User;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

public class DateSelectorView {

    @FXML
    private Label welcomeText;
    @FXML
    private DatePicker fxCheckIn;
    @FXML
    private DatePicker fxCheckOut;
    @FXML
    private MenuButton fxPeople;
    @FXML
    private MenuButton fxDestination;
    @FXML
    private MenuButton fxOrigin;
    @FXML
    private Button fxConfirm;

    private static PackageController packageController;


    public static PackageController getPackageController() {
        if (packageController == null) {
            packageController = new PackageController(DataManager.getInstance().getCurrentUser(), "Default Origin", "Default Destination",
                    LocalDate.of(2024, Month.MAY, 1), LocalDate.of(2024, Month.MAY, 2), 0);
        }
        return packageController;
    }

    @FXML
    public void fxConfirmHandler(ActionEvent event) {
        try {
            String origin = fxOrigin.getText().replace("Select Location: ", "");
            String destination = fxDestination.getText().replace("Select Destination: ", "");
            String personsText = fxPeople.getText().replace("Select Number: ", "");

            if (personsText.matches("\\d+")) {  // Ensure it is a number
                int persons = Integer.parseInt(personsText);

                // Check if all necessary fields are filled or if origin and destination are the same
                if (origin.equals("Select Location") || destination.equals("Select Destination") ||
                        fxCheckIn.getValue() == null || fxCheckOut.getValue() == null || persons == 0) {
                    showAlert(AlertType.WARNING, "Please complete all fields.");
                    return;
                }

                if (origin.equals(destination)) {
                    showAlert(AlertType.WARNING, "Origin cannot be the same as destination.");
                    return;
                }

                if (fxCheckOut.getValue().isBefore(fxCheckIn.getValue())) {
                    showAlert(AlertType.WARNING, "Please complete all fields."); // Generalizing the alert for invalid dates
                    return;
                }

                // Retrieve the current user from DataManager
                User currentUser = DataManager.getInstance().getCurrentUser();
                if (currentUser == null) {
                    showAlert(AlertType.ERROR, "Please log in through the User button (upper right corner) before continuing! :)");
                    return;
                }

                // Initialize the package controller with user data and trip details
                packageController = new PackageController(currentUser, origin, destination,
                        fxCheckIn.getValue(), fxCheckOut.getValue(), persons);

                ViewSwitcher.switchTo(View.BOOKINGSELECTOR);
            } else {
                showAlert(AlertType.WARNING, "Please complete all fields."); // Generalizing the alert for invalid person count
            }
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Please complete all fields."); // Generalizing the alert for format errors
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAlert(AlertType type, String contentText) {
        Alert alert = new Alert(type);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    @FXML
    public void initialize() {
        packageController = getPackageController();
        setupMenuButton(fxOrigin, "Select Location", packageController.getOrigin());
        setupMenuButton(fxDestination, "Select Destination", packageController.getDestination());
        setupMenuButton(fxPeople, "Select Number", Integer.toString(packageController.getPersons()));
        fxCheckIn.setValue(packageController.getCheckIn());
        fxCheckOut.setValue(packageController.getCheckOut());
    }

    private void setupMenuButton(MenuButton menuButton, String defaultText, String value) {
        menuButton.setText(value.equals("Default Origin") || value.equals("Default Destination") ? defaultText : value);
        menuButton.getItems().forEach(item -> item.setOnAction(e -> {
            menuButton.setText(item.getText());
        }));
    }

    public void goToCart(ActionEvent actionEvent) {
        ViewSwitcher.switchTo(View.CART);
    }


    @FXML
    public void fxUserHandler(ActionEvent actionEvent) {
        User currentUser = DataManager.getInstance().getCurrentUser();
        if (currentUser != null) {
            showUserArea(currentUser);
        } else {
            // No user registered, open the registration dialog
            UserDialog dialog = new UserDialog();
            Optional<User> result = dialog.showAndWait();
            result.ifPresent(user -> {
                DataManager.getInstance().setCurrentUser(user);  // Update DataManager with the new user
                packageController.setUser(user);  // Also update the package controller with the new user
                showUserArea(user);  // Display user area with new user info
            });
        }
    }

    public void showUserInfo(User user) {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("User Information");
        infoAlert.setHeaderText("Welcome, " + user.getName());
        infoAlert.setContentText("Here are your details:\nEmail: " + user.getEmail() +
                "\n\nYou can now use the system features to view and manage your bookings.");
        infoAlert.showAndWait();
    }

    public void showUserArea(User user) {
        // Create a new dialog or window to display user information
        UserAreaDialog userAreaDialog = new UserAreaDialog(user);
        userAreaDialog.showAndWait();
    }
}

