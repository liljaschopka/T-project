package model;

import java.time.LocalDate;

/**
 * Represents a reservation for a tour made by a customer.
 */
public class DaytripBooking {
    private Integer bookingID;
    private Integer userID;
    private Integer daytripID;
    private int numberOfParticipants;
    private LocalDate dateBooked;

    /**
     * Constructs a new Reservation instance.
     *
     * @param bookingID            The unique identifier for the reservation.
     * @param userID               The ID of the customer making the reservation.
     * @param daytripID            The ID of the tour being reserved.
     * @param numberOfParticipants The number of participants for the reservation.
     * @param dateBooked           The date on which the reservation was made.
     */
    public DaytripBooking(Integer bookingID, Integer userID, Integer daytripID, int numberOfParticipants, LocalDate dateBooked) {
        this.bookingID = bookingID;
        this.userID = userID;
        this.daytripID = daytripID;
        this.numberOfParticipants = numberOfParticipants;
        this.dateBooked = dateBooked;
    }

    // Getters and Setters
    public Integer getBookingID() {
        return bookingID;
    }

    public void setBookingID(Integer bookingID) {
        this.bookingID = bookingID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getDaytripID() {
        return daytripID;
    }

    public void setDaytripID(Integer daytripID) {
        this.daytripID = daytripID;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public LocalDate getDateBooked() {
        return dateBooked;
    }

    public void setDateBooked(LocalDate dateBooked) {
        this.dateBooked = dateBooked;
    }
}
