package com.example.tproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

    @FXML
    public void fxGoBackHandler(ActionEvent ActionEvent) {
        ViewSwitcher.switchTo(View.DATESELECTOR);
    }

    @FXML
    public void fxConfirmHandler(ActionEvent ActionEvent) {
        ViewSwitcher.switchTo(View.PAYMENT);
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
}
