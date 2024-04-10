package controllers;

import model.Hotel;
import model.HotelRoom;

import java.time.LocalDate;
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

    List<Hotel> searchForHotels(String location, LocalDate checkIn, LocalDate checkOut, int capacity);

    List<HotelRoom> getAvailableRooms(Hotel hotel, int persons);
}

