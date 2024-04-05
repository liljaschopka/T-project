package daytrip.controller;

import daytrip.model.Customer;
import daytrip.repository.CustomerInterface;
import daytrip.repository.ReservationInterface;

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
     * @param newPhoneNumber The new phone number of the customer.
     * @return true if the update was successful, false otherwise.
     */
    public boolean updateCustomerDetails(Integer customerId, String newName, String newEmail, String newPhoneNumber) {
        Customer customer = customerDAL.getCustomerDetails(customerId);
        if (customer != null) {
            customer.setName(newName);
            customer.setEmail(newEmail);
            customer.setPhoneNumber(newPhoneNumber);
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
}
