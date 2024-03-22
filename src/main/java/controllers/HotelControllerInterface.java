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
public interface HotelControllerInterface {

    List<Hotel> searchForHotels(String location, LocalDateTime checkIn, LocalDateTime checkOut, int capacity);

    List<HotelRoom> getAvailableRooms(Hotel hotel, int persons);
}

