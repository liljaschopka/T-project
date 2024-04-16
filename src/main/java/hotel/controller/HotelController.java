package hotel.controller;

import hotel.model.Hotel;
import hotel.model.HotelRoom;
import hotel.model.Booking;
import model.User;
import hotel.dal.*;
import java.time.LocalDate;
import java.util.List;

public class HotelController {

    private final HotelDAL hotelDAL;
    private final BookingDAL bookingDAL;
    public HotelController(){
        hotelDAL = new HotelDAL();
        bookingDAL = new BookingDAL();
    }

    /**
     * Gets a list of hotels given a location.
     * @param location - the location of the hotels
     * @return list of hotels
     */
    public List<Hotel> getHotels(String location){
        return hotelDAL.getHotelsFromLocation(location);
    }

    /**
     * Returns a list of rooms over a time period for a given hotel
     * @param hotel - Hotel you want to view the available rooms for
     * @param arrival - Start date
     * @param departure - End date
     * @return - List of available HotelRoom s
     */
    public List<HotelRoom> getAvailableRooms(Hotel hotel, LocalDate arrival, LocalDate departure){
        return hotelDAL.getAvailableRooms(hotel, arrival, departure);
    }

    /**
     * Creates a new booking in the database.
     * @param booking
     */
    public void createBooking(Booking booking){
        bookingDAL.createBooking(booking);
    }

    /**
     * Deletes a booking from the database.
     * @param booking
     */
    public void deleteBooking(Booking booking){
        bookingDAL.deleteBooking(booking);
    }

    /**
     * Gets a list of bookings the user has.
     * @param user
     * @return - List of bookings
     */
    public List<Booking> getBookings(User user){
        return bookingDAL.getBookings(user);
    }
}