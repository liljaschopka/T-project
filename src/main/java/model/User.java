package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
public class User {

    private Integer id;
    private String name;
    private String email;
    private PaymentInfo paymentInfo;
    private List<String> bookingIds;

    public User(Integer id, String name, String email, PaymentInfo paymentInfo, List<String> bookingIds) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.paymentInfo = paymentInfo;
        this.bookingIds = bookingIds;
    }

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

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        if (paymentInfo != null) {
            this.paymentInfo = paymentInfo;
        }
    }

    public List<String> getBookingIds() {
        return bookingIds;
    }

    public void addBookingId(String bookingId) {
        this.bookingIds.add(bookingId);
    }

    public List<String> getBookingDetails() {
        //TODO ná í booking úr eh dataset eða eh hm idk á eftir að figure it out
        if (bookingIds == null || bookingIds.isEmpty()) {
            return new ArrayList<>();
        }
        return bookingIds.stream()
                .map(id -> "Booking ID: " + id + " - Details of booking here.")
                .collect(Collectors.toList());
        
    }
}
