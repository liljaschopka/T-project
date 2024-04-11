package daytrip.controller;

import daytrip.model.Tour;
import daytrip.repository.TourInterface;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for managing tour-related operations.
 */
public class TourController {
    private TourInterface tourInter;

    /**
     * Constructs a TourController with the specified tour interface.
     *
     * @param tourInter The interface for tour-related data access.
     */
    public TourController(TourInterface tourInter) {
        this.tourInter = tourInter;
    }

    /**
     * Retrieves the details of a tour identified by the given tour ID.
     *
     * @param tourID The unique identifier of the tour.
     * @return A Tour object containing the details of the tour, or null if the tour is not found.
     * @throws NullPointerException if the tourID is null.
     * @throws IllegalArgumentException if the retrieved tour has invalid (negative) duration or price.
     */
    public Tour getTourDetails(Integer tourID) {
        if (tourID == null) {
            throw new NullPointerException("Tour ID cannot be null");
        }
        Tour tour = tourInter.getTourDetails(tourID);
        if (tour == null) {
            return null;
        }

        if (tour.getDuration() <= 0 || tour.getPrice() < 0) {
            throw new IllegalArgumentException("Tour cannot have negative duration or price.");
        }
        return tour;
    }

    /**
     * Retrieves a list of all available tours.
     *
     * @return A list of tours that are currently available.
     */
    public List<Tour> getAvailableTours(){
        return tourInter.getAvailableTours();
    }

    /**
     * Checks whether a specific tour is available for booking on a given date for a specified number of participants.
     *
     * @param tourID The unique identifier of the tour.
     * @param date The date for which availability needs to be checked.
     * @param participants The number of participants.
     * @return true if the tour is available for booking; false otherwise.
     * @throws NullPointerException if the tourID is null.
     */
    public boolean checkAvailability(Integer tourID, LocalDate date, int participants) {
        if (tourID == null) {
            throw new NullPointerException("Tour ID cannot be null");
        }

        if (date == null || date.isBefore(LocalDate.now())) {
            return false; // Tour cannot be booked in the past.
        }

        // Retrieve tour details to check various conditions
        Tour tour = tourInter.getTourDetails(tourID);
        if (tour == null) {
            return false; // Tour does not exist
        }

        if (participants <= 0) {
            return false; // Invalid number of participants
        }

        if (participants > tour.getMaxParticipants()) {
            return false; // Too many participants
        }

        return true;
    }


    /**
     * Searches for tours that match the specified location and fall within the specified date range,
     * and have enough spots for the specified number of participants.
     *
     * @param location The location where the user wants to find a tour.
     * @param startDate The start date of the date range for which the user wants to attend the tour.
     * @param endDate The end date of the date range for which the user wants to attend the tour.
     * @param participants The number of participants for which the user is seeking availability.
     * @return A list of tours that match the criteria. The list will be empty if no tours are found.
     */
    public List<Tour> searchTours(String location, LocalDate startDate, LocalDate endDate, int participants){
        List<Tour> availableTours = tourInter.getAvailableTours();


        return availableTours.stream()
                .filter(tour -> tour.getLocation().equalsIgnoreCase(location))
                .filter(tour -> !tour.getDate().isBefore(startDate) && !tour.getDate().isAfter(endDate))
                .filter(tour -> participants <= tour.getMaxParticipants())
                .collect(Collectors.toList());
    }

    /**
     * Attempts to book a tour for a given date, number of participants, and customer details.
     *
     * @param tourID The unique identifier of the tour to book.
     * @param date The date of the booking.
     * @param participants The number of participants for the booking.
     * @param customerName The name of the customer making the booking.
     * @param customerEmail The email address of the customer.
     * @param customerPhoneNumber An optional phone number of the customer.
     * @return true if the booking was successful; false otherwise.
     * @throws IllegalArgumentException if any required booking information is missing or invalid.
     */
    public boolean bookTour(Integer tourID, LocalDate date, int participants, String customerName, String customerEmail, Optional<String> customerPhoneNumber) {
        if (tourID == null || date == null || customerName == null || customerEmail == null || participants <= 0) {
            throw new IllegalArgumentException("Missing or invalid booking information");
        }

        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Booking date cannot be in the past");
        }

        if (!checkAvailability(tourID, date, participants)) {
            return false;
        }

        return tourInter.bookTour(tourID, date, participants, customerName, customerEmail, customerPhoneNumber);
    }
}
