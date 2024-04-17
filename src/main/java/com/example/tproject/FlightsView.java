package com.example.tproject;

import controllers.PackageController;
import flight.Flight;
import flight.FlightController;
import flight.FlightInventory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Cart;

import java.io.IOException;
import java.util.List;


public class FlightsView {

    @FXML
    public ListView<Flight> fxDepartureList;
    @FXML
    public ListView<Flight> fxArrivalList;
    @FXML
    private Button fxAddToCart;
    @FXML
    private Button fxGoBack;
    @FXML
    private Label fxFlightDescription;

    private FlightController flightController;
    private FlightInventory flightInventory;
    private PackageController packageController = DateSelectorView.getPackageController();
    private Cart cart = packageController.getCart();

    public FlightsView() throws IOException {
        String filePath = "Flights.txt";
        flightInventory = new FlightInventory(filePath);
        flightController = new FlightController(flightInventory);
    }

    @FXML
    public void initialize() {
        populateArrivalList();
        populateDepartureList();
        setupArrivalListView();
        setupDepartureListView();
    }

    private void populateArrivalList() {
        try {
            List<Flight> availableFlights = packageController.findAvailableArrivals(flightInventory);
            fxArrivalList.setItems(FXCollections.observableArrayList(availableFlights));
        } catch (Exception e) {
            System.out.println("Error loading Flights: " + e.getMessage());
            fxFlightDescription.setText("Error loading Flights. Please try again.");
            // Clears the list view in case of an error
            fxArrivalList.setItems(FXCollections.observableArrayList());
        }
    }

    private void populateDepartureList() {
        try {
            List<Flight> availableFlights = packageController.findAvailableDepartures(flightInventory);
            fxDepartureList.setItems(FXCollections.observableArrayList(availableFlights));
        } catch (Exception e) {
            System.out.println("Error loading Flights: " + e.getMessage());
            fxFlightDescription.setText("Error loading Flights. Please try again.");
            // Clears the list view in case of an error
            fxDepartureList.setItems(FXCollections.observableArrayList());
        }
    }

    private void setupArrivalListView() {
        fxArrivalList.setCellFactory(lv -> new ListCell<Flight>() {
            @Override
            protected void updateItem(Flight flight, boolean empty) {
                super.updateItem(flight, empty);
                setText(empty || flight == null ? null : (flight.getOrigin() + "-" + flight.getDestination() + ", " + flight.getDepartureTime() + ", " + flight.getPrice() + " ISK" + ", available seats: " + flight.getAvailableSeats()))
                ;
            }
        });

        fxArrivalList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            fxFlightDescription.setText(newValue != null ? newValue.getDestination() : "Select a flight to see details");
        });
    }

    private void setupDepartureListView() {
        fxDepartureList.setCellFactory(lv -> new ListCell<Flight>() {
            @Override
            protected void updateItem(Flight flight, boolean empty) {
                super.updateItem(flight, empty);
                setText(empty || flight == null ? null : (flight.getOrigin() + "-" + flight.getDestination() + ", " + flight.getDepartureTime() + ", " + flight.getPrice() + " ISK" + ", available seats: " + flight.getAvailableSeats()))

            }
        });

        fxDepartureList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            fxFlightDescription.setText(newValue != null ? newValue.getDestination() : "Select a flight to see details");
        });
    }


    @FXML
    private void fxAddToCartHandler(ActionEvent event) {
        //Placeholder
        Flight selectedDeparture = fxDepartureList.getSelectionModel().getSelectedItem();
        Flight selectedArrival = fxArrivalList.getSelectionModel().getSelectedItem();
        if (selectedDeparture != null || selectedArrival != null) {
            cart.addFlightToCart(selectedDeparture);
            cart.addFlightToCart(selectedArrival);
        }
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("User Information");
        infoAlert.setHeaderText("Added to cart!");
        infoAlert.setContentText("Departure " + selectedDeparture.getDestination()
                + " and return " + selectedArrival.getDestination() + " has been added to your cart.");
        infoAlert.showAndWait();
    }

    @FXML
    private void fxGoBackHandler(ActionEvent event) {
        ViewSwitcher.switchTo(View.BOOKINGSELECTOR);
    }
}
