package daytrip.dal;

import daytrip.model.Customer;
import daytrip.repository.CustomerInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Layer for managing customer-related operations in the database.
 */
public class CustomerDAL implements CustomerInterface {

    /**
     * Constructor. tries loading SQLite JDBC driver
     */
    public CustomerDAL() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Establishes a connection to the database.
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
     * Updates the details of an existing customer in the database.
     *
     * @param customerId The ID of the customer to update.
     * @param updatedCustomer A Customer object containing the updated details.
     * @return true if the update was successful, false otherwise.
     */
    @Override
    public boolean updateCustomer(Integer customerId, Customer updatedCustomer) {
        String sql = "UPDATE Customers SET Name = ?, Email = ? WHERE CustomerID = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, updatedCustomer.getName());
            pstmt.setString(2, updatedCustomer.getEmail());
            pstmt.setInt(4, customerId);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves the details of a specific customer from the database.
     *
     * @param customerId The ID of the customer whose details are to be retrieved.
     * @return A Customer object containing the details of the specified customer,
     * or null if the customer is not found.
     */
    @Override
    public Customer getCustomerDetails(Integer customerId) {
        String sql = "SELECT * FROM Customers WHERE CustomerID = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        null
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves a list of all customers from the database.
     *
     * @return A list of all customers. The list may be empty if there are no customers.
     */
    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customers";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        null

                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customers;
    }

    public boolean addNewCustomer(Customer customer) {
        String sql = "INSERT INTO Customers (Name, Email) VALUES (?, ?)"; // Adjust SQL according to your schema
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getEmail());
            // Add more parameters as needed
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    @Override
    public Customer getCustomerByEmail(String email) {
        String sql = "SELECT * FROM Customers WHERE Email = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        null // Replace null with actual payment info retrieval
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
