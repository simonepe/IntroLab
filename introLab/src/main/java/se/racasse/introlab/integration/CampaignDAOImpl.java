package se.racasse.introlab.integration;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import se.racasse.introlab.model.Campaign;
import se.racasse.introlab.model.CampaignMapper;

/**
 * @author SimonePersson
 * Implementation of Data Access Object class for a campaign.
 */
@Component
public class CampaignDAOImpl implements CampaignDAO {
	
	private final static Logger LOGGER = Logger.getLogger(CampaignDAOImpl.class.getPackage().getName());
	
	private JdbcTemplate jdbcTemplate;
	
	private final String SQL_FIND_CAMPAIGN = "select * from campaigns where id = ?";
	private final String SQL_GET_ALL_CAMPAIGNS = "select * from campaigns";
	private final String SQL_INSERT_CAMPAIGN = "insert into campaigns(campaignname, campaigncode, campaigndate, importdate) values(?,?,?,?)";
	private final String SQL_DELETE_CAMPAIGN = "delete from campaigns where id = ?";
	
	/**
	 * Creating an instance of jdbc template.
	 * 
	 * @param dataSource
	 */
	@Autowired
	public CampaignDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * Fetches campaign with parameter id. 
	 * 
	 * @param id of campaign
	 * @return campaign 
	 */
	public Campaign getCampaignById(int id) {
		try {
			return jdbcTemplate.queryForObject(SQL_FIND_CAMPAIGN, new Object[] { id }, new CampaignMapper());
		} catch (DataAccessException e) {
			LOGGER.error("Could not retrieve campaign with id: " + id + " from database.", e);
			return null;
		}
	}
	/**
	 * Fetches all campaigns from database.
	 * 
	 * @return campaigns in the form of a list. 
	 */
	public List<Campaign> getAllCampaigns() {
		
		try {
			return jdbcTemplate.query(SQL_GET_ALL_CAMPAIGNS, new CampaignMapper());
		} catch (DataAccessException e) {
			LOGGER.error("Failed to retrieve all campaigns from database.", e);
			return null;
		}
	}

	/**
	 * Inserts a campaign into the database.
	 * 
	 * @param campaign
	 * @return true if campaign was inserted into database successfully. Otherwise false. 
	 */
	public boolean createCampaign(Campaign campaign) {
		try {
			return jdbcTemplate.update(SQL_INSERT_CAMPAIGN, campaign.getCampaignName(), campaign.getCampaignCode(), 
					campaign.getCampaignDate(), campaign.getImportDate()) > 0;
		} catch (DataAccessException e) {
			LOGGER.error("Failed to insert campaign: " + campaign.toString() + " into database.", e);
			return false;
		}
	}

	/**
	 * Deletes campaign with parameter id.
	 * 
	 * @param id of campaign
	 * @return true if campaign was deleted. Otherwise false. 
	 */
	public boolean deleteCampaignById(int id) {
		try {
			return jdbcTemplate.update(SQL_DELETE_CAMPAIGN, new Object[] { id }) == 1;
		} catch (DataAccessException e) {
			LOGGER.error("Failed to delete campaign with id: " + id + " from database.", e);
			return false;
		}
	}
	
	

}
