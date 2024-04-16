package daytrip.repository;

import daytrip.model.Customer;

import java.util.List;


/**
 * Interface defining the contract for operations on customers within the system.
 * Provides methods for retrieving, updating, and managing customer information.
 */
public interface CustomerInterface {

    /**
     * Retrieves a list of all customers in the system.
     *
     * @return a list containing all customers; or an empty list if no customers exist.
     */
    List<Customer> getAllCustomers();

    /**
     * Updates the information of a specific customer.
     *
     * @param customerId The unique identifier of the customer to be updated.
     * @param updatedCustomer An instance of {@link Customer} containing the updated information.
     * @return true if the customer was successfully updated; false otherwise.
     */
    boolean updateCustomer(Integer customerId, Customer updatedCustomer);

    /**
     * Retrieves the details of a specific customer.
     *
     * @param customerId The unique identifier of the customer whose details are to be retrieved.
     * @return an instance of {@link Customer} representing the customer's details; or null if no such customer exists.
     */
    Customer getCustomerDetails(Integer customerId);

    /**
     * Adds a new customer to the system.
     *
     * @param newCustomer An instance of {@link Customer} containing the information for the new customer.
     * @return The unique identifier of the newly added customer; or null if the customer could not be added.
     */
    boolean addNewCustomer(Customer newCustomer);

    Customer getCustomerByEmail(String email);

}
