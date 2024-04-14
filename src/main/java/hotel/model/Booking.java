package hotel.model;

import java.time.LocalDate;
import java.util.List;


public class Booking {

    private final int bookingID;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Hotel hotel;
    private List<HotelRoom> rooms;
    private int persons;

    private User owner;


    /**
     * Notið þennan til að senda upplýsingar til gagnagrunns í gegnum Controller úr viðmóti
     * @param checkIn
     * @param checkOut
     * @param nrPers
     * @param owner
     */
    public Booking(LocalDate checkIn, LocalDate checkOut, int nrPers, User owner, Hotel hotel, List<HotelRoom> rooms){
        this.bookingID = -1;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.persons = nrPers;
        this.rooms = rooms;
        this.hotel = hotel;
        this.owner = owner;
    }

    /**
     * Aðeins BookingController á að nota þennan constructor
     */
    public Booking(int id, LocalDate checkIn, LocalDate checkOut, int nrPers, List<HotelRoom> rooms, Hotel hotel, User owner){
        this.bookingID = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.persons = nrPers;
        this.rooms = rooms;
        this.hotel = hotel;
        this.owner = owner;
    }

    public int getBookingID() {
        return bookingID;
    }

    public User getOwner() {
        return owner;
    }

    public List<HotelRoom> getRooms() {
        return rooms;
    }

    public int getPersons(){
        return this.persons;
    }

    public LocalDate getCheckIn(){
        return this.checkIn;
    }

    public LocalDate getCheckOut(){
        return this.checkOut;
    }

    public Hotel getHotel(){
        return this.hotel;
    }

}