package com.example.tproject;

import controllers.PackageController;
import daytrip.controller.TourController;
import daytrip.dal.TourDAL;
import daytrip.model.Tour;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import model.Cart;

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
    private ListView fxToursList;
    @FXML
    private Label fxTourDescription;

    private TourController tourController = new TourController(new TourDAL());
    private PackageController packageController = DateSelectorView.getPackageController();
    private Cart cart = packageController.getCart();

    @FXML
    public void fxAddTourToCartHandler(ActionEvent ActionEvent) {
        fxToursList.getSelectionModel().getSelectedItems().forEach((selected) ->
                cart.addTourToCart((Tour) selected));
    }

    @FXML
    public void setFxGoBackHandler(ActionEvent ActionEvent) {
        ViewSwitcher.switchTo(View.BOOKINGSELECTOR);
    }

    public void initialize() {
        // Set items in the ListView
        fxToursList.setItems(FXCollections.observableArrayList(packageController.findAvailableDayTrips(tourController)));

        // Set custom cell factory to display only the name of the tours
        fxToursList.setCellFactory(listView -> new ListCell<Tour>() {
            @Override
            protected void updateItem(Tour tour, boolean empty) {
                super.updateItem(tour, empty);
                if (empty || tour == null) {
                    setText(null);
                } else {
                    setText(tour.getName());
                }
            }
        });

        // Add listener to update the description label when a new item is selected
        fxToursList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue instanceof Tour) {
                // Update the description label with the selected tour's description
                fxTourDescription.setText(((Tour) newValue).getDescription());
            } else {
                // Clear the description label if no item is selected
                fxTourDescription.setText("");
            }
        });
    }
}
