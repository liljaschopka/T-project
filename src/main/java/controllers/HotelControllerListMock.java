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
public class HotelControllerListMock implements HotelControllerInterface {

    // the method returns a list of hotels that should pass the tests
    public List<Hotel> searchForHotels(String location, LocalDateTime checkIn, LocalDateTime checkOut, int capacity) {
        Hotel hotelAkureyri = new Hotel(null, "Akureyri", "Hótel Akureyri", "Þetta hótel er á Akureyri", null, 10000);
        Hotel hotelIsafjordur = new Hotel(null, "Ísafjörður", "Hótel Ísafjörður", "Þetta hótel er á Ísafirði", null, 50000);
        Hotel hotelEgilsstaðir = new Hotel(null, "Egilsstaðir", "Hótel Egilsstaðir", "Þetta hótel er á Egilsstöðum", null, 35000);
        Hotel hotelReykjavik = new Hotel(null, "Reykjavík", "Hótel Reykjavík", "Þetta hótel er í Reykjavík", null, 20000);

        List<Hotel> hotels = List.of(hotelAkureyri, hotelIsafjordur, hotelEgilsstaðir, hotelReykjavik);
        return hotels;
    }
}
