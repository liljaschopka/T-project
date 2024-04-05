package daytrip.controller;

import daytrip.model.Reservation;
import daytrip.model.Tour;
import daytrip.repository.ReservationInterface;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

/**
 * Controls the reservation processes, interacting with both the reservation and tour data access layers.
 */
public class ReservationController {
    private ReservationInterface reservationDAL;
    private TourController tourController;

    /**
     * Initializes a new instance of ReservationController.
     *
     * @param reservationDAL The data access layer for reservations.
     * @param tourController The controller for tour-related operations.
     */
    public ReservationController(ReservationInterface reservationDAL, TourController tourController) {
        this.reservationDAL = reservationDAL;
        this.tourController = tourController;
    }

    /**
     * Attempts to make a new reservation.
     *
     * @param tourID The ID of the tour to book.
     * @param customerName The name of the customer making the reservation.
     * @param customerEmail The email of the customer.
     * @param date The date for the reservation.
     * @param numberOfParticipants The number of participants for the reservation.
     * @param customerPhoneNumber An optional phone number of the customer.
     * @return true if the reservation was successfully made, false otherwise.
     */
    public boolean makeReservation(Integer tourID, String customerName, String customerEmail, LocalDate date, int numberOfParticipants, Optional<String> customerPhoneNumber) {
        if (tourID == null || date == null || customerName == null || customerEmail == null || numberOfParticipants <= 0) {
            throw new IllegalArgumentException("Invalid booking information provided.");
        }
        if (tourController.bookTour(tourID, date, numberOfParticipants, customerName, customerEmail, customerPhoneNumber)){
            return reservationDAL.makeReservation(tourID, customerName, customerEmail, numberOfParticipants, date);
        }
        return false;
    }


    /**
     * Cancels an existing reservation.
     *
     * @param reservationID The ID of the reservation to cancel.
     * @return true if the cancellation was successful, false otherwise.
     */
    public boolean cancelReservation(Integer reservationID) {
        if (reservationID == null) {
            throw new NullPointerException("Reservation ID cannot be null");
        }
        return reservationDAL.cancelReservation(reservationID);
    }

    /**
     * Updates the details of an existing reservation.
     *
     * @param reservationID The ID of the reservation to update.
     * @param numberOfParticipants The new number of participants.
     * @param date The new date for the reservation.
     * @return true if the update was successful, false otherwise.
     */
    public boolean updateReservation(Integer reservationID, int numberOfParticipants, LocalDate date) {
        if (reservationID == null || date == null) {
            throw new NullPointerException("Reservation ID and date cannot be null");
        }
        return reservationDAL.updateReservation(reservationID, numberOfParticipants, date);
    }

    /**
     * Retrieves details of a specific tour.
     *
     * @param tourID The ID of the tour to retrieve details for.
     * @return A Tour object containing the details of the specified tour.
     */
    public Tour getTourDetails(Integer tourID) {
        return tourController.getTourDetails(tourID);
    }

    /**
     * Retrieves details of a specific reservation.
     *
     * @param reservationID The ID of the reservation to retrieve details for.
     * @return A Reservation object containing the details of the specified reservation.
     */
    public Reservation getReservationDetails(Integer reservationID) {
        if (reservationID == null) {
            throw new NullPointerException("Reservation ID cannot be null");
        }
        return reservationDAL.getReservationDetails(reservationID);
    }

    /**
     * Checks if a reservation exists.
     *
     * @param reservationID The ID of the reservation to check.
     * @return true if the reservation exists, false otherwise.
     */
    public boolean isReservationExists(Integer reservationID) {
        if (reservationID == null) {
            throw new NullPointerException("Reservation ID cannot be null");
        }
        return reservationDAL.isReservationExists(reservationID);
    }

    /**
     * Retrieves a list of all reservations.
     *
     * @return A list of all reservations.
     */
    public List<Reservation> getAllReservations(){
        return reservationDAL.getAllReservations();
    }


    /**
     * Retrieves the number of participants for a specific reservation.
     *
     * @param reservationID The ID of the reservation.
     * @return The number of participants for the specified reservation.
     */
    public int getNumberOfParticipants(Integer reservationID) {
        if (reservationID == null) {
            throw new NullPointerException("Reservation ID cannot be null");
        }
        return reservationDAL.getNumberOfParticipants(reservationID);
    }

    /**
     * Retrieves a list of all reservations made by a specific customer. This method acts as a bridge between the view and data access layer, providing a more accessible interface for retrieving customer-specific reservations.
     *
     * @param customerId The unique identifier of the customer whose reservations are requested.
     * @return A list of Reservation objects associated with the specified customer. If the customer has no reservations, an empty list is returned.
     * @throws NullPointerException if the provided customerId is null, indicating that the method requires a valid customer ID to function properly.
     */
    public List<Reservation> getReservationsByCustomerId(Integer customerId) {
        if (customerId == null) {
            throw new NullPointerException("Customer ID cannot be null");
        }
        return reservationDAL.getReservationsByCustomerId(customerId);
    }

}
