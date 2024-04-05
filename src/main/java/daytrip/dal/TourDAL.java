package daytrip.dal;

import daytrip.model.Tour;
import daytrip.repository.TourInterface;

import java.sql.*;
import java.util.Optional;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

/**
 * Data Access Layer for managing tour-related operations in the database.
 */
public class TourDAL implements TourInterface {

    /**
     * Constructor. tries loading SQLite JDBC driver
     */
    public TourDAL() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Establishes a connection to the SQLite database.
     *
     * @return A Connection object to the database.
     */
    private Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:daytrip_database.db";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Fetches tour details for a given tour ID.
     * @param tourID The unique identifier for the tour.
     * @return An Optional containing the Tour if found, or an empty Optional otherwise.
     */
    public Tour getTourDetails(Integer tourID) {
        String sql = "SELECT * FROM Tours WHERE TourID = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, tourID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Tour tour = new Tour(
                        rs.getInt("TourID"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getInt("Duration"),
                        rs.getDouble("Price"),
                        rs.getInt("MaxParticipants"),
                        LocalDate.parse(rs.getString("Date"))
                );
                return tour;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    /**
     * Retrieves a list of all tours that have available seats and are scheduled for future dates.
     *
     * @return A list of available tours.
     */
    @Override
    public List<Tour> getAvailableTours() {
        List<Tour> availableTours = new ArrayList<>();
        String sql = "SELECT * FROM Tours WHERE AvailableSeats > 0 AND Date >= date('now')";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Tour tour = new Tour(
                        rs.getInt("TourID"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getInt("Duration"),
                        rs.getDouble("Price"),
                        rs.getInt("AvailableSeats"),
                        LocalDate.parse(rs.getString("Date"))
                );
                availableTours.add(tour);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return availableTours;
    }


    /**
     * Checks if there are enough available seats for a tour on a specific date.
     * @param tourID The ID of the tour.
     * @param date The date of the tour.
     * @param participants The number of participants.
     * @return True if there are enough seats, false otherwise.
     */
    @Override
    public boolean checkAvailability(Integer tourID, LocalDate date, int participants) {
        String sql = "SELECT AvailableSeats FROM Tours WHERE TourID = ? AND Date = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, tourID);
            pstmt.setString(2, date.toString());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int availableSeats = rs.getInt("AvailableSeats");
                return participants <= availableSeats;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Attempts to book a specified number of seats for a tour on a given date.
     * @param tourID The ID of the tour.
     * @param date The date of the tour.
     * @param participants The number of participants to book for.
     * @return True if the booking is successful, false otherwise.
     */
    @Override
    public boolean bookTour(Integer tourID, LocalDate date, int participants, String customerName, String customerEmail, Optional<String> customerPhoneNumber) {
        String getAvailableSeatsSql = "SELECT AvailableSeats FROM Tours WHERE TourID = ? AND Date = ?";
        String bookTourSql = "UPDATE Tours SET AvailableSeats = AvailableSeats - ? WHERE TourID = ? AND Date = ?";
        Connection conn = null;

        try {
            conn = this.connect(); // Initialize connection
            conn.setAutoCommit(false); // Start transaction

            try (PreparedStatement pstmtSelect = conn.prepareStatement(getAvailableSeatsSql);
                 PreparedStatement pstmtUpdate = conn.prepareStatement(bookTourSql)) {

                // Check seats available
                pstmtSelect.setInt(1, tourID);
                pstmtSelect.setString(2, date.toString());
                ResultSet rs = pstmtSelect.executeQuery();

                if (rs.next()) {
                    int availableSeats = rs.getInt("AvailableSeats");
                    if (participants > availableSeats) {
                        return false; // No seats
                    }
                } else {
                    return false; // Tour not found
                }

                // Book the tour
                pstmtUpdate.setInt(1, participants);
                pstmtUpdate.setInt(2, tourID);
                pstmtUpdate.setString(3, date.toString());
                pstmtUpdate.executeUpdate();

                conn.commit(); // do transaction
                return true;
            } catch (SQLException e) {
                conn.rollback(); // Rollback exception
                System.out.println(e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * Tests the connection to the SQLite database.
     */
    public void testConnection() {
        try (Connection conn = this.connect()) {
            if (conn != null) {
                System.out.println("Connection to SQLite database is successful.");
            } else {
                System.out.println("Failed to make connection to SQLite database.");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        TourDAL tourDal = new TourDAL();
        tourDal.testConnection();
    }


}
