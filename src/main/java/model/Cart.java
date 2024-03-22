package model;

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
public class Cart {

    private Hotel selectedHotel;
    private Flight selectedFlight;
    private Daytrip selectedDaytrip;
    private int totalAmount;

    public int getTotalAmount() {
        return totalAmount;
    }

    private void updateTotalAmount() {
        if (selectedHotel != null) {
            totalAmount += selectedHotel.getPrice();
        }
        if (selectedFlight != null) {
            totalAmount += selectedFlight.getPrice();
        }
        if (selectedDaytrip != null) {
            totalAmount += selectedDaytrip.getPrice();
        }
    }

    public void addHotelToCart(Hotel hotel) {
        selectedHotel = hotel;
        updateTotalAmount();
    }

    public Hotel getSelectedHotel() {
        return selectedHotel;
    }

    public void addFlightToCart(Flight flight) {
        selectedFlight = flight;
        updateTotalAmount();
    }

    public Flight getSelectedFlight() {
        return selectedFlight;
    }

    public void addDaytripToCart(Daytrip daytrip) {
        selectedDaytrip = daytrip;
        updateTotalAmount();
    }

    public Daytrip getSelectedDaytrip() {
        return selectedDaytrip;
    }

    public void emptyCart() {
        selectedHotel = null;
        selectedFlight = null;
        selectedDaytrip = null;
        totalAmount = 0;
    }

    public boolean isCartEmpty() {
        return totalAmount == 0;
    }
}
