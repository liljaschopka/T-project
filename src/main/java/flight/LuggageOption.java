package flight;

public class LuggageOption {
    private String description;
    private double additionalFee;

    public LuggageOption(String description, double additionalFee) {
        this.description = description;
        this.additionalFee = additionalFee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAdditionalFee() {
        return additionalFee;
    }

    public void setAdditionalFee(double additionalFee) {
        this.additionalFee = additionalFee;
    }

    @Override
    public String toString() {
        return "LuggageOption{" +
                "description='" + description + '\'' +
                ", additionalFee=" + additionalFee +
                '}';
    }
}

