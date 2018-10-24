package se.racasse.introlab.integration;

import java.util.List;

import se.racasse.introlab.model.Customer;

/**
 * @author SimonePersson
 * Data Access Object class for a customer.
 *
 */
public interface CustomerDAO {
	
	List<Customer> getAllCustomers();
	
	List<Customer> getAllCustomersFromCampaign(int id);
	
	boolean createCustomer(Customer customer);
	
	boolean deleteCustomerById(int id);

}
