package model;

import daytrip.model.Tour;

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
        if (selectedTour != null) {
            totalAmount += selectedTour.getPrice();
        }
    }

    public void addHotelRoomToCart(HotelRoom room) {
        selectedRoom = room;
        updateTotalAmount();
    }

    public HotelRoom getSelectedHotelRoom() {
        return selectedRoom;
    }

    public void addFlightToCart(Flight flight) {
        selectedFlight = flight;
        updateTotalAmount();
    }

    public Flight getSelectedFlight() {
        return selectedFlight;
    }

    public void addTourToCart(Tour tour) {
        selectedTour = tour;
        updateTotalAmount();
    }

    public Tour getSelectedTour() {
        return selectedTour;
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
