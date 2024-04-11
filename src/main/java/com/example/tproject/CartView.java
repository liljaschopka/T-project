package com.example.tproject;

import controllers.BookingController;
import controllers.PackageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Cart;
import model.User;

import java.util.Optional;

/******************************************************************************
 *  Lýsing  :
 *
 *
 *
 *
 *****************************************************************************/
public class CartView {
    @FXML
    private Button fxPay;
    @FXML
    private Button fxGoBack;
    @FXML
    private Button fxEmptyCart;
    @FXML
    private Button fxRemove;
    @FXML
    private ListView fxCart;
    @FXML
    private Label fxTotalPrice;

    private BookingController bookingController = new BookingController();
    private PackageController packageController = DateSelectorView.getPackageController();
    private Cart cart = packageController.getCart();
    private User user = packageController.getUser();


    @FXML
    public void fxGoBackHandler(ActionEvent ActionEvent) {
        ViewSwitcher.goBack();
    }

    @FXML
    public void fxEmptyCartHandler(ActionEvent ActionEvent) {
        cart.emptyCart();
        updateCartDisplay();
    }

    /**
     * Ef user er ekki innskráður birtist UserDialog þar sem hann getur nýskráð sig. Svo þegar það er komið
     * er hægt að velja pay aftur og þá myndast bókun. Svo er allt núllstillt og farið á upphafsskjá.
     */
    @FXML
    public void fxPayHandler(ActionEvent ActionEvent) {
        if (user == null) {
            newUser();
        } else {
            packageController.createBooking(bookingController);
            packageController.clearSelection();
            ViewSwitcher.switchTo(View.DATESELECTOR);
        }
    }

    @FXML
    public void fxRemoveHandler(ActionEvent ActionEvent) {
        // Example of removing the selected hotel room
        if (!fxCart.getSelectionModel().getSelectedItems().isEmpty()) {
            String selected = fxCart.getSelectionModel().getSelectedItem().toString();
            if (selected.startsWith("Room:")) {
                cart.setSelectedHotelRoom(null);  // bjó til settera í cart til að gera þetta idk ef það er gott
            } else if (selected.startsWith("Flight:")) {
                cart.setSelectedFlight(null);
            } else if (selected.startsWith("Tour:")) {
                cart.setSelectedTour(null);
            }
            updateCartDisplay();
        }
    }

    public void initialize() {
        fxTotalPrice.setText(String.valueOf(cart.getTotalAmount()));
        updateCartDisplay();
    }

    private void newUser() {
        UserDialog dialog = new UserDialog();
        Optional<User> result = dialog.showAndWait();
        result.ifPresent(user -> {
            packageController.setUser(user.getName(), user.getEmail(), user.getPaymentInfo(), user.getBookingIds());
            System.out.println("New user created: " + user.getName());
        });
    }

    public void updateCartDisplay() {
        fxCart.getItems().clear(); // Clear existing items
        if (cart.getSelectedHotelRoom() != null) {
            fxCart.getItems().add("Room: " + cart.getSelectedHotelRoom().getDescription() + " Price: $" + cart.getSelectedHotelRoom().getPrice());
        }
        if (cart.getSelectedFlight() != null) {
            fxCart.getItems().add("Flight: " + cart.getSelectedFlight().getDescription() + " Price: $" + cart.getSelectedFlight().getPrice());
        }
        if (cart.getSelectedTour() != null) {
            fxCart.getItems().add("Tour: " + cart.getSelectedTour().getDescription() + " Price: $" + cart.getSelectedTour().getPrice());
        }
        fxTotalPrice.setText("Total: $" + cart.getTotalAmount()); // Update total price
    }

}
