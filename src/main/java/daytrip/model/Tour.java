package daytrip.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a tour available for booking in the day trip booking application.
 */
public class Tour {
    private Integer tourID;
    private String name;
    private String description;
    private String location;
    private int duration;
    private double price;
    private int maxParticipants;
    private LocalDate date;


    /**
     * Constructs a Tour instance with detailed information.
     *
     * @param tourID The unique identifier for the tour.
     * @param name The name of the tour.
     * @param description A description of what the tour entails.
     * @param location The location where the tour takes place.
     * @param duration The duration of the tour in hours.
     * @param price The price per participant for the tour.
     * @param maxParticipants The maximum number of participants allowed on the tour.
     * @param date The scheduled date of the tour.
     */

    public Tour(Integer tourID, String name, String description, String location, int duration, double price, int maxParticipants, LocalDate date) {
        this.tourID = tourID;
        this.name = name;
        this.description = description;
        this.location = location;
        this.duration = duration;
        this.price = price;
        this.maxParticipants = maxParticipants;
        this.date = date;
    }

    // Getters and Setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getTourID() {
        return tourID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public int getDuration() {
        return duration;
    }

    public double getPrice() {
        return price;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o The reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return Objects.equals(tourID, tour.tourID);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(tourID);
    }
}
