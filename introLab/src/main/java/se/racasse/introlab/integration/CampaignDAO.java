package se.racasse.introlab.integration;

import java.util.List;

import se.racasse.introlab.model.Campaign;

/**
 * @author SimonePersson
 * Data Access Object class for a campaign.
 *
 */
public interface CampaignDAO {
	Campaign getCampaignById(int id);
	
	boolean deleteCampaignById(int id);
	
	List<Campaign> getAllCampaigns();
	
	boolean createCampaign(Campaign campaign);

}
