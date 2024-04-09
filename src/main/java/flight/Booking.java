package flight;

public class Booking {
    private final Flight flight;
    private final int[] seatNumbers;

    public Booking(Flight flight, int[] seatNumbers) {
        this.flight = flight;
        this.seatNumbers = seatNumbers;
    }

    public Flight getFlight() {
        return flight;
    }

    public int[] getSeatNumbers() {
        return seatNumbers;
    }
}

