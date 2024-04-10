package controllers;

import model.Hotel;
import model.HotelRoom;

import java.time.LocalDate;
import java.util.List;
/******************************************************************************
 *  Lýsing  :
 *
 *
 *
 *
 *****************************************************************************/
public class HotelController implements HotelControllerInterface {

    public List<Hotel> searchForHotels(String location, LocalDate checkIn, LocalDate checkOut, int capacity) {

        return null;
    }

    public List<HotelRoom> getAvailableRooms(Hotel hotel, int persons) {
        return null;
    }

}
