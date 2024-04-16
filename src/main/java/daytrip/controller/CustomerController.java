package daytrip.controller;

import daytrip.model.Customer;
import daytrip.repository.CustomerInterface;
import daytrip.repository.ReservationInterface;
import model.PaymentInfo;

import java.util.List;

/**
 * Controller for managing customer-related operations.
 */
public class CustomerController {
    private CustomerInterface customerDAL;

    /**
     * Constructs a CustomerController with specified customer data access layer.
     * Note: The ReservationInterface parameter in the constructor is not used.
     *
     * @param customerDAL The data access layer for customer-related operations.
     * @param reservationDAL The data access layer for reservation-related operations, currently not used.
     */
    public CustomerController(CustomerInterface customerDAL, ReservationInterface reservationDAL) {
        this.customerDAL = customerDAL;
    }

    /**
     * Updates the details of a customer.
     *
     * @param customerId The ID of the customer to update.
     * @param newName The new name of the customer.
     * @param newEmail The new email of the customer.
     * @return true if the update was successful, false otherwise.
     */
    public boolean updateCustomerDetails(Integer customerId, String newName, String newEmail) {
        Customer customer = customerDAL.getCustomerDetails(customerId);
        if (customer != null) {
            customer.setName(newName);
            customer.setEmail(newEmail);
            return customerDAL.updateCustomer(customerId, customer);
        }
        return false;
    }

    /**
     * Retrieves a list of all customers.
     *
     * @return A list containing all customers. The list may be empty if there are no customers.
     */
    public List<Customer> getAllCustomers(){
        return customerDAL.getAllCustomers();
    }

    /**
     * Adds a new customer to the database.
     *
     * @param name The name of the new customer.
     * @param email The email address of the new customer.
     * @param paymentInfo The payment information of the new customer.
     * @return true if the customer was successfully added, false otherwise.
     */
    public boolean addNewCustomer(String name, String email, PaymentInfo paymentInfo) {
        Customer newCustomer = new Customer(null, name, email, paymentInfo);
        return customerDAL.addNewCustomer(newCustomer);
    }

    public Customer getCustomerByEmail(String email) {
        return customerDAL.getCustomerByEmail(email);
    }

}
