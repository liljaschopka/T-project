package daytrip.dal;

import daytrip.model.Reservation;
import daytrip.model.Tour;
import daytrip.repository.ReservationInterface;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Layer for managing reservation-related operations in the database.
 */
public class ReservationDAL implements ReservationInterface {

    /**
     * Constructor. tries loading SQLite JDBC driver
     */
    public ReservationDAL() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC driver not found.");
            e.printStackTrace();
        }
    }

    /**
     * Establishes a connection to the SQLite database.
     *
     * @return A Connection object to the database.
     */
    private Connection connect() {
        // Establish a connection to the SQLite database
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
     * This method is a placeholder and is not implemented because
     * checking availability is handled by the TourDAL.
     *
     * @throws UnsupportedOperationException always.
     */
    @Override
    public boolean checkAvailability(Integer tourID, LocalDate date) {
        throw new UnsupportedOperationException("checkAvailability should be handled by TourDAL.");
    }

    /**
     * This method is a placeholder and is not implemented because
     * retrieving tour details is handled by the TourDAL.
     *
     * @throws UnsupportedOperationException always.
     */
    @Override
    public Tour getTourDetails(Integer tourID) {
        throw new UnsupportedOperationException("getTourDetails should be handled by TourDAL.");
    }

    /**
     * Cancels an existing reservation by its ID and updates the available seats for the associated tour.
     *
     * @param reservationID The ID of the reservation to cancel.
     * @return true if the cancellation was successful, false otherwise.
     */
    @Override
    public boolean cancelReservation(Integer reservationID) {
        String getParticipantsSql = "SELECT TourID, NumberOfParticipants FROM Reservations WHERE ReservationID = ?";
        String updateSeatsSql = "UPDATE Tours SET AvailableSeats = AvailableSeats + ? WHERE TourID = ?";
        String deleteReservationSql = "DELETE FROM Reservations WHERE ReservationID = ?";
        Connection conn = null;

        try {
            conn = this.connect(); // Initialize connection
            conn.setAutoCommit(false); // Start transaction

            // Step 1: Fetch the number of participants for the reservation
            int numberOfParticipants = 0;
            int tourID = 0;
            try (PreparedStatement pstmtSelect = conn.prepareStatement(getParticipantsSql)) {
                pstmtSelect.setInt(1, reservationID);
                ResultSet rs = pstmtSelect.executeQuery();
                if (rs.next()) {
                    numberOfParticipants = rs.getInt("NumberOfParticipants");
                    tourID = rs.getInt("TourID");
                } else {
                    conn.rollback(); // Reservation not found, rollback
                    return false;
                }
            }

            // Step 2: Increase available seats in Tours table
            try (PreparedStatement pstmtUpdate = conn.prepareStatement(updateSeatsSql)) {
                pstmtUpdate.setInt(1, numberOfParticipants);
                pstmtUpdate.setInt(2, tourID);
                int updateCount = pstmtUpdate.executeUpdate();
                if (updateCount == 0) {
                    conn.rollback(); // Failed to update Tours, rollback
                    return false;
                }
            }

            // Step 3: Delete the reservation
            try (PreparedStatement pstmtDelete = conn.prepareStatement(deleteReservationSql)) {
                pstmtDelete.setInt(1, reservationID);
                int affectedRows = pstmtDelete.executeUpdate();
                if (affectedRows == 0) {
                    conn.rollback(); // Failed to delete reservation, rollback
                    return false;
                }
            }

            conn.commit(); // Commit the transaction
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback on error
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close(); // Ensure connection is closed
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }



    /**
     * Updates the details of an existing reservation.
     *
     * @param reservationID The ID of the reservation to update.
     * @param newNumberOfParticipants The new number of participants.
     * @param newDate The new date for the reservation.
     * @return true if the update was successful, false otherwise.
     */
    @Override
    public boolean updateReservation(Integer reservationID, int newNumberOfParticipants, LocalDate newDate) {
        String sql = "UPDATE Reservations SET NumberOfParticipants = ?, DateBooked = ? WHERE ReservationID = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newNumberOfParticipants);
            pstmt.setDate(2, Date.valueOf(newDate));
            pstmt.setInt(3, reservationID);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Retrieves or creates a customer ID based on the provided customer name and email.
     * If a customer with the given email does not exist in the database, a new customer
     * record is created with the provided name and email.
     *
     * @param customerName The name of the customer.
     * @param customerEmail The email address of the customer.
     * @return The customer ID of the existing or newly created customer.
     */
    private Integer getOrCreateCustomerId(String customerName, String customerEmail) {
        Integer customerId = getCustomerIdByEmail(customerEmail);
        if (customerId == null) {
            customerId = insertNewCustomer(customerName, customerEmail);
        }
        return customerId;
    }

    /**
     * Retrieves the customer ID for a given email address.
     * This method queries the database to find a customer with the specified email.
     *
     * @param email The email address of the customer.
     * @return The customer ID if a customer with the specified email exists, null otherwise.
     */
    private Integer getCustomerIdByEmail(String email) {
        String sql = "SELECT CustomerID FROM Customers WHERE Email = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("CustomerID");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null; // Return null if no customer is found
    }

    /**
     * Inserts a new customer into the database with the specified name and email address.
     * This method is called if no existing customer matches the provided email.
     *
     * @param name The name of the new customer.
     * @param email The email address of the new customer.
     * @return The customer ID of the newly inserted customer, or null if the insertion fails.
     */
    private Integer insertNewCustomer(String name, String email) {
        String sql = "INSERT INTO Customers (Name, Email) VALUES (?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1); // Return the newly created customer ID
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Inserting new customer failed: " + e.getMessage());
        }
        return null; // Return null if insertion fails
    }

    /**
     * Creates a new reservation with the specified details.
     *
     * @param tourID The ID of the tour to reserve.
     * @param customerName The name of the customer making the reservation.
     * @param customerEmail The email address of the customer.
     * @param numberOfParticipants The number of participants for the reservation.
     * @param date The date of the reservation.
     * @return true if the reservation was successfully made, false otherwise.
     */
    @Override
    public boolean makeReservation(Integer tourID, String customerName, String customerEmail, int numberOfParticipants, LocalDate date) {
        Integer customerID = getOrCreateCustomerId(customerName, customerEmail);

        String sql = "INSERT INTO Reservations (CustomerID, TourID, NumberOfParticipants, DateBooked) VALUES (?, ?, ?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, customerID);
            pstmt.setInt(2, tourID);
            pstmt.setInt(3, numberOfParticipants);
            pstmt.setDate(4, Date.valueOf(date));
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return true; // Return the newly created reservation ID
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Retrieves the details of a specific reservation by its ID.
     *
     * @param reservationID The ID of the reservation to retrieve.
     * @return A Reservation object containing the details of the specified reservation, or null if not found.
     */
    @Override
    public Reservation getReservationDetails(Integer reservationID) {
        // SQL query to select a reservation by its ID.
        String sql = "SELECT * FROM Reservations WHERE ReservationID = ?";

        // Try-with-resources statement to auto-close resources.
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set the reservation ID parameter.
            pstmt.setInt(1, reservationID);

            // Execute the query and process the result set.
            ResultSet rs = pstmt.executeQuery();

            // If a reservation is found, create and return a Reservation object.
            if (rs.next()) {
                return new Reservation(
                        rs.getInt("ReservationID"),
                        rs.getInt("CustomerID"),
                        rs.getInt("TourID"),
                        rs.getInt("NumberOfParticipants"),
                        rs.getDate("DateBooked").toLocalDate() // Convert SQL Date to LocalDate
                );
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
        // Return null if the reservation is not found or an error occurs.
        return null;
    }

    /**
     * Checks if a specific reservation exists.
     *
     * @param reservationID The ID of the reservation to check.
     * @return true if the reservation exists, false otherwise.
     */
    @Override
    public boolean isReservationExists(Integer reservationID) {
        return getReservationDetails(reservationID) != null;
    }

    /**
     * Retrieves a list of all reservations from the database.
     *
     * @return A list of all reservations.
     */
    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM Reservations";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                reservations.add(new Reservation(
                        rs.getInt("ReservationID"),
                        rs.getInt("CustomerID"),
                        rs.getInt("TourID"),
                        rs.getInt("NumberOfParticipants"),
                        rs.getDate("DateBooked").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return reservations;
    }

    /**
     * Retrieves the number of participants for a specific reservation.
     *
     * @param reservationID The ID of the reservation.
     * @return The number of participants for the specified reservation.
     */
    @Override
    public int getNumberOfParticipants(Integer reservationID) {
        Reservation reservation = getReservationDetails(reservationID);
        return reservation != null ? reservation.getNumberOfParticipants() : 0;
    }
}
