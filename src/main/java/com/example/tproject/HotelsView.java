package com.example.tproject;

import controllers.PackageController;
import hotel.model.Hotel;
import hotel.model.HotelRoom;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Cart;

import java.util.List;

public class HotelsView {

    @FXML
    public ImageView fxHotelImageView;
    @FXML
    private Button fxAddToCart, fxGoBack;
    @FXML
    private ListView<hotel.model.Hotel> fxHotelsList;
    @FXML
    private ListView<hotel.model.HotelRoom> fxHotelRoomsList;

    private hotel.controller.HotelController hotelController;
    private PackageController packageController;
    private Cart cart;
    private CartView cartView;

    public HotelsView() {
        hotelController = new hotel.controller.HotelController();
        packageController = DateSelectorView.getPackageController();
        cart = packageController.getCart();
    }

    @FXML
    public void initialize() {
        setupHotelListView();
        List<hotel.model.Hotel> hotels = packageController.findAvailableHotels(hotelController);
        if (hotels.isEmpty()) {
            fxHotelsList.setItems(FXCollections.observableArrayList());
            System.out.println("No hotels found.");
        } else {
            fxHotelsList.setItems(FXCollections.observableArrayList(hotels));
        }
    }

    private void setupHotelListView() {
        fxHotelsList.setCellFactory(listView -> new ListCell<hotel.model.Hotel>() {
            @Override
            protected void updateItem(hotel.model.Hotel hotel, boolean empty) {
                super.updateItem(hotel, empty);
                setText(empty || hotel == null ? null : (hotel.getName() + ", location: " + hotel.getAddress()));
            }
        });
    }

    @FXML
    public void handleHotelRoomSelection(MouseEvent event) {
        HotelRoom selectedHotelRoom = fxHotelRoomsList.getSelectionModel().getSelectedItem();
        if (selectedHotelRoom != null) {
            //HotelRoom selectedHotelRoom = fxHotelRoomsList.getSelectionModel().getSelectedItem();
            //if (selectedHotelRoom != null) {
            String hotelRoomImageName = selectedHotelRoom.getPictureURL();
            hotelRoomImageName = hotelRoomImageName.replaceFirst("src/main/resources", "");
            //System.out.print(hotelRoomImageName);
            Image hotelRoomImage = new Image(getClass().getResourceAsStream(hotelRoomImageName));
            fxHotelImageView.setImage(hotelRoomImage);
        }
    }

    @FXML
    public void handleHotelSelection(MouseEvent event) {
        Hotel selectedHotel = fxHotelsList.getSelectionModel().getSelectedItem();
        if (selectedHotel != null) {
            try {
                List<hotel.model.HotelRoom> availableRooms = packageController.getAvailableRooms(selectedHotel, hotelController);
                //List<HotelRoom> availableRooms = packageController.getAvailableRooms(selectedHotel, hotelControllerListMock);
                setupHotelRoomListView();
                if (availableRooms.isEmpty()) {
                    fxHotelRoomsList.setItems(FXCollections.observableArrayList());
                    fxHotelRoomsList.setPlaceholder(new Label("No available rooms"));
                } else {
                    fxHotelRoomsList.setItems(FXCollections.observableArrayList(availableRooms));
                }
                fxHotelRoomsList.setVisible(true);
                fxAddToCart.setVisible(true);
            } catch (IllegalArgumentException e) {
                fxHotelRoomsList.setItems(FXCollections.observableArrayList());
                fxHotelRoomsList.setPlaceholder(new Label("No available rooms"));
                fxHotelRoomsList.setVisible(true);
                fxAddToCart.setVisible(false);
                System.out.println("Error: " + e.getMessage());
            }


            // Setja mynd (vantar URL frá Hotel)
            String imageName = selectedHotel.getPicture();
            imageName = imageName.replaceFirst("src/main/resources", "");
            //System.out.println(selectedHotel.getPicture());
            Image hotelImage = new Image(getClass().getResourceAsStream(imageName));
            //hotelImage = new Image(getClass().getResourceAsStream("/com/example/tproject/myndir/AK-Hotel.png"));
            fxHotelImageView.setImage(hotelImage);


            //System.out.println(selectedHotel.getPicture());
        } else {
            fxHotelRoomsList.setVisible(false);
            fxAddToCart.setVisible(false);
        }
    }

    private void setupHotelRoomListView() {
        fxHotelRoomsList.setCellFactory(listView -> new ListCell<hotel.model.HotelRoom>() {
            @Override
            protected void updateItem(hotel.model.HotelRoom hotelRoom, boolean empty) {
                super.updateItem(hotelRoom, empty);
                setText(empty || hotelRoom == null ? null : ("Number of guests: " + hotelRoom.getPersons() + ", price: " + hotelRoom.getPrice() + " ISK"));
            }
        });
    }


    @FXML
    private void fxAddToCartHandler(ActionEvent event) {
        hotel.model.HotelRoom selectedRoom = fxHotelRoomsList.getSelectionModel().getSelectedItem();
        hotel.model.Hotel selectedHotel = fxHotelsList.getSelectionModel().getSelectedItem();
        if (selectedRoom != null) {
            cart.addHotelRoomToCart(selectedRoom, selectedHotel);
            cart.setSelectedHotel(selectedHotel);
        }
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("User Information");
        infoAlert.setHeaderText("Added to cart!");
        infoAlert.setContentText("A hotel room for " + selectedRoom.getPersons() + " guest/s at " + selectedHotel.getName() + " has been added to your cart.");
        infoAlert.showAndWait();
    }

    @FXML
    private void setFxGoBackHandler(ActionEvent event) {
        ViewSwitcher.switchTo(View.BOOKINGSELECTOR);
    }
}
