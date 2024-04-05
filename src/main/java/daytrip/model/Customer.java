package daytrip.model;

import model.PaymentInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer who can make reservations for day trips.
 */
public class Customer {
    private Integer id;
    private String name;
    private String email;
    private PaymentInfo paymentInfo;
    private List<Integer> reservationIds;

    /**
     * Constructs a new Customer instance with specified details.
     *
     * @param id The unique identifier for the customer.
     * @param name The name of the customer.
     * @param email The email address of the customer.
     * @param paymentInfo The payment information of the customer.
     */
    public Customer(Integer id, String name, String email, PaymentInfo paymentInfo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.paymentInfo = paymentInfo;
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

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo){
        this.paymentInfo = paymentInfo;
    }

    public List<Integer> getReservationIds() {
        return reservationIds;
    }

    public void addReservationId(Integer reservationId) {
        this.reservationIds.add(reservationId);
    }
}
