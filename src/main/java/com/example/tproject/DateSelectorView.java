package com.example.tproject;

import controllers.PackageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import model.User;

import java.time.LocalDate;
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
        return packageController;
    }

    @FXML
    public void fxConfirmHandler(ActionEvent event) {
        try {
            String origin = fxOrigin.getText().replace("Select Location: ", "");
            String destination = fxDestination.getText().replace("Select Destination: ", "");
            String personsText = fxPeople.getText().replace("Select Number: ", "");
            if (personsText.matches("\\d+")) {  // passa það sé tala
                int persons = Integer.parseInt(personsText);

                if (origin.equals("Select Location") || destination.equals("Select Destination") ||
                        fxCheckIn.getValue() == null || fxCheckOut.getValue() == null) {
                    System.out.println("Please complete all fields.");
                    showAlert(AlertType.WARNING, "Please complete all fields.");
                    return;
                }

                if (origin.equals(destination)) {
                    System.out.print("Origin can not be the same as destination.");
                    showAlert(AlertType.WARNING, "Origin can not be the same as destination.");
                    return;
                }

                if (fxCheckOut.getValue().isBefore(fxCheckIn.getValue())) {
                    System.out.print("Invalid dates.");
                    showAlert(AlertType.WARNING, "Invalid dates.");
                    return;
                }
                
                packageController.setOrigin(origin);
                packageController.setDestination(destination);
                packageController.setCheckIn(fxCheckIn.getValue());
                packageController.setCheckOut(fxCheckOut.getValue());
                packageController.setPersons(persons);

                ViewSwitcher.switchTo(View.BOOKINGSELECTOR);
            } else {
                System.out.println("Please select a valid number of persons.");
                showAlert(AlertType.WARNING, "Please select a valid number of persons.");
                return;
            }

        } catch (NumberFormatException e) {
            System.out.println("Error in number of persons: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
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
        setupMenuButton(fxOrigin, "Select Location");
        setupMenuButton(fxDestination, "Select Destination");
        setupMenuButton(fxPeople, "Select Number");
        packageController = new PackageController(null, "Default Origin", "Default Destination",
                LocalDate.now(), LocalDate.now().plusDays(1), 1); //placeholders
    }

    private void setupMenuButton(MenuButton menuButton, String defaultText) {
        menuButton.setText(defaultText);

        menuButton.getItems().forEach(item -> item.setOnAction(e -> {
            menuButton.setText(item.getText());
        }));
    }

    public void goToCart(ActionEvent actionEvent) {
        ViewSwitcher.switchTo(View.CART);
    }

    @FXML
    public void fxUserHandler(ActionEvent actionEvent) {
        if (packageController.getUser() != null) {
            // User is already registered, open the User Area dialog
            showUserArea(packageController.getUser());
        } else {
            // No user registered, open the registration dialog
            UserDialog dialog = new UserDialog();
            Optional<User> result = dialog.showAndWait();
            result.ifPresent(user -> {
                packageController.setUser(user.getName(), user.getEmail(), user.getPaymentInfo(), user.getBookingIds());
                System.out.println("New user created: " + user.getName());
                showUserInfo(user);  // Optionally show immediate confirmation
            });
        }
    }

    private void showUserInfo(User user) {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("User Information");
        infoAlert.setHeaderText("Welcome, " + user.getName());
        infoAlert.setContentText("Here are your details:\nEmail: " + user.getEmail() +
                "\n\nYou can now use the system features to view and manage your bookings.");
        infoAlert.showAndWait();
    }

    private void showUserArea(User user) {
        // Create a new dialog or window to display user information
        UserAreaDialog userAreaDialog = new UserAreaDialog(user);
        userAreaDialog.showAndWait();
    }
}

