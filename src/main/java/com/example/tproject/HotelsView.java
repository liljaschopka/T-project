package com.example.tproject;

import controllers.HotelController;
import controllers.PackageController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Cart;
import model.Hotel;
import model.HotelRoom;

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
public class HotelsView {
    @FXML
    private Button fxAddToCart;
    @FXML
    private Button fxGoBack;
    @FXML
    private Button fxShowAvailableRooms;
    @FXML
    private ListView<Hotel> fxHotelsList;
    @FXML
    private ListView<HotelRoom> fxHotelRoomsList;

    private HotelController hotelController = new HotelController();
    private PackageController packageController = DateSelectorView.getPackageController();

    private Cart cart = packageController.getCart();
    private int persons = packageController.getPersons();

    @FXML
    public void fxShowAvailableRoomsHandler(ActionEvent ActionEvent) {
        // birtum hótelherbergin sem eru laus fyrir hótelið sem var valið
        fxHotelsList.getSelectionModel().getSelectedItems().forEach((selected) ->
                fxHotelRoomsList.getItems().add((HotelRoom) packageController.getAvailableRooms(selected, hotelController)));
    }

    @FXML
    public void fxAddToCartHandler(ActionEvent ActionEvent) {
        // bætum hótelherberginu sem var valið í körfuna
        fxHotelRoomsList.getSelectionModel().getSelectedItems().forEach((selected) ->
                cart.addHotelRoomToCart(selected));
    }


    @FXML
    public void setFxGoBackHandler(ActionEvent ActionEvent) {
        ViewSwitcher.switchTo(View.BOOKINGSELECTOR);
    }


    public void initialize() {
        fxHotelsList.setItems((ObservableList) packageController.findAvailableHotels(hotelController));
    }
}
