package com.example.tproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;

public class DateSelectorController {
    @FXML
    private Label welcomeText;
    @FXML
    private DatePicker fxArrival;
    @FXML
    private DatePicker fxDeparture;
    @FXML
    private MenuButton fxPeople;
    @FXML
    private MenuButton fxTo;
    @FXML
    private MenuButton fxFrom;
    @FXML
    private Button fxConfirm;

    @FXML
    public void fxConfirmHandler(ActionEvent ActionEvent) {
        ViewSwitcher.switchTo(View.BOOKINGSELECTOR);
    }


}
