package model;

import java.time.LocalDate;
import java.util.Objects;

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
public class Daytrip {

    private Integer daytripID;
    private String name;
    private String description;
    private String location;
    private int duration;
    private double price;
    private int maxParticipants;
    private LocalDate date;

    public Daytrip(Integer daytripID, String name, String description, String location, int duration, double price, int maxParticipants, LocalDate date) {
        this.daytripID = daytripID;
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

    public Integer getDaytripID() {
        return daytripID;
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
        Daytrip daytrip = (Daytrip) o;
        return Objects.equals(daytripID, daytrip.daytripID);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(daytripID);
    }
}
