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

    private Integer id;
    private String name;
    private String email;
    private PaymentInfo paymentInfo;


    public User(Integer id, String name, String email, PaymentInfo paymentInfo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.paymentInfo = paymentInfo;
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
    
}
