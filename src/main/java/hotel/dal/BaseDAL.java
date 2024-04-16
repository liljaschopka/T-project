package hotel.dal;

import hotel.model.Hotel;
import hotel.model.HotelRoom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseDAL {

    protected Connection connection;
    protected final String DATABASE_URL = "jdbc:sqlite:hotel_database.db";

    /**
     * Helper function for both HotelDAL and BookingDAL
     * @param hotelName Name of the hotel - part of PK in hotel table
     * @param hotelAddress Address of the hotel - part of PK in hotel table
     * @return list of hotels java class that contains all their rooms.
     */
    protected Hotel getHotel(String hotelName, String hotelAddress){
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Hotel WHERE (HotelName, Address) = (?,?)");
            stmt.setString(1, hotelName);
            stmt.setString(2, hotelAddress);
            ResultSet results = stmt.executeQuery();
            if(results.next()){
                List<HotelRoom> rooms = getHotelRooms(results.getString(1), results.getString(3));
                return new Hotel(
                        results.getString(1),
                        results.getString(2),
                        results.getString(3),
                        results.getString(4),
                        results.getString(5),
                        rooms);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Hotel not found.");
        return null;
    }

    /**
     * Helper function for both HotelDAL and BookingDAL
     * @param name Name of the hotel - part of PK in hotel table
     * @param address Address of the hotel - part of PK in hotel table
     * @return list of hotel rooms in that hotel.
     */
    protected List<HotelRoom> getHotelRooms(String name, String address){
        List<HotelRoom> rooms = new ArrayList<>();
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
            e.printStackTrace();
        }
        return rooms;
    }
}
