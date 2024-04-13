package model;

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
    private List<Tour> selectedTours;
    private List<HotelRoom> selectedHotelRooms;
    private List<Flight> selectedFlights;

    public Cart() {
        selectedTours = new ArrayList<>(); // Initialize the list of selected tours
        selectedHotelRooms = new ArrayList<>();
        selectedFlights = new ArrayList<>();
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
        if (selectedTour != null) {
            totalAmount += selectedTour.getPrice();
        }
    }

    public void addHotelRoomToCart(HotelRoom room) {
        selectedHotelRooms.add(room);
        selectedRoom = room;
        totalAmount += room.getPrice();
    }

    public List<HotelRoom> getSelectedHotelRooms() {
        return selectedHotelRooms;
    }

    public void setSelectedHotelRoom(HotelRoom room) {
        selectedRoom = room;
    }

    public void removeSelectedHotelRoom(HotelRoom room) {
        totalAmount -= room.getPrice();
        selectedHotelRooms.remove(room);
    }

    public void addFlightToCart(Flight flight) {
        selectedFlights.add(flight);
        selectedFlight = flight;
        totalAmount += flight.getPrice();
        //cartView.updateCartDisplay();
    }

    public List<Flight> getSelectedFlights() {
        return selectedFlights;
    }

    public void setSelectedFlight(Flight flight) {
        selectedFlight = flight;
    }

    public void removeSelectedFlight(Flight flight) {
        totalAmount -= flight.getPrice();
        selectedFlights.remove(flight);
    }

    public void addTourToCart(Tour tour) {
        selectedTours.add(tour); // Add the tour to the list of selected tours
        selectedTour = tour;
        totalAmount += tour.getPrice();
    }

    public void removeSelectedTour(Tour tour) {
        totalAmount -= tour.getPrice();
        selectedTours.remove(tour);
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
}
