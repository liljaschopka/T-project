package daytrip.repository;

import daytrip.model.Tour;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

/**
 * Interface for managing tours, including retrieving tour details, checking availability, and booking tours.
 */
public interface TourInterface {

    /**
     * Retrieves the details of a tour identified by its ID.
     *
     * @param tourID The unique identifier of the tour.
     * @return A {@link Tour} object containing the details of the specified tour, or null if no such tour exists.
     */
    Tour getTourDetails(Integer tourID);

    /**
     * Fetches a list of tours that are available for booking.
     *
     * @return A list of {@link Tour} objects representing the tours available for booking.
     */
    List<Tour> getAvailableTours();

    /**
     * Checks whether a specific tour is available for booking on a given date for a specified number of participants.
     *
     * @param tourID The ID of the tour to check availability for.
     * @param date The date for which to check the tour's availability.
     * @param participants The number of participants for the booking.
     * @return true if the tour is available for the given date and number of participants; false otherwise.
     */
    boolean checkAvailability(Integer tourID, LocalDate date, int participants);

    /**
     * Attempts to book a tour for a given date and number of participants.
     *
     * @param tourID The ID of the tour to book.
     * @param date The date for which to book the tour.
     * @param participants The number of participants for the booking.
     * @param customerName The name of the customer making the booking.
     * @param customerEmail The email address of the customer.
     * @param customerPhoneNumber An optional phone number of the customer.
     * @return true if the booking was successfully made; false otherwise.
     */
    boolean bookTour(Integer tourID, LocalDate date, int participants, String customerName, String customerEmail, Optional<String> customerPhoneNumber);
}
