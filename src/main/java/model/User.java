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
public class User {

    private String name;
    private String email;
    private PaymentInfo paymentInfo;
    private Booking[] bookings;

    public User(String name, String email, PaymentInfo paymentInfo, Booking[] bookings) {
        this.name = name;
        this.email = email;
        this.paymentInfo = paymentInfo;
        this.bookings = bookings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    public Booking[] getBookings() {
        return bookings;
    }

    public void setBookings(Booking[] bookings) {
        if (bookings != null) {
            this.bookings = bookings;
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
}
