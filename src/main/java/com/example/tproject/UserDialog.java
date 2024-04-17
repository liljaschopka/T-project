package com.example.tproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import model.PaymentInfo;
import model.User;

import java.io.IOException;

public class UserDialog extends Dialog<User> {
    @FXML
    private TextField fxName, fxEmail, fxCardNumber, fxCardHolder, fxExpirationDate, fxSecurityCode;

    private ButtonType okButton;
    private ButtonType cancelButton;

    public UserDialog() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/tproject/User-view.fxml"));
        fxmlLoader.setController(this);
        try {
            DialogPane dialogPane = fxmlLoader.load();
            setDialogPane(dialogPane);

            okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            getDialogPane().getButtonTypes().addAll(okButton, cancelButton);

            bindButtonDisableProperties();

            Button okButtonNode = (Button) getDialogPane().lookupButton(okButton);
            okButtonNode.addEventFilter(ActionEvent.ACTION, event -> {
                if (!validateInput()) {
                    event.consume(); // Prevent the dialog from closing
                }
            });

            setupResultConverter();
        } catch (IOException e) {
            throw new RuntimeException("Unable to load User-view.fxml", e);
        }
    }

    private boolean validateInput() {
        try {
            validateCardNumber(fxCardNumber.getText());
            validateExpirationDate(fxExpirationDate.getText());
            validateSecurityCode(fxSecurityCode.getText());
            return true;
        } catch (IllegalArgumentException e) {
            showWarningDialog(e.getMessage());
            return false;
        }
    }

    private void validateCardNumber(String cardNumber) throws IllegalArgumentException {
        try {
            Integer.parseInt(cardNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Card Number must be a number");
        }
    }

    private void validateExpirationDate(String expirationDate) throws IllegalArgumentException {
        String[] parts = expirationDate.split("/");
        if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
            throw new IllegalArgumentException("Expiration Date must be in the format MM/YYYY");
        }
        try {
            Integer.parseInt(parts[0]);  // Validate month
            Integer.parseInt(parts[1]);  // Validate year
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Expiration Date parts must be numbers");
        }
    }

    private void validateSecurityCode(String securityCode) throws IllegalArgumentException {
        try {
            Integer.parseInt(securityCode);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Security Code must be a number");
        }
    }

    private User createUser() {
        return new User(1, fxName.getText(),
                fxEmail.getText(),
                new PaymentInfo(
                        fxCardNumber.getText(),
                        fxCardHolder.getText(),
                        fxExpirationDate.getText(),
                        fxSecurityCode.getText()
                ));
    }

    private void bindButtonDisableProperties() {
        Button okButtonNode = (Button) getDialogPane().lookupButton(okButton);
        okButtonNode.disableProperty().bind(
                fxName.textProperty().isEmpty()
                        .or(fxEmail.textProperty().isEmpty())
                        .or(fxCardNumber.textProperty().isEmpty())
                        .or(fxCardHolder.textProperty().isEmpty())
                        .or(fxExpirationDate.textProperty().isEmpty())
                        .or(fxSecurityCode.textProperty().isEmpty())
        );
    }

    private void setupResultConverter() {
        setResultConverter(dialogButton -> {
            if (dialogButton == okButton) {
                if (validateInput()) {
                    User newUser = createUser();
                    DataManager.getInstance().setCurrentUser(newUser);
                    return newUser;
                }
                // If input validation fails, do not close the dialog.
            }
            return null;
        });
    }

    private void showWarningDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}