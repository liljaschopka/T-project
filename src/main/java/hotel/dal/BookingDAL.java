package hotel.dal;


import hotel.model.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BookingDAL {

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

    /**
     * Gets a list of bookings the user currently has booked
     * @param user - java class of a user
     * @return
     */
    public static List<Booking> getBookings(User user){
        List<Booking> bookings = new ArrayList<>();
        if(connection == null) Connect();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Booking WHERE OwnerLastName=? AND OwnerEmail=?");
            stmt.setString(1, user.getLastName());
            stmt.setString(2, user.getEmail());
            ResultSet results = stmt.executeQuery();
            while(results.next()){
                PreparedStatement rmstmt = connection.prepareStatement(
                        "SELECT * FROM Room WHERE (HotelName, HotelAddress, Number) IN(" +
                                "SELECT HotelName, HotelAddress, RoomNumber FROM Booking_Room WHERE BookingID=?)");
                rmstmt.setInt(1,results.getInt(1));
                ResultSet rmResults = rmstmt.executeQuery();
                List<HotelRoom> rooms = new ArrayList<>();
                while(rmResults.next()){
                    HotelRoom room = new HotelRoom(
                            rmResults.getInt(3),
                            rmResults.getInt(4),
                            rmResults.getInt(5),
                            rmResults.getInt(6),
                            rmResults.getString(7)
                    );
                    rooms.add(room);
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
                String arrival =  results.getString("Arrival");
                String departure =  results.getString("Arrival");
                Booking newBooking = new Booking(
                        results.getInt(1),
                        LocalDate.parse(arrival, formatter),
                        LocalDate.parse(departure, formatter),
                        results.getInt(4),
                        rooms,
                        HotelDAL.getHotel(rmResults.getString(1), rmResults.getString(3)),
                        user
                );
                bookings.add(newBooking);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return bookings;
    }

    /**
     * Returns a list of rooms available for a range of date in a given hotel
     * @param hotel Hotel to check available rooms
     * @param arrival the arrival date
     * @param departure the departure date
     * @return
     */

    /**
     * Creates a new booking in the DB
     * @param booking - java class of a booking
     */
    public static void createBooking(Booking booking){
        if(connection == null) Connect();
        try {
            PreparedStatement userExists = connection.prepareStatement("SELECT * FROM User Where (LastName, Email) = (?, ?)");
            userExists.setString(1, booking.getOwner().getLastName());
            userExists.setString(2, booking.getOwner().getEmail());
            ResultSet userFound = userExists.executeQuery();
            //If the user is not already in the DB, we create it.
            if(!userFound.next()){
                System.out.println("User not found... Creating new user in DB..");
                createUser(booking.getOwner());
            }
            //Creates a new booking in the DB
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Booking('Arrival', 'Departure', 'nrPerson', 'OwnerLastName', 'OwnerEmail') VALUES(?, ?, ?, ?, ?)");
            stmt.setString(1, booking.getCheckIn().toString());
            stmt.setString(2, booking.getCheckOut().toString());
            stmt.setInt(3, booking.getPersons());
            stmt.setString(4, booking.getOwner().getLastName());
            stmt.setString(5, booking.getOwner().getEmail());
            stmt.executeUpdate();
            ResultSet key = stmt.getGeneratedKeys();
            //Creates the in between rows that connects rooms to bookings
            for(HotelRoom room: booking.getRooms()){
                PreparedStatement rmStmt = connection.prepareStatement("INSERT INTO Booking_Room VALUES(?, ?, ?, ?)");
                rmStmt.setInt(1, key.getInt(1));
                rmStmt.setString(2, booking.getHotel().getName());
                rmStmt.setString(3, booking.getHotel().getAddress());
                rmStmt.setInt(4, room.getRoomNumber());
            }

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    /**
     * Deletes a booking from the DB
     * @param booking - java class of a booking
     */
    public static void deleteBooking(Booking booking){
        if(booking.getBookingID() == -1){
            System.out.println("Invalid booking id.. Nothing deleted");
            return;
        }
        if(connection == null) Connect();
        try {
            //Deletes  from booking table
            PreparedStatement deleteBook = connection.prepareStatement("DELETE FROM Booking WHERE BookingID = ?");
            deleteBook.setInt(1, booking.getBookingID());
            deleteBook.executeUpdate();

            //Deletes from table joining bookings and rooms (room is no longer part of that booking)
            PreparedStatement deleteBookRoom = connection.prepareStatement("DELETE FROM Booking_Room WHERE BookingID = ?");
            deleteBookRoom.setInt(1, booking.getBookingID());
            deleteBookRoom.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    /**
     * Creates a user in the DB
     * Note:    only supposed to be called by createBooking if the user is not already
     *          in the system, this will create that user.
     * @param user - java class of the USER that will be parsed into the DB
     */
    private static void createUser(User user){
        if(connection == null) Connect();
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO User VALUES(?, ?, ?)");
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}