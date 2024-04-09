package com.example.tproject;

import flight.FlightController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import model.Flight;


public class FlightsView {

    @FXML
    private ListView<Flight> fxFlightsList;
    @FXML
    private Button fxAddToCart;
    @FXML
    private Button fxGoBack;
    @FXML
    private Label fxFlightDescription;

    private FlightController flightController;

    public FlightsView() {
        flightController = new FlightController();
    }

    @FXML
    public void initialize() {
        ObservableList<Flight> flights = FXCollections.observableArrayList(); // Assume this comes from somewhere
        fxFlightsList.setItems(flights);

        fxFlightsList.setCellFactory(lv -> new ListCell<Flight>() {
            @Override
            protected void updateItem(Flight flight, boolean empty) {
                super.updateItem(flight, empty);
                if (empty || flight == null) {
                    setText(null);
                } else {
                    setText(flight.toString()); // Make sure toString() is implemented in Flight
                }
            }
        });

        fxFlightsList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                fxFlightDescription.setText(newSelection.toString()); // Display details of selected flight
            }
        });
    }

    @FXML
    private void fxAddToCartHandler(ActionEvent event) {
        Flight selectedFlight = fxFlightsList.getSelectionModel().getSelectedItem();
        if (selectedFlight != null) {
            System.out.println("Flight added to cart: " + selectedFlight); // Placeholder for adding to cart
        }
    }

    @FXML
    private void fxGoBackHandler(ActionEvent event) {
        ViewSwitcher.switchTo(View.BOOKINGSELECTOR);
    }
}