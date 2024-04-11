package flight;

public class Flight{
    private int flightID;
    private String origin;
    private String destination;
    private int duration;
    private String departureTime;
    private String arrivalTime;
    private int price;

    public Flight(int flightID, String origin, String destination, int duration, String departureTime, String arrivalTime, int price) {
        this.flightID = flightID;
        this.origin = origin;
        this.destination = destination;
        this.duration = duration;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }

    @Override
    public String toString() {
        return "FlightID: " + flightID +
                ", Origin: " + origin +
                ", Destination: " + destination +
                ", Duration: " + duration +
                ", DepartureTime: " + departureTime +
                ", ArrivalTime: " + arrivalTime +
                ", Price: " + price;
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

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public int getPrice() {
        return price;
    }
}
