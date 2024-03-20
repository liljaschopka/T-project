package controllers;

import model.Hotel;

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

    public List<Hotel> searchForHotels(String location, LocalDateTime checkIn, LocalDateTime checkOut, int capacity) {
        return null;
    }
}
