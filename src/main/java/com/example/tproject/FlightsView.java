package com.example.tproject;

import controllers.FlightController;
import controllers.PackageController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Cart;
import model.Flight;

/******************************************************************************
 *  Lýsing  :
 *  View klasi fyrir flug þar sem flug eru valin. Hefur aðferðir fxAddToCart og fxGoBack.
 *  Has a private attribute of type FlightController and PackageController
 *
 *
 *
 *****************************************************************************/
public class FlightsView {

    @FXML
    private Button fxAddToCart;
    @FXML
    private Button fxGoBack;
    @FXML
    private ListView fxflightsList;

    private FlightController flightController = new FlightController();
    private PackageController packageController = DateSelectorView.getPackageController();
    private Cart cart = packageController.getCart();

    @FXML
    public void fxAddFlightToCartHandler(ActionEvent ActionEvent) {
        fxflightsList.getSelectionModel().getSelectedItems().forEach((selected) ->
                cart.addFlightToCart((Flight) selected));
    }

    @FXML
    public void setFxGoBackHandler(ActionEvent ActionEvent) {
        ViewSwitcher.switchTo(View.BOOKINGSELECTOR);
    }

    public void initialize() {
        fxflightsList.setItems((ObservableList) packageController.findAvailableFlights(flightController));
    }


}
