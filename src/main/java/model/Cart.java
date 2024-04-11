package model;

import com.example.tproject.CartView;
import daytrip.model.Tour;

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
    private CartView cartView;
    private List<Tour> selectedTours;

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
        for (Tour tour : selectedTours) {
            totalAmount += tour.getPrice();
        }
    }

    public void addHotelRoomToCart(HotelRoom room) {
        selectedRoom = room;
        updateTotalAmount();
        //cartView.updateCartDisplay();
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
        updateTotalAmount();
        cartView.updateCartDisplay();
    }

    public void setSelectedTours(List<Tour> tours) {
        selectedTours = tours;
        updateTotalAmount();
    }

    public List<Tour> getSelectedTours() {
        return selectedTours;
    }


    public Tour getSelectedTour() {
        return selectedTour;
    }

    public void setSelectedTour(Tour tour) {
        selectedTour = tour;
        updateTotalAmount();
    }

    public void emptyCart() {
        selectedRoom = null;
        selectedFlight = null;
        selectedTour = null;
        totalAmount = 0;
    }

    public boolean isCartEmpty() {
        return totalAmount == 0;
    }
}
