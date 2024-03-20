package com.example.tproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

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
public class PaymentView {
    @FXML
    private Button fxPay;
    @FXML
    private Button fxCancel;
    @FXML
    private Button fxRemove;
    @FXML
    private ListView fxCart;
    @FXML
    private Label fxTotalPrice;

    @FXML
    public void fxCancelHandler(ActionEvent ActionEvent) {
        ViewSwitcher.switchTo(View.BOOKINGSELECTOR); // hvert viljum við skipta hér?
    }

    @FXML
    public void fxPayHandler(ActionEvent ActionEvent) {
        // ViewSwitcher.switchTo(View.BOOKINGSELECTOR);
    }

    @FXML
    public void fxRemoveHandler(ActionEvent ActionEvent) {
    }

    public static void main(String[] args) {

    }
}
