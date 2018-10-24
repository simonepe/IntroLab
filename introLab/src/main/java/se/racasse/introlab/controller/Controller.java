package se.racasse.introlab.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.sql.Date;
import java.text.ParseException;

import se.racasse.introlab.model.CampaignHandler;
import se.racasse.introlab.model.FileExporter;
import se.racasse.introlab.model.FileImporter;
import se.racasse.introlab.model.OutputHandler;

/**
 * @author SimonePersson
 * The only controller of the application. All calls to the model pass through here.
 */
public class Controller {
	
	private static CampaignHandler campaignHandler = new CampaignHandler();
	private static FileImporter fileImporter = new FileImporter();
	private static FileExporter fileExporter = new FileExporter();
	
	/**
	 * Initializes console output.
	 * 
	 * @param output
	 */
	public void initConsoleOutput(OutputHandler output) {
		campaignHandler.setOutputHandler(output);
	}
	
	/**
	 * Closes down the application.
	 */
	public void quit() {
		campaignHandler.quit();
	}
	
	/**
	 * Fetches all campaigns in the database.
	 */
	public void getAllCampaigns() {
		campaignHandler.getAllCampaigns();
	}
	
	/**
	 * Fetches campaign with parameter id.
	 * 
	 * @param id of the campaign to be fetched.
	 */
	public void getCampaignById(int id){
		campaignHandler.getCampaignById(id);
	}
	
	/**
	 * Adds new campaign to the database.
	 * 
	 * @param campaignName
	 * @param campaignCode
	 * @param campaignDate
	 * @throws ParseException
	 */
	public void addNewCampaign(String campaignName, String campaignCode, String campaignDate) throws ParseException {
		campaignHandler.addNewCampaign(campaignName, campaignCode, campaignDate);
	}
	
	/**
	 * Imports file to database.
	 * 
	 * @param path to file to be imported
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void importFile(String path) throws FileNotFoundException, IOException, ParseException {
		fileImporter.importFile(path, campaignHandler);
    }
	
	/**
	 * Exports file containing information about customers belonging to campaign with parameter id. 
	 * 
	 * @param id of campaign
	 * @param path to where the file should be exported
	 * @throws FileAlreadyExistsException
	 * @throws IOException
	 */
	public void exportCustomersByCampaignId(int id, String path) throws FileAlreadyExistsException, IOException {
		fileExporter.exportCustomersByCampaignId(id, campaignHandler,  path);
	}
	
	/**
	 * Fetches customer objects belonging to campaign with parameter id. 
	 * 
	 * @param id of campaign
	 */
	public void customerDataById(int id) {
		campaignHandler.customerDataById(id);
	}
	
	/**
	 * Deletes all campaigns and their respective customers with a campaign date older than parameter date. 
	 * 
	 * @param date
	 * @throws ParseException
	 */
	public void deleteCampaignOlderThanDate (String date) throws ParseException {
		campaignHandler.deleteCampaignOlderThanDate(date);
	}

}
