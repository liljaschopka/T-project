package com.example.tproject;

import controllers.HotelController;
import controllers.HotelControllerListMock;
import controllers.PackageController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
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
    private HotelControllerListMock hotelControllerListMock;
    private PackageController packageController;
    private Cart cart;

    public HotelsView() {
        hotelController = new HotelController();
        packageController = DateSelectorView.getPackageController();
        cart = packageController.getCart();
        hotelControllerListMock = new HotelControllerListMock();
    }

    @FXML
    public void initialize() {
        setupHotelListView();
        //List<Hotel> hotels = packageController.findAvailableHotels(hotelController);
        List<Hotel> hotels = packageController.findAvailableHotels(hotelControllerListMock);
        if (hotels.isEmpty()) {
            fxHotelsList.setItems(FXCollections.observableArrayList());
            System.out.println("No hotels found.");
        } else {
            fxHotelsList.setItems(FXCollections.observableArrayList(hotels));
        }
    }

    private void setupHotelListView() {
        fxHotelsList.setCellFactory(listView -> new ListCell<Hotel>() {
            @Override
            protected void updateItem(Hotel hotel, boolean empty) {
                super.updateItem(hotel, empty);
                setText(empty || hotel == null ? null : (hotel.getName() + ", location: " + hotel.getAddress()));
            }
        });
    }

    @FXML
    public void handleHotelSelection(MouseEvent event) {
        Hotel selectedHotel = fxHotelsList.getSelectionModel().getSelectedItem();
        if (selectedHotel != null) {
            //List<HotelRoom> availableRooms = hotelController.getAvailableRooms(selectedHotel, packageController.getPersons());
            List<HotelRoom> availableRooms = hotelControllerListMock.getAvailableRooms(selectedHotel, packageController.getPersons());
            setupHotelRoomListView();
            fxHotelRoomsList.setItems(FXCollections.observableArrayList(availableRooms));
            fxHotelRoomsList.setVisible(true);
            fxAddToCart.setVisible(true);
        } else {
            fxHotelRoomsList.setVisible(false);
            fxAddToCart.setVisible(false);
        }
    }

    private void setupHotelRoomListView() {
        fxHotelRoomsList.setCellFactory(listView -> new ListCell<HotelRoom>() {
            @Override
            protected void updateItem(HotelRoom hotelRoom, boolean empty) {
                super.updateItem(hotelRoom, empty);
                setText(empty || hotelRoom == null ? null : ("Number of guests: " + hotelRoom.getPersons() + ", price: " + hotelRoom.getPrice() + "€"));
            }
        });
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
