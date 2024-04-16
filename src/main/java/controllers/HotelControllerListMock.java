package controllers;

import model.Hotel;
import model.HotelRoom;

import java.time.LocalDate;
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
public class HotelControllerListMock implements HotelControllerInterface {

    private List<Hotel> hotels;
    private List<HotelRoom> rooms;

    public HotelControllerListMock() {
        hotels = new ArrayList<>();
        hotels.add(new Hotel(null, "Akureyri", "Hótel Akureyri", "Þetta hótel er á Akureyri", null, 100));
        hotels.add(new Hotel(null, "Ísafjörður", "Hótel Ísafjörður", "Þetta hótel er á Ísafirði", null, 50));
        hotels.add(new Hotel(null, "Egilsstaðir", "Hótel Egilsstaðir", "Þetta hótel er á Egilsstöðum", null, 35));
        hotels.add(new Hotel(null, "Reykjavík", "Hótel Reykjavík", "Þetta hótel er í Reykjavík", null, 200));
        rooms = new ArrayList<>();
        // three rooms per hotel
        rooms.add(new HotelRoom(101, 1, 35, 1, null, hotels.get(0)));
        rooms.add(new HotelRoom(102, 2, 50, 1, null, hotels.get(0)));
        rooms.add(new HotelRoom(103, 4, 100, 1, null, hotels.get(0)));
        rooms.add(new HotelRoom(201, 1, 35, 1, null, hotels.get(1)));
        rooms.add(new HotelRoom(202, 2, 50, 1, null, hotels.get(1)));
        rooms.add(new HotelRoom(203, 4, 100, 1, null, hotels.get(1)));
        rooms.add(new HotelRoom(301, 1, 35, 1, null, hotels.get(2)));
        rooms.add(new HotelRoom(302, 2, 50, 1, null, hotels.get(2)));
        rooms.add(new HotelRoom(303, 4, 100, 1, null, hotels.get(2)));
        rooms.add(new HotelRoom(401, 1, 35, 1, null, hotels.get(3)));
        rooms.add(new HotelRoom(402, 2, 50, 1, null, hotels.get(3)));
        rooms.add(new HotelRoom(403, 4, 100, 1, null, hotels.get(3)));

    }

    // the method returns a list of hotels that should pass the tests
    public List<Hotel> searchForHotels(String location, LocalDate checkIn, LocalDate checkOut, int capacity) {
        List<Hotel> matchingHotels = new ArrayList<>();
        for (Hotel hotel : hotels) {
            if (hotel.getAddress().equalsIgnoreCase(location)) {
                matchingHotels.add(hotel);
            }
        }
        return matchingHotels;
    }

    public List<HotelRoom> getAvailableRooms(Hotel hotel, int persons) {
        List<HotelRoom> matchingRooms = new ArrayList<>();
        for (HotelRoom room : rooms) {
            if (room.getHotelName().equals(hotel.getName()) && room.getPersons() >= persons) {
                matchingRooms.add(room);
            }
        }
        return matchingRooms;
    }
}
