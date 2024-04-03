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
        HotelRoom selectedHotelRoom = cart.getSelectedHotelRoom();
        // TODO: create booking by using the BookingController from the H-team
    }

    public void createFlightBooking(User user, Cart cart) {
        Flight selectedFlight = cart.getSelectedFlight();

        // TODO: create booking by using the BookingController from the F-team
    }

    public void createDayTripBooking(User user, Cart cart) {
        Daytrip selectedDaytrip = cart.getSelectedDaytrip();

        // TODO: create booking by using the BookingController from the D-team
    }

    public List<Booking> findBookings(User user) {
        return null;
    }

    public void cancelBooking(String bookingID) {

    }

}
