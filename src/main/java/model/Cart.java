package model;

import daytrip.model.Tour;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

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
public class Cart {

    private HotelRoom selectedRoom;
    private Flight selectedFlight;
    private Tour selectedTour;
    private int totalAmount;
    private List<Tour> selectedTours;
    private ListView fxCart;

    public Cart() {
        selectedTours = new ArrayList<>(); // Initialize the list of selected tours
    }

    public int getTotalAmount() {
        return totalAmount;

    }


    private void updateTotalAmount() {
        if (selectedRoom != null) {
            totalAmount += selectedRoom.getPrice();
        }
        if (selectedFlight != null) {
            totalAmount += selectedFlight.getPrice();
        }
    }

    public void addHotelRoomToCart(HotelRoom room) {
        selectedRoom = room;
        updateTotalAmount();
    }

    public HotelRoom getSelectedHotelRoom() {
        return selectedRoom;
    }

    public void setSelectedHotelRoom(HotelRoom room) {
        selectedRoom = room;
        updateTotalAmount();
    }

    public void addFlightToCart(Flight flight) {
        selectedFlight = flight;
        updateTotalAmount();
        //cartView.updateCartDisplay();
    }

    public Flight getSelectedFlight() {
        return selectedFlight;
    }

    public void setSelectedFlight(Flight flight) {
        selectedFlight = flight;
        updateTotalAmount();
    }

    public void addTourToCart(Tour tour) {
        selectedTours.add(tour); // Add the tour to the list of selected tours
        selectedTour = tour;
        totalAmount += tour.getPrice();
    }

    public Tour getSelectedTour() {
        return selectedTour;
    }

    public void removeSelectedTour(Tour tour) {
        totalAmount -= tour.getPrice();
        selectedTours.remove(tour);
    }

    public void removeSelectedHotelRoom(HotelRoom hotel) {
        totalAmount -= hotel.getPrice();
    }

    public List<Tour> getSelectedTours() {
        return selectedTours;
    }

    public void emptyCart() {
        selectedRoom = null;
        selectedFlight = null;
        selectedTours.clear();
        totalAmount = 0;
    }

    public boolean isCartEmpty() {
        return totalAmount == 0;
    }

    public void removeSelectedFlight(Flight flight) {
        totalAmount -= flight.getPrice();
    }
}
