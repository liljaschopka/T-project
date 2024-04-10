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
public class HotelControllerListMock implements HotelControllerInterface {

    // the method returns a list of hotels that should pass the tests
    public List<Hotel> searchForHotels(String location, LocalDate checkIn, LocalDate checkOut, int capacity) {
        Hotel hotelAkureyri = new Hotel(null, "Akureyri", "Hótel Akureyri", "Þetta hótel er á Akureyri", null, 10000);
        Hotel hotelIsafjordur = new Hotel(null, "Ísafjörður", "Hótel Ísafjörður", "Þetta hótel er á Ísafirði", null, 50000);
        Hotel hotelEgilsstadir = new Hotel(null, "Egilsstaðir", "Hótel Egilsstaðir", "Þetta hótel er á Egilsstöðum", null, 35000);
        Hotel hotelReykjavik = new Hotel(null, "Reykjavík", "Hótel Reykjavík", "Þetta hótel er í Reykjavík", null, 20000);

        List<Hotel> hotels = List.of(hotelAkureyri, hotelIsafjordur, hotelEgilsstadir, hotelReykjavik);
        return hotels;
    }

    public List<HotelRoom> getAvailableRooms(Hotel hotel, int persons) {
        HotelRoom room1 = new HotelRoom(101, persons, 10000, 1, null, hotel);
        HotelRoom room2 = new HotelRoom(102, persons, 10000, 1, null, hotel);
        HotelRoom room3 = new HotelRoom(103, persons, 10000, 1, null, hotel);

        List<HotelRoom> rooms = List.of(room1, room2, room3);
        return rooms;
    }
}
