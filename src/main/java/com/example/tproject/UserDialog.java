package com.example.tproject;

import controllers.PackageController;
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
    private User user;
    private PackageController packageController = DateSelectorView.getPackageController();

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
            setupResultConverter();
        } catch (IOException e) {
            throw new RuntimeException("Unable to load User-view.fxml", e);
        }
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
                return new User(null, fxName.getText(),
                        fxEmail.getText(),
                        new PaymentInfo(
                                fxCardNumber.getText(),
                                fxCardHolder.getText(),
                                fxExpirationDate.getText(),
                                fxSecurityCode.getText()
                        ), null);
            }
            return null; // when cancel is pressed
        });
    }
}
