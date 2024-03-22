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
public class HotelController implements HotelControllerInterface {

    public List<Hotel> searchForHotels(String location, LocalDateTime checkIn, LocalDateTime checkOut, int capacity) {

        return null;
    }

    public List<HotelRoom> getAvailableRooms(Hotel hotel, int persons) {
        return null;
    }

}
