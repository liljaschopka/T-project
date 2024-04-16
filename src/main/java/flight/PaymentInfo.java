package flight;

public class PaymentInfo {
    private String cardType;
    private String cardNumber;
    private String expirationDate;
    private String cvv;

    public PaymentInfo(String cardType, String cardNumber, String expirationDate, String cvv) {
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    // Getters and setters as needed
    public String getCardType() {
        return cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getCvv() {
        return cvv;
    }
}

