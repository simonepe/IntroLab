package se.racasse.introlab.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @author SimonePersson
 * This class is used for mapping result of query to the database into an instance of a Campaign-object.
 */
public class CampaignMapper implements RowMapper<Campaign> {

	/**
	 * Creates an instance of Campaign from the result from sql-query. 
	 * 
	 * @param resultSet Result of sql-query.
	 * @param i 
	 * @return customer An instance of Campaign created with the results from the sql-query. 
	 * @throws SQLException
	 */
	public Campaign mapRow(ResultSet resultSet, int i) throws SQLException {
		
		Campaign campaign = new Campaign();
		campaign.setId(resultSet.getInt("id"));
		campaign.setCampaignName(resultSet.getString("campaignname"));
		campaign.setCampaignCode(resultSet.getString("campaigncode"));
		campaign.setCampaignDate(resultSet.getDate("campaigndate"));
		campaign.setImportDate(resultSet.getDate("importdate"));

		return campaign;
	}

}
