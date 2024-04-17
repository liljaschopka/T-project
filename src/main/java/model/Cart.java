package model;

import daytrip.model.Tour;
import flight.Flight;

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

    private hotel.model.Hotel selectedHotel;
    private hotel.model.HotelRoom selectedRoom;
    private Flight selectedFlight;
    private Tour selectedTour;
    private int totalAmount;
    private List<Tour> selectedTours;
    private List<hotel.model.HotelRoom> selectedHotelRooms;
    private List<Flight> selectedFlights;

    public Cart() {
        selectedTours = new ArrayList<>(); // Initialize the list of selected tours
        selectedHotelRooms = new ArrayList<>();
        selectedFlights = new ArrayList<>();
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void addHotelRoomToCart(hotel.model.HotelRoom room, hotel.model.Hotel hotel) {
        selectedHotelRooms.add(room);
        selectedRoom = room;
        selectedHotel = hotel;
        updateTotalAmount();
    }

    private void updateTotalAmount() {
        if (selectedRoom != null) {
            totalAmount += selectedRoom.getPrice();
        }
        if (selectedFlight != null) {
            totalAmount += selectedFlight.getPrice();
        }
    }

    public List<hotel.model.HotelRoom> getSelectedHotelRooms() {
        return selectedHotelRooms;
    }

    public void setSelectedHotel(hotel.model.Hotel hotel) {
        selectedHotel = hotel;
    }

    public hotel.model.HotelRoom getSelectedHotelRoom() {
        return selectedRoom;
    }

    public void setSelectedHotelRoom(hotel.model.HotelRoom room) {
        selectedRoom = room;
        updateTotalAmount();
    }

    public void removeSelectedHotelRoom(hotel.model.HotelRoom room, hotel.model.Hotel hotel) {
        totalAmount -= room.getPrice();
        selectedHotelRooms.remove(room);

        if (selectedHotelRooms.isEmpty()) {
            selectedHotel = null;
            selectedRoom = null;
        } else {
            selectedRoom = selectedHotelRooms.get(0);
        }
    }

    public hotel.model.Hotel getSelectedHotel() {
        return selectedHotel;
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
        selectedHotel = null;
        selectedHotelRooms.clear();
        selectedFlights.clear();
        selectedTours.clear();
        totalAmount = 0;
    }

    public boolean isCartEmpty() {
        return totalAmount == 0;
    }


}
