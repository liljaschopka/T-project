package model;

import java.time.LocalDateTime;

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
public class Daytrip {

    private int participants;
    private String location;
    private String description;
    private String title;
    private LocalDateTime date;
    private String daytripID;
    private int price;
    private int duration;

    public Daytrip(int participants, String location, String description, String title, LocalDateTime date, String daytripID, int price, int duration) {
        this.participants = participants;
        this.location = location;
        this.description = description;
        this.title = title;
        this.date = date;
        this.daytripID = daytripID;
        this.price = price;
        this.duration = duration;
    }

    public int getPrice() {
        return price;
    }
}
