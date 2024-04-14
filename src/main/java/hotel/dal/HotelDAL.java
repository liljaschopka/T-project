package hotel.dal;

import hotel.model.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelDAL {
    private static Connection connection;
    private static final String DATABASE_URL = "jdbc:sqlite:hotel_database.db";

    private static void Connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DATABASE_URL);
        } catch (SQLException e) {
            System.err.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Hotel> getHotelsFromLocation(String location){
        List<Hotel> hotels = new ArrayList<>();
        if(connection == null) Connect();
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
            System.err.println(e);
        }
        return hotels;
    }

    public static List<HotelRoom> getHotelRooms(Hotel hotel){
        return getHotelRooms(hotel.getName(), hotel.getAddress());
    }

    public static Hotel getHotel(String hotelName, String hotelAddress){
        if(connection == null) Connect();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Hotel WHERE HotelName=?, HotelAddress=?");
            stmt.setString(1, hotelName);
            stmt.setString(2, hotelAddress);
            ResultSet results = stmt.executeQuery();
            if(results.next()){
                List<HotelRoom> rooms = getHotelRooms(results.getString(1), results.getString(3));
                Hotel hotel = new Hotel(
                        results.getString(1),
                        results.getString(2),
                        results.getString(3),
                        results.getString(4),
                        results.getString(5),
                        rooms);
                return hotel;
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        System.out.println("Hotel not found.");
        return null;
    }

    public static List<HotelRoom> getHotelRooms(String name, String address){
        List<HotelRoom> rooms = new ArrayList<>();
        if(connection == null) Connect();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Room WHERE HotelName=? AND HotelAddress=?");
            stmt.setString(1, name);
            stmt.setString(2, address);
            ResultSet results = stmt.executeQuery();
            while(results.next()){
                HotelRoom room = new HotelRoom(
                        results.getInt(3),
                        results.getInt(4),
                        results.getInt(5),
                        results.getInt(6),
                        results.getString(7)
                );
                rooms.add(room);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return rooms;
    }

    public static List<HotelRoom> getAvailableRooms(Hotel hotel, LocalDate arrival, LocalDate departure){
        List<HotelRoom> rooms = new ArrayList<>();
        if(connection == null) Connect();
        try {
            //Finds every room for given hotel that is not currently booked for a range of dates
            PreparedStatement stmt = connection.prepareStatement("SELECT Number, NrPerson, Floor, Price_Isk, PictureUrl FROM Room JOIN hotel ON (Room.HotelName, Room.HotelAddress) = (hotel.HotelName, hotel.Address)\n" +
                    "WHERE NOT EXISTS (" +
                    "SELECT r.Number, r.NrPerson, r.Floor, r.Price_Isk, r.PictureUrl FROM Room AS r " +
                    "JOIN Hotel AS h ON ((r.HotelName, r.HotelAddress) = (h.HotelName, h.Address)) " +
                    "JOIN Booking_Room AS br ON ((br.HotelName, br.HotelAddress, br.RoomNumber) = (r.HotelName, r.HotelAddress, r.Number)) " +
                    "JOIN Booking AS b ON (b.BookingID = br.BookingID)" +
                    "WHERE (b.arrival NOT BETWEEN ? AND ?) " +
                    "AND (b.departure NOT BETWEEN ? AND ?)" +
                    "AND NOT (b.arrival > ? AND b.departure < ?))" +
                    "AND (hotel.HotelName, hotel.Address) = (?, ?)");
            stmt.setDate(1, Date.valueOf(arrival));
            stmt.setDate(2, Date.valueOf(departure));
            stmt.setDate(3, Date.valueOf(arrival));
            stmt.setDate(4, Date.valueOf(departure));
            stmt.setDate(5, Date.valueOf(arrival));
            stmt.setDate(6, Date.valueOf(departure));
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
            System.err.println(e);
        }
        return rooms;
    }
}
