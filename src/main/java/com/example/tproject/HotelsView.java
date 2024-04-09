package com.example.tproject;

import controllers.HotelController;
import controllers.PackageController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import model.Cart;
import model.Hotel;
import model.HotelRoom;

import java.util.List;

public class HotelsView {

    @FXML
    private Button fxAddToCart, fxGoBack;
    @FXML
    private ListView<Hotel> fxHotelsList;
    @FXML
    private ListView<HotelRoom> fxHotelRoomsList;

    private HotelController hotelController;
    private PackageController packageController;
    private Cart cart;

    public HotelsView() {
        hotelController = new HotelController();
        packageController = DateSelectorView.getPackageController();
        cart = packageController.getCart();
    }

    @FXML
    public void initialize() {
        List<Hotel> hotels = packageController.findAvailableHotels(hotelController);
        if (hotels.isEmpty()) {
            fxHotelsList.setItems(FXCollections.observableArrayList());
            System.out.println("No hotels found.");
        } else {
            fxHotelsList.setItems(FXCollections.observableArrayList(hotels));
        }
    }

    @FXML
    public void handleHotelSelection(MouseEvent event) {
        Hotel selectedHotel = fxHotelsList.getSelectionModel().getSelectedItem();
        if (selectedHotel != null) {
            List<HotelRoom> availableRooms = hotelController.getAvailableRooms(selectedHotel, packageController.getPersons());
            fxHotelRoomsList.setItems(FXCollections.observableArrayList(availableRooms));
            fxHotelRoomsList.setVisible(true);
            fxAddToCart.setVisible(true);
        } else {
            fxHotelRoomsList.setVisible(false);
            fxAddToCart.setVisible(false);
        }
    }

    @FXML
    private void fxAddToCartHandler(ActionEvent event) {
        HotelRoom selectedRoom = fxHotelRoomsList.getSelectionModel().getSelectedItem();
        if (selectedRoom != null) {
            cart.addHotelRoomToCart(selectedRoom);
        }
    }

    @FXML
    private void setFxGoBackHandler(ActionEvent event) {
        ViewSwitcher.switchTo(View.BOOKINGSELECTOR);
    }
}
