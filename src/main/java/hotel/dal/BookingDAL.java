package hotel.dal;

import hotel.model.Booking;
import hotel.model.HotelRoom;
import model.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BookingDAL extends BaseDAL{

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


    /**
     * Gets a list of bookings the user currently has booked
     * @param user - java class of a user
     * @return A list of bookings that user has
     */
    public List<Booking> getBookings(User user){
        List<Booking> bookings = new ArrayList<>();
        connect();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Booking WHERE OwnerName=? AND OwnerEmail=?");
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            ResultSet results = stmt.executeQuery();
            while(results.next()){
                PreparedStatement rmstmt = connection.prepareStatement(
                        "SELECT * FROM Room WHERE (HotelName, HotelAddress, Number) IN(" +
                                "SELECT HotelName, HotelAddress, RoomNumber FROM Booking_Room WHERE BookingID=?)");
                rmstmt.setInt(1,results.getInt(1));
                ResultSet rmResults = rmstmt.executeQuery();
                List<HotelRoom> rooms = new ArrayList<>();
                String hotelName = rmResults.getString(1);
                String hotelAddress = rmResults.getString(2);
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
                String departure =  results.getString("Departure");
                Booking newBooking = new Booking(
                        results.getInt(1),
                        LocalDate.parse(arrival, formatter),
                        LocalDate.parse(departure, formatter),
                        results.getInt(4),
                        rooms,
                        getHotel(hotelName, hotelAddress),
                        user
                );
                bookings.add(newBooking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return bookings;
    }

    /**
     * Creates a new booking in the DB
     * @param booking - java class of a booking
     */
    public void createBooking(Booking booking){
        connect();
        try {
            PreparedStatement userExists = connection.prepareStatement("SELECT * FROM User Where (Name, Email) = (?, ?)");
            userExists.setString(1, booking.getOwner().getName());
            userExists.setString(2, booking.getOwner().getEmail());
            ResultSet userFound = userExists.executeQuery();
            //If the user is not already in the DB, we create it.
            if(!userFound.next()){
                System.out.println("User not found... Creating new user in DB..");
                createUser(booking.getOwner());
            }
            //Creates a new booking in the DB
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Booking('Arrival', 'Departure', 'nrPerson', 'OwnerName', 'OwnerEmail') VALUES(?, ?, ?, ?, ?)");
            stmt.setString(1, booking.getCheckIn().toString());
            stmt.setString(2, booking.getCheckOut().toString());
            stmt.setInt(3, booking.getPersons());
            stmt.setString(4, booking.getOwner().getName());
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
                rmStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
    }

    /**
     * Deletes a booking from the DB
     * @param booking - java class of a booking
     */
    public void deleteBooking(Booking booking){
        connect();
        // If it's a booking that's not from the database we ignore it
        if(booking.getBookingID() == -1){
            System.out.println("Invalid booking id.. Nothing deleted");
            return;
        }
        connect();
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
            e.printStackTrace();
        }
        close();
    }

    /**
     * Creates a user in the DB
     * Note:    Helper function called by createBooking if the user is not already
     *          in the system, this will create that user in the system to match bookings.
     * @param user - java class of the USER that will be created into the DB
     */
    private void createUser(User user){
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO User VALUES(?, ?)");
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}