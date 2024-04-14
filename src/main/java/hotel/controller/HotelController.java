package hotel.controller;

import hotel.model.*;
import hotel.dal.*;
import java.time.LocalDate;
import java.util.List;

public class HotelController {

    /**
     * Gets a list of hotels given a location.
     * @param location - the location of the hotels
     * @return list of hotels
     */
    public static List<Hotel> getHotels(String location){
        return HotelDAL.getHotelsFromLocation(location);
    }

    /**
     * Returns a list of rooms over a time period for a given hotel
     * @param hotel - Hotel you want to view the available rooms for
     * @param arrival - Start date
     * @param departure - End date
     * @return - List of available HotelRoom s
     */
    public static List<HotelRoom> getAvailableRooms(Hotel hotel, LocalDate arrival, LocalDate departure){
        /*
        GEFUR ÖLL HERBERGI SAMA HVERSU MARGIR ERU Í BÓKUNINNI - kemur annað fall sem skilar list<HotelRoom[]> sem gefur tillögur að herbergjum fyrir fjöldann
         */
        return HotelDAL.getAvailableRooms(hotel, arrival, departure);
    }

    /**
     * Creates a new booking in the database.
     * @param booking
     */
    public static void createBooking(Booking booking){
        BookingDAL.createBooking(booking);
    }

    /**
     * Deletes a booking from the database.
     * @param booking
     */
    public static void deleteBooking(Booking booking){
        BookingDAL.deleteBooking(booking);
    }

    /**
     * Gets a list of bookings the user has.
     * @param user
     * @return - List of bookings
     */
    public static List<Booking> getBookings(User user){
        return BookingDAL.getBookings(user);
    }
}