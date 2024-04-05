package daytrip.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer who can make reservations for day trips.
 */
public class Customer {
    private Integer id;
    private String name;
    private String email;
    private String phoneNumber;
    private List<String> reservationIds;

    /**
     * Constructs a new Customer instance with specified details.
     *
     * @param id The unique identifier for the customer.
     * @param name The name of the customer.
     * @param email The email address of the customer.
     * @param phoneNumber The phone number of the customer.
     */
    public Customer(Integer id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.reservationIds = new ArrayList<>();
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getReservationIds() {
        return reservationIds;
    }

    public void addReservationId(String reservationId) {
        this.reservationIds.add(reservationId);
    }
}
