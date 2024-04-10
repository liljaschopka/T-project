package com.example.tproject;

import controllers.PackageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import model.User;

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

                packageController = new PackageController(null, origin, destination, fxCheckIn.getValue(),
                        fxCheckOut.getValue(), persons);
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
        try {
            UserDialog userDialog = new UserDialog();
            Optional<User> result = userDialog.showAndWait();
            result.ifPresent(user -> System.out.println("User Created: " + user.getName()));
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Failed to open user dialog: " + e.getMessage());
        }
    }
}

