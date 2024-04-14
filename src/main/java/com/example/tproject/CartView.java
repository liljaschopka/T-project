package com.example.tproject;

import controllers.BookingController;
import controllers.PackageController;
import daytrip.model.Tour;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Cart;
import model.Flight;
import model.User;

import java.util.List;
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
    private DateSelectorView date;


    private BookingController bookingController = new BookingController();
    private PackageController packageController = DateSelectorView.getPackageController();
    private Cart cart = packageController.getCart();
    private User user = packageController.getUser();
    private UserAreaController userAreaController;


    public CartView() {
        // Initialize the date object
        this.date = new DateSelectorView();
        this.userAreaController = new UserAreaController();
    }

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
        //if (user == null) {
        //  newUser();
        //} else {
        //  packageController.createBooking(bookingController);
        //packageController.clearSelection();
        //ViewSwitcher.switchTo(View.DATESELECTOR);
        // cart.emptyCart();
        //    }
        User loggedInUser = packageController.getUser();
        if (loggedInUser != null) {
            // this.date.showUserArea(loggedInUser);
            //ObservableList<String> cartItems = fxCart.getItems();
            //userAreaController.bookingsListView.getItems().addAll(cartItems);
            ViewSwitcher.switchTo(View.DATESELECTOR);
            borga();

        } else {
            // No user registered, open the registration dialog
            UserDialog dialog = new UserDialog();
            Optional<User> result = dialog.showAndWait();
            result.ifPresent(user -> {
                //packageController.setUser(user.getName(), user.getEmail(), user.getPaymentInfo(), user.getBookingIds());
                this.user = user;
                System.out.println("New user created: " + user.getName());
                this.date.showUserInfo(user);  // Optionally show immediate confirmation
            });
        }

    }

    public void borga() {
        User loggedInUser = packageController.getUser();
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle(loggedInUser.getName());
        infoAlert.setHeaderText("Booking completed!");
        infoAlert.setContentText("Dear " + loggedInUser.getName() + ", your trip has been booked and paid for. Have a nice trip!");
        infoAlert.showAndWait();
        while (1 == 1) {
            if (!infoAlert.isShowing()) {
                this.date.showUserArea(loggedInUser);
                break;
            }
        }
        cart.emptyCart();
    }


    @FXML
    public void fxRemoveHandler(ActionEvent ActionEvent) {
        String selected = fxCart.getSelectionModel().getSelectedItem().toString();
        if (selected.startsWith("Room number:")) {
            List<hotel.model.HotelRoom> rooms = cart.getSelectedHotelRooms();
            
        } else if (selected.startsWith("Flight:")) {
            List<Flight> flights = cart.getSelectedFlights();
            // cart.removeSelectedFlight(flight);
            cart.setSelectedFlight(null);
        } else if (selected.startsWith("Tour:")) {
            // Identify the tour to remove
            String tourDescription = selected.substring(selected.indexOf(":") + 2, selected.indexOf("Price") - 1);
            // Find and remove the tour from the selected tours list
            Tour tourToRemove = null;
            for (Tour tour : cart.getSelectedTours()) {
                if (tour.getName().equals(tourDescription)) {
                    tourToRemove = tour;
                    break;
                }
            }
            if (tourToRemove != null) {
                cart.removeSelectedTour(tourToRemove);
                //cart.getSelectedTours().remove(tourToRemove);
            }
        }
        updateCartDisplay();
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
        for (hotel.model.HotelRoom hotelRoom : cart.getSelectedHotelRooms()) {
            fxCart.getItems().add("Room number: " + hotelRoom.getRoomNumber() + " in " + cart.getSelectedHotel().getName() + ", price: " + hotelRoom.getPrice() + "$ per night");
        }
        for (Flight flight : cart.getSelectedFlights()) {
            fxCart.getItems().add("Flight: " + flight.getFlightDetails() + " Price: $" + flight.getPrice());
        }
        for (Tour tour : cart.getSelectedTours()) {
            fxCart.getItems().add("Tour: " + tour.getName() + " Price: $" + tour.getPrice());
        }

        fxTotalPrice.setText("Total: $" + cart.getTotalAmount()); // Update total price
    }

}
