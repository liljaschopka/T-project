package controllers;

import model.Hotel;
import model.HotelRoom;

import java.time.LocalDateTime;
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
public class HotelControllerEmptyMock implements HotelControllerInterface {

    // the method returns an empty list of hotels
    public List<Hotel> searchForHotels(String location, LocalDateTime checkIn, LocalDateTime checkOut, int capacity) {
        List<Hotel> hotels = List.of();
        return hotels;
    }

    public List<HotelRoom> getAvailableRooms(Hotel hotel, int persons) {
        List<HotelRoom> rooms = List.of();
        return rooms;
    }
}
