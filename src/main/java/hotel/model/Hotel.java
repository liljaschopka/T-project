package hotel.model;

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
public class Hotel {
    private List<HotelRoom> rooms;
    private String address;
    private String location;
    private String name;
    private String description;
    private String picture;

    public Hotel(String name, String location, String address, String picture, String description, List<HotelRoom> rooms) {
        this.rooms = rooms;
        this.address = address;
        this.location = location;
        this.name = name;
        this.description = description;
        this.picture = picture;
    }

    public void setRooms(List<HotelRoom> rooms) {
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return this.address;
    }

    public List<HotelRoom> getAvailableRooms(int persons, LocalDate checkIn, LocalDate checkOut) {
        return null;
    }

    public List<HotelRoom> getRooms() {
        return this.rooms;
    }

}
