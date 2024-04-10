package model;

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
public class Hotel {
    private List<HotelRoom> rooms;
    private String address;
    private String name;
    private String description;
    private List<String> pictures;
    private int price;

    public Hotel(List<HotelRoom> rooms, String address, String name, String description, List<String> pictures, int price) {
        this.rooms = rooms;
        this.address = address;
        this.name = name;
        this.description = description;
        this.pictures = pictures;
        this.price = price;

    }

    public int getPrice() {
        return price;
    }

    public List<HotelRoom> getAvailableRooms(int persons, LocalDateTime checkIn, LocalDateTime checkOut) {
        return null;
    }

}
