package hotel.model;

public class HotelRoom {

    private int RoomNumber;
    private int persons;
    private int price;
    private int floor;
    private String pictureURL;

    public HotelRoom(int RoomNumber, int persons, int floor, int price, String pictureURL) {
        this.RoomNumber = RoomNumber;
        this.persons = persons;
        this.price = price;
        this.floor = floor;
        this.pictureURL = pictureURL;
    }

    public int getRoomNumber() {
        return RoomNumber;
    }

    public int getPersons() {
        return persons;
    }

    public int getFloor() {
        return floor;
    }

    public String getPictureURL() {
        return pictureURL;
    }
    public int getPrice() {
        return price;
    }
}