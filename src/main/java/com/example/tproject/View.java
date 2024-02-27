package com.example.tproject;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public enum View {
    DATESELECTOR("DateSelector-view.fxml"),
    BOOKINGSELECTOR("BookingSelector-view.fxml"),
    PAYMENT("Payment-view.fxml"),
    HOTELS("Hotels-view.fxml"),
    FLIGHTS("Flights-view.fxml"),
    DAYTRIPS("Daytrips-view.fxml");

    private final String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}

