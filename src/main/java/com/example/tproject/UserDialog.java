package com.example.tproject;

import controllers.PackageController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import model.PaymentInfo;
import model.User;

import java.io.IOException;

/******************************************************************************
 *  Nafn    : Lilja Kolbrún Schopka
 *  T-póstur: lks17@hi.is
 *
 *  Lýsing  :
 *
 *
 *
 *
 *****************************************************************************/
public class UserDialog extends Dialog<User> {

    @FXML
    private TextField fxName;
    @FXML
    private TextField fxEmail;
    @FXML
    private TextField fxCardNumber;
    @FXML
    private TextField fxCardHolder;
    @FXML
    private TextField fxExpirationDate;
    @FXML
    private TextField fxSecurityCode;

    @FXML
    private ButtonType fxConfirm;
    @FXML
    private ButtonType fxCancel;

    private User user;
    private PackageController packageController = DateSelectorView.getPackageController();

    public UserDialog() {

        setDialogPane(readUserDialog());
        check();

        fxConfirm = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(fxConfirm, ButtonType.CANCEL);

        setResultConverter(dialogButton -> {
            if (dialogButton == fxConfirm) {
                return new User(null, fxName.getText(),
                        fxEmail.getText(),
                        new PaymentInfo(
                                fxCardNumber.getText(),
                                fxCardHolder.getText(),
                                fxExpirationDate.getText(),
                                fxSecurityCode.getText()
                        ), null);
            }
            return null;
        });

    }

    private DialogPane readUserDialog() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user-view.fxml"));
        try {
            fxmlLoader.setController(this);
            DialogPane dialogPane = fxmlLoader.load();
            return dialogPane;
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void check() {

        Node iLagi = getDialogPane().lookupButton(fxConfirm);
        iLagi.disableProperty()
                .bind(fxName.textProperty().isEmpty()
                        .or(fxEmail.textProperty().isEmpty())
                        .or(fxCardNumber.textProperty().isEmpty())
                        .or(fxCardHolder.textProperty().isEmpty())
                        .or(fxExpirationDate.textProperty().isEmpty())
                        .or(fxSecurityCode.textProperty().isEmpty()));
    }

}
