package se.racasse.introlab.model;

import java.sql.Date;
import java.util.List;

/**
 * @author SimonePersson
 * 
 * Class representing a campaign.
 *
 */
public class Campaign {
	private int id;
	private String campaignName;
	private String campaignCode;
	private Date campaignDate;
	private Date importDate;
	private List<Customer> customers;
	
	/**
	 * Empty constructor for campaign.
	 */
	public Campaign(){
	}
	
	/**
	 * Creates a campaign with campaign name, campaign code, campaign date, and import date.
	 * 
	 * @param campaignName
	 * @param campaignCode
	 * @param campaignDate
	 * @param importDate
	 */
	public Campaign(String campaignName, String campaignCode, Date campaignDate, Date importDate) {
		
		this.campaignName = campaignName;
		this.campaignCode = campaignCode;
		this.campaignDate = campaignDate;
		this.importDate = importDate;
	}
	
	/**
	 * Fetches id of campaign.
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets id of campaign.
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Fetches campaign name.
	 * 
	 * @return campaignName
	 */
	public String getCampaignName() {
		return campaignName;
	}

	/**
	 * Sets campaign name.
	 * 
	 * @param firstName
	 */
	public void setCampaignName(String firstName) {
		this.campaignName = firstName;
	}

	/**
	 * Fetches campaign code.
	 * 
	 * @return campaignCode
	 */
	public String getCampaignCode() {
		return campaignCode;
	}

	/**
	 * Sets campaign code.
	 * 
	 * @param campaignCode
	 */
	public void setCampaignCode(String campaignCode) {
		this.campaignCode = campaignCode;
	}

	/**
	 * Fetches campaign date.
	 * 
	 * @return campaignDate
	 */
	public Date getCampaignDate() {
		return campaignDate;
	}

	/**
	 * Sets campaign date.
	 * 
	 * @param campaignDate
	 */
	public void setCampaignDate(Date campaignDate) {
		this.campaignDate = campaignDate;
	}

	/**
	 * Fetches import date.
	 * 
	 * @return importDate
	 */
	public Date getImportDate() {
		return importDate;
	}

	/**
	 * Sets import date. 
	 * 
	 * @param importDate
	 */
	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}
	
	/**
	 * Sets list of customers belonging to campaign. 
	 * 
	 * @param customers
	 */
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	/**
	 * Fetches list of customers belonging to campaign.
	 * 
	 * @return customers
	 */
	public List<Customer> getCustomers() {
		return customers;
	}
	
	/**
	 * Formats campaign-object as a String.
	 */
	@Override
	public String toString() {
		return "Campaign{" + " Id=" + id + ", Name=" + campaignName
				+ ", Code=" + campaignCode + ", Date=" + campaignDate
				+ ", Imported at=" + importDate
				+ '\'' + '}';
	}
}
