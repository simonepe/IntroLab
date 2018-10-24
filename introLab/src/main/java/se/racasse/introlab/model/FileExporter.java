package se.racasse.introlab.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

/**
 * @author SimonePersson
 * This class handles exporting of a file.
 *
 */
public class FileExporter {
	
	/**
	 * Exports a file with windows-latin-1 encoding (ISO-8859-1) containing all customers for a specified campaign.
	 * 
	 * @param id Id of the campaign. 
	 * @param campaignHandler Instance of class CampaignHandler. 
	 * @param path Path to where the file will be exported to.
	 * @throws IOException
	 * @throws FileAlreadyExistsException
	 */
	public void exportCustomersByCampaignId(int id, CampaignHandler campaignHandler, String path) throws IOException, FileAlreadyExistsException {
		
		Campaign campaign = campaignHandler.getCampaignById(id);
		
		String completeFilePath = path + "\\" + campaign.getCampaignCode() + "_" + campaign.getCampaignDate() + ".txt";
		
		File f = new File(completeFilePath);
		if(f.exists()) { 
		    throw new FileAlreadyExistsException(completeFilePath);
		}
		
		Writer writer = null;
		
		FileOutputStream ops = new FileOutputStream(completeFilePath);
		List<Customer> customersInCampaign = campaignHandler.getAllCustomersFromCampaign(campaign);

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          ops, "8859_1"));
		    
		    for(Customer customer : customersInCampaign) {
				writer.write("\r\n" + customer.getLastName() + "," + customer.getFirstName() + "; \t" 
				+ customer.getAddress() + " " + customer.getZipCode() + " " + customer.getTown());
			}
		    
		} catch (IOException ex) {
			throw ex;
	
		} finally {
		   try {
			   writer.close();
			   }catch (Exception ex) {
				   throw ex;
				   }
		}
	}

}
