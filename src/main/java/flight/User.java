package flight;

import java.util.List;

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

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }
}
