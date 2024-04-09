package com.example.tproject;

import controllers.PackageController;
import daytrip.controller.TourController;
import daytrip.model.Tour;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import model.Cart;
import java.util.List;

public class DaytripsView {

    @FXML
    private Button fxAddToCart;
    @FXML
    private Button fxGoBack;
    @FXML
    private ListView<Tour> fxToursList;
    @FXML
    private Label fxTourDescription;

    private TourController tourController;
    private PackageController packageController;
    private Cart cart;

    public void setTourController(TourController tourController) {
        this.tourController = tourController;
    }

    public void setPackageController(PackageController packageController) {
        this.packageController = packageController;
        this.cart = packageController.getCart(); // cart initiliazied in package controller
    }

    /**
     * Initializes the controller class, sets up the ListView and populates it with available tours.
     */
    public void initialize() {
        setupTourListView();
        populateTourList();
    }

    /**
     * Populates the ListView with available tours using the PackageController.
     */
    private void populateTourList() {
        try {
            List<Tour> availableTours = packageController.findAvailableDayTrips(tourController);
            fxToursList.setItems(FXCollections.observableArrayList(availableTours));
        } catch (Exception e) {
            System.out.println("Error loading tours: " + e.getMessage());
            fxTourDescription.setText("Error loading tours. Please try again.");
            // Clears the list view in case of an error
            fxToursList.setItems(FXCollections.observableArrayList());
        }
    }

    /**
     * Sets up the ListView with a custom cell factory and a selection model for displaying tour details.
     */
    private void setupTourListView() {
        fxToursList.setCellFactory(listView -> new ListCell<Tour>() {
            @Override
            protected void updateItem(Tour tour, boolean empty) {
                super.updateItem(tour, empty);
                setText(empty || tour == null ? null : tour.getName());
            }
        });

        fxToursList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            fxTourDescription.setText(newValue != null ? newValue.getDescription() : "Select a tour to see details");
        });
    }

    /**
     * Handler for adding tour to cart
     */
    @FXML
    private void fxAddTourToCartHandler(ActionEvent event) {
        Tour selectedTour = fxToursList.getSelectionModel().getSelectedItem();
        if (selectedTour != null) {
            cart.addTourToCart(selectedTour);
            System.out.println("Added to cart: " + selectedTour.getName());
        } else {
            System.out.println("No tour selected. Please select a tour first.");
        }
    }

    /**
     * Handler for going back.
     */
    @FXML
    private void fxGoBackHandler(ActionEvent event) {
        ViewSwitcher.switchTo(View.BOOKINGSELECTOR);
    }
}
