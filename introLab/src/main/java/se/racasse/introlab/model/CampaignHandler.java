package se.racasse.introlab.model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import se.racasse.introlab.config.AppConfig;
import se.racasse.introlab.integration.CampaignDAO;
import se.racasse.introlab.integration.CustomerDAO;

/**
 * @author SimonePersson
 * Handles communication with DAOs. 
 */
public class CampaignHandler {
	
	private final static Logger LOGGER = Logger.getLogger(CampaignHandler.class.getPackage().getName());
	
	private OutputHandler outputHandler;
	private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	private CampaignDAO campaignDAO = context.getBean(CampaignDAO.class);
	private CustomerDAO customerDAO = context.getBean(CustomerDAO.class);
	
	/**
	 * Closes connection with database.
	 */
	public void quit() {
		context.close();
	}
	
	/**
	 * Fetches all campaigns from the database along with their respective customers and displays them. The result is displayed through the interface OutputHandler which is 
	 * implemented in the view layer so that it will be updated directly. 
	 */
	public void getAllCampaigns( ) {
		
		for (Campaign campaign : campaignDAO.getAllCampaigns()) {
			outputHandler.handleMsg(campaign.toString());
			
			for(Customer customer : customerDAO.getAllCustomersFromCampaign(campaign.getId())) {
				outputHandler.handleMsg(customer.toString());
			}
			
		}
		
	}
	
	/**
	 * Fetches all customers from campaign as list with Customer-objects.
	 * 
	 * @param campaign
	 * @return List<Customer> 
	 */
	public List<Customer> getAllCustomersFromCampaign(Campaign campaign) {
		return customerDAO.getAllCustomersFromCampaign(campaign.getId());
	}
	
	/**
	 * Fetches campaign with specified id.
	 * 
	 * @param id
	 * @return campaignById
	 */
	public Campaign getCampaignById(int id){
		Campaign campaignById = campaignDAO.getCampaignById(id);
		//outputHandler.handleMsg(campaignById.toString());
		return campaignById;
	}
	
	/**
	 * Adds new campaign to database. Converts campaign date into sql.Date-format and creates current import date.
	 * All parameters are allowed to be null except campaignCode. 
	 * 
	 * @param campaignName
	 * @param campaignCode
	 * @param campaignDate
	 * @throws IllegalArgumentException Thrown if campaignCode is null. 
	 * @throws ParseException Thrown if date conversion fails.
	 */
	public void addNewCampaign(String campaignName, String campaignCode, String campaignDate) throws IllegalArgumentException, ParseException{
		
		if(checkIfNull(campaignCode)) {
			throw new IllegalArgumentException();
		}
		
		java.sql.Date sqlCampaignDate;
		
		if(!checkIfNull(campaignDate)) {
			sqlCampaignDate = stringToDate(campaignDate);
		}else {
			sqlCampaignDate = null;
		}
		
		java.sql.Date importDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		
		campaignDAO.createCampaign(new Campaign(campaignName, campaignCode, sqlCampaignDate, importDate));
		
	}
	
	/**
	 * Adds new customer to database. Converts parameters into correct format. All parameters can be null except campaignId 
	 * which will result in a NumberFormatException. 
	 * 
	 * @param campaignId
	 * @param firstName
	 * @param lastName
	 * @param gender
	 * @param birthyear
	 * @param address
	 * @param zipCode
	 * @param town
	 */
	public void addNewCustomer(String campaignId, String firstName, String lastName, String gender, String birthyear, String address, String zipCode, String town) throws NumberFormatException {
		
		int cId;
		try {
			cId = Integer.valueOf(campaignId);
		} catch (NumberFormatException e) {
			LOGGER.error("Failed to convert campaign id.", e);
			throw new NumberFormatException();
		}
		
		char checkedGender;
		Integer checkedBirthyear;
		Integer checkedZipCode; 
	
		if(checkIfNull(gender)) {
			checkedGender = '\u0000';
		}else {
			checkedGender = gender.charAt(0);
		}
		
		if(!checkIfNull(birthyear)) {
			checkedBirthyear = Integer.valueOf(birthyear);
		}else {
			checkedBirthyear = null;
		}
		
		if(!checkIfNull(zipCode)) {
			checkedZipCode = Integer.valueOf(zipCode);
		}else {
			checkedZipCode = null;
		}
		
		customerDAO.createCustomer(new Customer(cId, firstName, lastName, checkedGender, checkedBirthyear, address, checkedZipCode, town));	
	}

	/**
	 * Deletes all campaigns older than parameter date. Also deletes all customers belonging to said campaigns. 
	 * 
	 * @param date
	 * @throws ParseException
	 */
	public void deleteCampaignOlderThanDate(String date) throws ParseException {
		int numberOfDeletedCampaigns = 0;
		java.sql.Date sqlDate = stringToDate(date);
		
		for (Campaign campaign : campaignDAO.getAllCampaigns()) {
			if (campaign.getCampaignDate().compareTo(sqlDate) <= 0) {
				deleteCampaignAndCustomers(campaign);
				numberOfDeletedCampaigns++;
			}
		}
		outputHandler.handleMsg(numberOfDeletedCampaigns + " campaigns were deleted successfully!");
	}
	
	/**
	 * Fetches customer information from campaign with parameter id.
	 * 
	 * @param id
	 */
	public void  customerDataById(int id) {
		for(Customer customer : customerDAO.getAllCustomersFromCampaign(id)) {
			outputHandler.handleMsg(customer.toString());
		}
	}
	
	private void deleteCampaignAndCustomers(Campaign campaign) {
		int id = campaign.getId();

	    customerDAO.deleteCustomerById(id);
		campaignDAO.deleteCampaignById(id);
		
	}
	
	
	private Date stringToDate(String convertDate) throws ParseException {
		SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date uDate = sDate.parse(convertDate);
		java.sql.Date sqlDate = new java.sql.Date(uDate.getTime()); 
		return sqlDate;
	}
	
	private boolean checkIfNull(String input) {
		if(input == null || input.equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * Sets OutputHandler.
	 * 
	 * @param outputHandler
	 */
	public void setOutputHandler(OutputHandler outputHandler) {
		this.outputHandler = outputHandler;
	}
	

}
