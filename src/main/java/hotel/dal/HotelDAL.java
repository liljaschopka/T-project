package hotel.dal;

import hotel.model.Hotel;
import hotel.model.HotelRoom;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelDAL extends BaseDAL{

    private void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DATABASE_URL);
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void close(){
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Hotel> getHotelsFromLocation(String location){
        List<Hotel> hotels = new ArrayList<>();
        connect();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Hotel WHERE Location=?");
            stmt.setString(1, location);
            ResultSet results = stmt.executeQuery();
            while(results.next()){
                List<HotelRoom> rooms = getHotelRooms(results.getString(1), results.getString(3));
                Hotel hotel = new Hotel(
                        results.getString(1),
                        results.getString(2),
                        results.getString(3),
                        results.getString(4),
                        results.getString(5),
                        rooms);
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return hotels;
    }

    /**
     * Returns a list of rooms available for a range of date in a given hotel
     * @param hotel Hotel to check available rooms
     * @param arrival the arrival date
     * @param departure the departure date
     * @return list of available rooms for that time period
     */
    public List<HotelRoom> getAvailableRooms(Hotel hotel, LocalDate arrival, LocalDate departure){
        List<HotelRoom> rooms = new ArrayList<>();
        connect();
        try {
            //Finds every room for given hotel that is not currently booked for a range of dates
            PreparedStatement stmt = connection.prepareStatement("SELECT Number, NrPerson, Floor, Price_Isk, PictureUrl FROM Room WHERE (HotelName, HotelAddress, Number) NOT IN(" +
                    "SELECT r.HotelName, r.HotelAddress, r.Number FROM Room AS r " +
                    "JOIN Hotel AS h ON ((r.HotelName, r.HotelAddress) = (h.HotelName, h.Address)) " +
                    "JOIN Booking_Room AS br ON ((br.HotelName, br.HotelAddress, br.RoomNumber) = (r.HotelName, r.HotelAddress, r.Number)) " +
                    "JOIN Booking AS b ON (b.BookingID = br.BookingID)" +
                    "WHERE (b.arrival BETWEEN date(?) AND date(?)) " +
                    "OR (b.departure BETWEEN date(?) AND date(?)) " +
                    "OR (b.arrival < date(?) AND b.departure > date(?))) " +
                    "AND (HotelName, HotelAddress) = (?, ?)");

            stmt.setString(1, arrival.toString());
            stmt.setString(2, departure.toString());
            stmt.setString(3,  arrival.toString());
            stmt.setString(4, departure.toString());
            stmt.setString(5,  arrival.toString());
            stmt.setString(6, departure.toString());
            stmt.setString(7, hotel.getName());
            stmt.setString(8, hotel.getAddress());
            ResultSet results = stmt.executeQuery();
            while(results.next()){
                HotelRoom room = new HotelRoom(
                        results.getInt(1),
                        results.getInt(2),
                        results.getInt(3),
                        results.getInt(4),
                        results.getString(5)
                );
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return rooms;
    }
}
