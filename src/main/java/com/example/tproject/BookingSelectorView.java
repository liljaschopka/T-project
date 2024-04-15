package com.example.tproject;

import controllers.PackageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.User;

import java.time.LocalDate;
import java.util.Optional;

/******************************************************************************
 *  Lýsing  :
 *  Ný sena eftir DateSelector sem inniheldur buttons fyrir hotel, flights og
 *  daytrip sem eru available fyrir valdar dagsetningar. Innheldur líka button
 *  til þess að fara til baka og halda áfram?
 *
 *****************************************************************************/
public class BookingSelectorView {
    @FXML
    private Label description;
    @FXML
    private Button fxFlights;
    @FXML
    private Button fxHotels;
    @FXML
    private Button fxDaytrips;
    @FXML
    private Button fxGoBack;
    @FXML
    private Button fxConfirm;
    private static PackageController packageController;

    @FXML
    public void fxGoBackHandler(ActionEvent ActionEvent) {
        ViewSwitcher.switchTo(View.DATESELECTOR);
    }

    @FXML
    public void fxConfirmHandler(ActionEvent ActionEvent) {
        ViewSwitcher.switchTo(View.CART);
    }

    @FXML
    public void fxFlightsHandler(ActionEvent ActionEvent) {
        ViewSwitcher.switchTo(View.FLIGHTS);
    }

    @FXML
    public void fxHotelsHandler(ActionEvent ActionEvent) {
        ViewSwitcher.switchTo(View.HOTELS);
    }

    @FXML
    public void fxDaytripsHandler(ActionEvent ActionEvent) {
        ViewSwitcher.switchTo(View.DAYTRIPS);
    }

    public void goToCart(ActionEvent actionEvent) {
        ViewSwitcher.switchTo(View.CART);
    }



    public static PackageController getPackageController() {
        if (packageController == null) {
            packageController = new PackageController(null, "Default Origin", "Default Destination",
                    LocalDate.now(), LocalDate.now().plusDays(1), 0);
        }
        return packageController;
    }

    private void showAlert(Alert.AlertType type, String contentText) {
        Alert alert = new Alert(type);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    @FXML
    public void initialize() {
        if (packageController == null) {
            getPackageController(); // Ensure the controller is initialized
        }
    }

    /*
    @FXML
    public void fxUserHandler(ActionEvent actionEvent) {
        if (getPackageController() == null) {
            showAlert(Alert.AlertType.ERROR, "Operation cannot be completed at this time.");
            return;
        }
        if (getPackageController().getUser() != null) {
            showUserArea(getPackageController().getUser());
        } else {
            // No user registered, open the registration dialog
            UserDialog dialog = new UserDialog();
            Optional<User> result = dialog.showAndWait();
            result.ifPresent(user -> {
                getPackageController().setUser(user.getName(), user.getEmail(), user.getPaymentInfo(), user.getBookingIds());
                System.out.println("New user created: " + user.getName());
                showUserInfo(user);  // Optionally show immediate confirmation
            });
        }
    }
*/

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
                packageController.setUser(user);  // Update the package controller with the new user
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
