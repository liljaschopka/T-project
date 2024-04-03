package com.example.tproject;

import controllers.DayTripController;
import controllers.PackageController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Cart;
import model.Daytrip;

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
public class DaytripsView {

    @FXML
    private Button fxAddToCart;
    @FXML
    private Button fxGoBack;
    @FXML
    private ListView fxdaytripsList;

    private DayTripController daytripController = new DayTripController();
    private PackageController packageController = DateSelectorView.getPackageController();
    private Cart cart = packageController.getCart();

    @FXML
    public void fxAddFlightToCartHandler(ActionEvent ActionEvent) {
        fxdaytripsList.getSelectionModel().getSelectedItems().forEach((selected) ->
                cart.addDaytripToCart((Daytrip) selected));
    }

    @FXML
    public void setFxGoBackHandler(ActionEvent ActionEvent) {
        ViewSwitcher.switchTo(View.BOOKINGSELECTOR);
    }

    public void initialize() {
        fxdaytripsList.setItems((ObservableList) packageController.findAvailableDayTrips(daytripController));
    }
}
