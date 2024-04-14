package hotel.model;

import java.util.List;

public class User {

    private final int id;
    private String name;
    private String lastName;
    private String email;
    private List<Booking> bookings;

    public User(Integer id, String name, String lastName, String email, List<Booking> bookings) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.bookings = bookings;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null) {
            this.email = email;
        }
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking newBooking) {
        this.bookings.add(newBooking);
    }
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public String getLastName() {
        return this.lastName;
    }
}
