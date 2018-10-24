package se.racasse.introlab.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @author SimonePersson
 * This class is used for mapping result of query to the database into an instance of a Customer object.
 */
public class CustomerMapper implements RowMapper<Customer> { 
	
	/**
	 * Creates an instance of Customer from the result from sql-query. 
	 * 
	 * @param resultSet Result of sql-query.
	 * @param i 
	 * @return customer An instance of Customer created with the results from the sql-query. 
	 * @throws SQLException
	 */
	public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
		
		Customer customer = new Customer();
		
		customer.setId(resultSet.getInt("id"));
		customer.setCampaignId(resultSet.getInt("campaignid"));
		customer.setFirstName(resultSet.getString("firstname"));
		customer.setLastName(resultSet.getString("lastname"));
		customer.setGender(resultSet.getString("gender").charAt(0));
		customer.setBirthyear(resultSet.getInt("birthyear"));
		customer.setAddress(resultSet.getString("address"));
		customer.setZipCode(resultSet.getInt("zipcode"));
		customer.setTown(resultSet.getString("town"));

		return customer;
	}

}
