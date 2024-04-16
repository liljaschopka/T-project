package com.example.tproject;

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
    private Button okButtonn;

    public UserDialog() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/tproject/User-view.fxml"));
        fxmlLoader.setController(this);
        try {
            DialogPane dialogPane = fxmlLoader.load();
            setDialogPane(dialogPane);

            okButton = new ButtonType("OK", ButtonBar.ButtonData.YES);
            cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            getDialogPane().getButtonTypes().addAll(okButton, cancelButton);

            bindButtonDisableProperties();

            // Button okButtonNode = (Button) getDialogPane().lookupButton(okButton);
            //okButtonNode.setOnAction(event -> handleOkAction());
            setupResultConverter();
        } catch (IOException e) {
            throw new RuntimeException("Unable to load User-view.fxml", e);
        }
    }

    private void handleOkAction() {
        try {
            Integer.parseInt(fxCardNumber.getText());
            //Integer.parseInt(fxExpirationDate.getText());
            String[] parts = fxExpirationDate.getText().split("/");
            Integer.parseInt(parts[0]);
            Integer.parseInt(parts[1]);
            Integer.parseInt(fxSecurityCode.getText());
            // Attempt to parse input fields and create a user
            User user = createUser();
            // If successful, set the result and close the dialog
            setResult(user);

        } catch (NumberFormatException e) {
            showWarningDialog("Card Number, Expiration Date, and Security Code must be numbers");
        } catch (IllegalArgumentException e) {
            showWarningDialog("Expiration Date must be in the format MM/YYYY");
        }

    }

    private User createUser() {
        User newUser = new User(1, fxName.getText(),
                fxEmail.getText(),
                new PaymentInfo(
                        fxCardNumber.getText(),
                        fxCardHolder.getText(),
                        fxExpirationDate.getText(),
                        fxSecurityCode.getText()
                ));

        DataManager.getInstance().setCurrentUser(newUser);  // Store user in DataManager
        return newUser;
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

   /* private void setupResultConverter() {
        setResultConverter(dialogButton -> {
            if (dialogButton == okButton) {
                try {
                    // Attempt to parse the text in fxCardNumber as an integer
                    Integer.parseInt(fxCardNumber.getText());
                    //Integer.parseInt(fxExpirationDate.getText());
                    String[] parts = fxExpirationDate.getText().split("/");
                    Integer.parseInt(parts[0]);
                    Integer.parseInt(parts[1]);
                    Integer.parseInt(fxSecurityCode.getText());
                    User newUser = new User(1, fxName.getText(),
                            fxEmail.getText(),
                            new PaymentInfo(
                                    fxCardNumber.getText(),
                                    fxCardHolder.getText(),
                                    fxExpirationDate.getText(),
                                    fxSecurityCode.getText()
                            ));

                    DataManager.getInstance().setCurrentUser(newUser);  // Store user in DataManager
                    return newUser;  // Return the newly created user
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    try {
                        Integer.parseInt(fxCardNumber.getText());

                    } catch (NumberFormatException i) {
                        showWarningDialog("Card Number must be a number");
                        ;
                        return null;
                    }
                    try {
                        String[] parts = fxExpirationDate.getText().split("/");
                        Integer.parseInt(parts[0]);
                        Integer.parseInt(parts[1]);
                        // Integer.parseInt(fxExpirationDate.getText());

                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException i) {
                        // Handle the exceptions
                        showWarningDialog("Expiration Date must be a day/month");
                        return null;
                    }
                    try {
                        Integer.parseInt(fxSecurityCode.getText());

                    } catch (NumberFormatException i) {
                        showWarningDialog("Security Code must be a number");
                        return null;
                    }
                }

                // If parsing fails, show an error message
                return null;
            }
            return null;
        });
    }*/

    private void setupResultConverter() {
        setResultConverter(dialogButton -> {
            if (dialogButton == okButton) {
                try {
                    // Attempt to parse the text in fxCardNumber as an integer
                    Integer.parseInt(fxCardNumber.getText());
                    //Integer.parseInt(fxExpirationDate.getText());
                    String[] parts = fxExpirationDate.getText().split("/");
                    Integer.parseInt(parts[0]);
                    Integer.parseInt(parts[1]);
                    Integer.parseInt(fxSecurityCode.getText());
                    User newUser = new User(1, fxName.getText(),
                            fxEmail.getText(),
                            new PaymentInfo(
                                    fxCardNumber.getText(),
                                    fxCardHolder.getText(),
                                    fxExpirationDate.getText(),
                                    fxSecurityCode.getText()
                            ));

                    DataManager.getInstance().setCurrentUser(newUser);  // Store user in DataManager
                    return newUser;  // Return the newly created user
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    try {
                        Integer.parseInt(fxCardNumber.getText());

                    } catch (NumberFormatException i) {
                        showWarningDialog("Card Number must be a number");
                        return null;
                    }
                    try {
                        String[] parts = fxExpirationDate.getText().split("/");
                        Integer.parseInt(parts[0]);
                        Integer.parseInt(parts[1]);
                        // Integer.parseInt(fxExpirationDate.getText());

                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException i) {
                        // Handle the exceptions
                        showWarningDialog("Expiration Date must be a day/month");
                        return null;
                    }
                    try {
                        Integer.parseInt(fxSecurityCode.getText());

                    } catch (NumberFormatException i) {
                        showWarningDialog("Security Code must be a number");
                        return null;
                    }
                }

                // If parsing fails, show an error message
                return null;
            }
            return null;
        });
    }

    private void showWarningDialog(String s) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
        ;
    }
}

