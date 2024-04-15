package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Flight {
    private int flightID;
    private String origin;
    private String destination;
    private int duration;
    private LocalDate date;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int price;
    private int totalSeats;
    private int availableSeats;

    public Flight(int flightID, String origin, String destination, int duration,
                  LocalDate date, LocalDateTime departureTime, LocalDateTime arrivalTime,
                  int price, int totalSeats, int availableSeats) {
        this.flightID = flightID;
        this.origin = origin;
        this.destination = destination;
        this.duration = duration;
        this.date = date;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return "FlightID: " + flightID +
                ", Origin: " + origin +
                ", Destination: " + destination +
                ", Duration: " + duration +
                ", Date: " + date.format(dateFormatter) +
                ", DepartureTime: " + departureTime.format(timeFormatter) +
                ", ArrivalTime: " + arrivalTime.format(timeFormatter) +
                ", Price: " + price +
                ", Total Seats: " + totalSeats +
                ", Available Seats: " + availableSeats;
    }

    public boolean bookSeat() {
        if (availableSeats > 0) {
            availableSeats--;
            return true;
        }
        return false;
    }

    public String getDepartureTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return departureTime.format(dateTimeFormatter);
    }

    public String getArrivalTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return arrivalTime.format(dateTimeFormatter);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getDuration() {
        return duration;
    }

    public int getPrice() {
        return price;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    //bætti við þessu fyrir cartview:
    public String getFlightDetails() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return "FlightID: " + flightID + ", Departure: " + departureTime.format(timeFormatter) +
                ", Arrival: " + arrivalTime.format(timeFormatter);
    }
}
