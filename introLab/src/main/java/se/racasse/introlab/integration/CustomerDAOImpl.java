package se.racasse.introlab.integration;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import se.racasse.introlab.model.Customer;
import se.racasse.introlab.model.CustomerMapper;

/**
 * @author SimonePersson
 * Implementation of Data Access Object class for a customer.
 *
 */
@Component
public class CustomerDAOImpl implements CustomerDAO{
	
	private final static Logger LOGGER = Logger.getLogger(CustomerDAOImpl.class.getPackage().getName());
	
	private JdbcTemplate jdbcTemplate;
	
	private final String SQL_GET_ALL_CUSTOMERS = "select * from customers";
	private final String SQL_GET_ALL_CUSTOMERS_FROM_CAMPAIGN = "select * from customers where campaignid = ?";
	private final String SQL_INSERT_CUSTOMER = "insert into customers(campaignid, firstname, lastname, gender, birthyear, address, zipcode, town) values(?,?,?,?,?,?,?,?)";
	private final String SQL_DELETE_CUSTOMER = "delete from customers where campaignid = ?";
	
	/**
	 * Creating an instance of jdbc template.
	 * 
	 * @param dataSource
	 */
	@Autowired
	public CustomerDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * Fetches all customers from database.
	 * 
	 * @return customers in the form of a list. 
	 */
	public List<Customer> getAllCustomers() {
		try {
			return jdbcTemplate.query(SQL_GET_ALL_CUSTOMERS, new CustomerMapper());
		} catch (DataAccessException e) {
			LOGGER.error("Failed to retrieve all customers from database.", e);
			return null;
		}
	
	}
	

	/**
	 * Inserts a customer into the database.
	 * 
	 * @param customer
	 * @return true if customer was inserted into database successfully. Otherwise false. 
	 */
	public boolean createCustomer(Customer customer) {
		try {
			return jdbcTemplate.update(SQL_INSERT_CUSTOMER, customer.getCampaignId(), customer.getFirstName(), customer.getLastName(),
					Character.toString(customer.getGender()), customer.getBirthyear(), customer.getAddress(), customer.getZipCode(), customer.getTown()) > 0;
		} catch (DataAccessException e) {
			LOGGER.error("Failed to insert customer: " + customer.toString() + "into database.", e);
			return false;
		}
	}

	/**
	 * Fetches customers belonging to campaign with parameter id. 
	 * 
	 * @param id of campaign
	 * @return customers belonging to campaign in form of a list.
	 */
	public List<Customer> getAllCustomersFromCampaign(int id) {
		
		try {
			return jdbcTemplate.query(SQL_GET_ALL_CUSTOMERS_FROM_CAMPAIGN, new Object[] { id }, new CustomerMapper());
		} catch (DataAccessException e) {
			LOGGER.error("Failed to retrieve campaign with id: " + id + "from database.", e);
			return null;
		}
	}

	/**
	 * Deletes customer with parameter id.
	 * 
	 * @param id of campaign
	 * @return true if customer was deleted. Otherwise false. 
	 */
	public boolean deleteCustomerById(int id) {
		try {
			return jdbcTemplate.update(SQL_DELETE_CUSTOMER, new Object[] { id }) > 0;
		} catch (DataAccessException e) {
			LOGGER.error("Failed to delete customer with id: " + id + "from database.", e);
			return false;
		}
	}
	

}
