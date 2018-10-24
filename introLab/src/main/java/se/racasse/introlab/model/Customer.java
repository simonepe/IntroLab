package se.racasse.introlab.model;

/**
 * @author SimonePersson
 * A customer belonging to a campaign.
 *
 */
public class Customer {
	private int id;
	private int campaignId;
	private String firstName;
	private String lastName;
	private char gender;
	private Integer birthyear;
	private String address;
	private Integer zipCode;
	private String town; 
	
	/**
	 * Empty constructor for customer.
	 */
	public Customer(){
	}
	
	/**
	 * Creates a customer with id of campaign, first name, last name, gender, birth year, address, zip code, and town. 
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
	public Customer(int campaignId, String firstName, String lastName, char gender, Integer birthyear, String address, Integer zipCode, String town) {
		
		this.campaignId = campaignId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthyear = birthyear;
		this.address = address;
		this.zipCode = zipCode;
		this.town = town; 	
	}
	
	
	/**
	 * Fetches id of customer
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets id of customer
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Fetches id of campaign the customer belongs to. 
	 * 
	 * @return campaignId 
	 */
	public int getCampaignId() {
		return campaignId;
	}

	/**
	 * Sets id of the campaign that the customer belongs to.
	 * 
	 * @param campaignId
	 */
	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}

	/**
	 * Fetches first name of customer.
	 * 
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets first name of customer.
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Fetches last name of customer.
	 * 
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets last name of customer. 
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Fetches gender of customer. 
	 * 
	 * @return gender
	 */
	public char getGender() {
		return gender;
	}

	/**
	 * Sets gender of customer.
	 * 
	 * @param gender
	 */
	public void setGender(char gender) {
		this.gender = gender;
	}

	/**
	 * Fetches birth year of customer.
	 * 
	 * @return birthYear 
	 */
	public Integer getBirthyear() {
		return birthyear;
	}

	/**
	 * Sets birth year of customer.
	 * 
	 * @param birthyear
	 */
	public void setBirthyear(Integer birthyear) {
		this.birthyear = birthyear;
	}

	/**
	 * Fetches address of customer.
	 * 
	 * @return address 
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets address of customer. 
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Fetches zip code of customer.
	 * 
	 * @return zipCode 
	 */
	public Integer getZipCode() {
		return zipCode;
	}

	/**
	 * Sets zip code of customer. 
	 * 
	 * @param zipCode
	 */
	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * Fetches town of customer.
	 * 
	 * @return town
	 */
	public String getTown() {
		return town;
	}

	/**
	 * Sets town of customer.
	 * 
	 * @param town
	 */
	public void setTown(String town) {
		this.town = town;
	}

	/**
	 * Formats customer-object as a String.
	 */
	@Override
	public String toString() {
		return "\t Customer{" + " First Name=" + firstName + ", Last Name=" + lastName 
				+ ", Gender=" + gender + ", Birth Year=" + birthyear + ", Address=" + address
				+ ", Zip Code=" + zipCode + ", Town=" + town
				+ '\'' + '}';
	}	
	

}
