package daytrip.repository;

import daytrip.model.Reservation;
import daytrip.model.Tour;
import java.util.List;

import java.time.LocalDate;

/**
 * Interface defining the operations related to managing reservations.
 * This includes creating, updating, canceling reservations, and querying reservation details.
 */
public interface ReservationInterface {

    /**
     * Retrieves all reservations.
     *
     * @return A list of all reservations in the system.
     */
    List<Reservation> getAllReservations();

    /**
     * Checks if a tour is available for reservation on a specific date.
     *
     * @param tourID The ID of the tour to check.
     * @param date The date for which availability is checked.
     * @return true if the tour is available, false otherwise.
     */
    boolean checkAvailability(Integer tourID, LocalDate date);

    /**
     * Retrieves the details of a specific tour by its ID.
     *
     * @param tourID The ID of the tour.
     * @return A {@link Tour} instance containing tour details, or null if the tour does not exist.
     */
    Tour getTourDetails(Integer tourID);

    /**
     * Cancels a reservation.
     *
     * @param reservationID The ID of the reservation to cancel.
     * @return true if the reservation was successfully canceled, false otherwise.
     */
    boolean cancelReservation(Integer reservationID);

    /**
     * Updates the number of participants and date for a given reservation.
     *
     * @param reservationID The ID of the reservation to update.
     * @param newNumberOfParticipants The new number of participants.
     * @param newDate The new date for the reservation.
     * @return true if the reservation was successfully updated, false otherwise.
     */
    boolean updateReservation(Integer reservationID, int newNumberOfParticipants, LocalDate newDate);

    /**
     * Makes a new reservation.
     *
     * @param tourID The ID of the tour to reserve.
     * @param customerName The name of the customer making the reservation.
     * @param customerEmail The email of the customer.
     * @param numberOfParticipants The number of participants for the reservation.
     * @param date The date of the reservation.
     * @return true if the reservation was successfully made, false otherwise.
     */
    boolean makeReservation(Integer tourID, String customerName, String customerEmail, int numberOfParticipants, LocalDate date);

    /**
     * Retrieves the details of a specific reservation.
     *
     * @param reservationID The ID of the reservation to retrieve.
     * @return A {@link Reservation} instance with the details of the reservation, or null if the reservation does not exist.
     */
    Reservation getReservationDetails(Integer reservationID);

    /**
     * Checks if a reservation exists in the system.
     *
     * @param reservationID The ID of the reservation to check.
     * @return true if the reservation exists, false otherwise.
     */
    boolean isReservationExists(Integer reservationID);

    /**
     * Retrieves the number of participants for a specific reservation.
     *
     * @param reservationID The ID of the reservation.
     * @return The number of participants in the reservation.
     */
    int getNumberOfParticipants(Integer reservationID);

    /**
     * Retrieves all reservations made by a specific customer.
     *
     * @param customerId The unique identifier of the customer.
     * @return A list of reservations associated with the given customer ID. The list may be empty if no reservations exist.
     */
    List<Reservation> getReservationsByCustomerId(Integer customerId);


}
