package flight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookingInventory {
    private List<Booking> bookings;
    private int nextBookingID;
    private Map<Integer, User> users;

    public BookingInventory() {
        bookings = new ArrayList<>();
        this.nextBookingID = 1;
        users = new HashMap<>();
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public int generateBookingID() {
        return nextBookingID++;
    }

    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings);
    }

    public List<Booking> getBookingsByUserID(int userID) {
        return bookings.stream()
                .filter(booking -> booking.getUserID() == userID)
                .collect(Collectors.toList());
    }

    public Booking getBookingByID(int bookingID) {
        return bookings.stream()
                .filter(booking -> booking.getBookingID() == bookingID)
                .findFirst()
                .orElse(null);
    }

    public boolean removeBooking(int bookingID) {
        return true;
    }

    public List<Booking> search(String criteria) {
        return new ArrayList<>();
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public List<Booking> getBookingsByUserEmail(String userEmail) {
        return bookings.stream()
                .filter(booking -> {
                    User user = users.get(booking.getUserID());
                    if (user == null) {
                        System.out.println("No user found with ID: " + booking.getUserID());
                        return false;
                    }
                    return user.getEmail().equals(userEmail);
                })
                .collect(Collectors.toList());
    }

}
