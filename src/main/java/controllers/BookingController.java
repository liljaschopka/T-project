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

        // TODO: create booking by using the DaytripController from the D-team
    }

    public List<Booking> findHotelBookings(User user) {
        return null;
    }

    public List<Booking> findFlightBookings(User user) {
        return null;
    }

    public List<DaytripBooking> findDaytripBookings(User user) {
        return null;
    }

    public void cancelHotelBooking(String bookingID) {

    }

    public void cancelFlightBooking(String bookingID) {

    }

    public void cancelDaytripBooking(String bookingID) {

    }
}
