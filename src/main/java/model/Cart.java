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

    private HotelRoom selectedRoom;
    private Flight selectedFlight;
    private Daytrip selectedDaytrip;
    private int totalAmount;

    public int getTotalAmount() {
        return totalAmount;
    }

    private void updateTotalAmount() {
        if (selectedRoom != null) {
            totalAmount += selectedRoom.getPrice();
        }
        if (selectedFlight != null) {
            totalAmount += selectedFlight.getPrice();
        }
        if (selectedDaytrip != null) {
            totalAmount += selectedDaytrip.getPrice();
        }
    }

    public void addHotelRoomToCart(HotelRoom room) {
        selectedRoom = room;
        updateTotalAmount();
    }

    public HotelRoom getSelectedHotelRoom() {
        return selectedRoom;
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
        selectedRoom = null;
        selectedFlight = null;
        selectedDaytrip = null;
        totalAmount = 0;
    }

    public boolean isCartEmpty() {
        return totalAmount == 0;
    }
}
