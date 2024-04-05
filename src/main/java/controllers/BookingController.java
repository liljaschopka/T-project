package controllers;

import daytrip.controller.ReservationController;
import daytrip.controller.TourController;
import daytrip.dal.ReservationDAL;
import daytrip.dal.TourDAL;
import daytrip.model.Tour;
import model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        Tour selectedTour = cart.getSelectedTour();

        // TODO: create booking by using the ReservationController from the D-team
        TourDAL tourDal = new TourDAL();
        ReservationDAL reservationDal = new ReservationDAL();
        TourController tourController = new TourController(tourDal);
        ReservationController reservationController = new ReservationController(reservationDal, tourController);

        int daytripId = selectedTour.getTourID();
        String userName = user.getName();
        String userEmail = user.getEmail();
        LocalDate date = selectedTour.getDate();
        int participants = selectedTour.getMaxParticipants();

        boolean success = reservationController.makeReservation(daytripId, userName, userEmail, date, participants, Optional.empty());
        if (success) {
            System.out.println("Daytrip booking successful.");
        } else {
            System.out.println("Daytrip booking failed. Please try again or check the tour availability.");
        }
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
