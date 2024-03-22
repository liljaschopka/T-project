package controllers;

import model.*;

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
public class BookingController {

    public void createHotelBooking(User user, Cart cart) {
        Hotel selectedHotel = cart.getSelectedHotel();
    }

    public void createFlightBooking(User user, Cart cart) {
        Flight selectedFlight = cart.getSelectedFlight();
    }

    public void createDayTripBooking(User user, Cart cart) {
        Daytrip selectedDaytrip = cart.getSelectedDaytrip();
    }

    public List<Booking> findBookings(User user) {
        return null;
    }

    public void cancelBooking(String bookingID) {

    }

    public static void main(String[] args) {

    }
}
