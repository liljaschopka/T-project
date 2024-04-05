package daytrip.model;

import java.time.LocalDate;

/**
 * Represents a reservation for a tour made by a customer.
 */
public class Reservation {
    private Integer reservationID;
    private Integer customerID;
    private Integer tourID;
    private int numberOfParticipants;
    private LocalDate dateBooked;

    /**
     * Constructs a new Reservation instance.
     *
     * @param reservationID The unique identifier for the reservation.
     * @param customerID The ID of the customer making the reservation.
     * @param tourID The ID of the tour being reserved.
     * @param numberOfParticipants The number of participants for the reservation.
     * @param dateBooked The date on which the reservation was made.
     */
    public Reservation(Integer reservationID, Integer customerID, Integer tourID, int numberOfParticipants, LocalDate dateBooked) {
        this.reservationID = reservationID;
        this.customerID = customerID;
        this.tourID = tourID;
        this.numberOfParticipants = numberOfParticipants;
        this.dateBooked = dateBooked;
    }

    // Getters and Setters
    public Integer getReservationID() {
        return reservationID;
    }

    public void setReservationID(Integer reservationID) {
        this.reservationID = reservationID;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getTourID() {
        return tourID;
    }

    public void setTourID(Integer tourID) {
        this.tourID = tourID;
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
