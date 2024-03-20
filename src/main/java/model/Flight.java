package model;

import java.time.LocalDateTime;

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
public class Flight {

    private String destination;
    private String origin;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private int capacity;
    private int flightNumber;
    private int price;

    //constructor
    public Flight(String destination, String origin, LocalDateTime departure, LocalDateTime arrival, int capacity, int flightNumber, int price) {
        this.destination = destination;
        this.origin = origin;
        this.departure = departure;
        this.arrival = arrival;
        this.capacity = capacity;
        this.flightNumber = flightNumber;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public static void main(String[] args) {

    }
}
