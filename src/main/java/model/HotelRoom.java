package model;

import java.time.LocalDateTime;

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
public class HotelRoom {

    private int RoomNumber;
    private int persons;
    private int price;
    private int floor;
    private String pictureURL;
    private Hotel hotel;

    public HotelRoom(int RoomNumber, int persons, int price, int floor, String pictureURL, Hotel hotel) {

        this.RoomNumber = RoomNumber;
        this.persons = persons;
        this.price = price;
        this.floor = floor;
        this.pictureURL = pictureURL;
        this.hotel = hotel;
    }

    public boolean isAvailable(LocalDateTime checkIn, LocalDateTime checkOut) {
        return true;
    }

    public int getPrice() {
        return price;
    }

    public int getPersons() {
        return persons;
    }

    public String getHotelName() {
        return hotel.getName();
    }

    public int getRoomNumber() {
        return RoomNumber;
    }

    //TODO:
    public String getDescription() {
        return null;
    }
}
