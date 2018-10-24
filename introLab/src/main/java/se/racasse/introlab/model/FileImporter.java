package se.racasse.introlab.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

/**
 * @author SimonePersson
 * This class handles reading of a file.
 *
 */
public class FileImporter {
	
	private final static Logger LOGGER = Logger.getLogger(FileImporter.class.getPackage().getName());
	
	/**
	 * Imports the contents of a file. Progress info containing percentage of lines read is logged every 1000nd line . 
	 * 
	 * @param path The path to the file to import
	 * @param campaignHandler Instance of class CampaignHander
	 * @throws FileNotFoundException 
	 * @throws IOException
	 * @throws ParseException
	 */
	public void importFile(String path, CampaignHandler campaignHandler) throws FileNotFoundException, IOException, ParseException {
		
		 try{
	            BufferedReader buf = new BufferedReader(new FileReader(path));
	            BufferedReader countLines = new BufferedReader(new FileReader(path));

	            ArrayList<String> customerInfo = new ArrayList<String>();
	            String lineJustFetched = null;
	            String[] campaignWithCustomers;
	            String[] campaignInfoWords;
	            String[] customerInfoWords;
	            int lineNumber = 0;
	            int counter = 0;
	          
	            int totalLines = countLines(countLines);

	            while(true){
	            	
	            	if(counter == 1000) {
	            		LOGGER.info("Progress info: " + checkProgressPercentage(lineNumber, totalLines) + "% of lines have been read.");
	            		counter = 0;
	            	}
	                
	                lineJustFetched = buf.readLine();
	                if(lineJustFetched == null){  
	                    break; 
	                }else{
	                	campaignWithCustomers = lineJustFetched.split("\t");
	                	
	                	String campaignInfo = campaignWithCustomers[0];
	                	
	                		campaignInfoWords = campaignInfo.split(";");
	                		
	    	            	String campaignName = campaignInfoWords[0];
	    	            	String campaignCode = campaignInfoWords[1];
	    	            	String campaignDate = campaignInfoWords[2];
	    	                campaignHandler.addNewCampaign(campaignName, campaignCode, campaignDate);
	    	            
	                    for(int i = 1; i<campaignWithCustomers.length; i++){
	                        if(!"".equals(campaignWithCustomers[i])){
	                        	customerInfo.add(campaignWithCustomers[i]);
	                        }
	                    }
	                }
	                lineNumber++;
	                counter++;
	            }
	            for(String ci : customerInfo){
	            	customerInfoWords = ci.split(";");
	            	
	            	String campaignId = customerInfoWords[0];
	            	String firstName = customerInfoWords[1];
	            	String lastName = customerInfoWords[2];
	            	String gender = customerInfoWords[3];
	            	String birthyear = customerInfoWords[4];
	            	String address = customerInfoWords[5];
	            	String zipCode = customerInfoWords[6];
	            	String town = customerInfoWords[7];
	            	
	                campaignHandler.addNewCustomer(campaignId, firstName, lastName, gender, birthyear, address, zipCode, town);
	            }

	            buf.close();

	        }catch(IOException ex){
	            throw ex;
	        }
		 
   }
	
	/**
	 * Counts the total number of lines in a file.
	 * 
	 * @param buf An instance of BufferedReader with the file.
	 * @return totalLines The total number of lines in the file.
	 * @throws IOException
	 */
	private int countLines(BufferedReader buf) throws IOException {
		int totalLines = 0;
		while (buf.readLine() != null) {
			totalLines++;
			}
		return totalLines;
		}
	
	/**
	 * Counts the percentage of lines that have been read.
	 * 
	 * @param lineNumber The current number of lines that have been read. 
	 * @param totalLines The total number of lines in the file.
	 * @return
	 */
	private double checkProgressPercentage(int lineNumber, int totalLines) {
		float percent = lineNumber * 100f / totalLines;
		double currentPercentRead = Math.round(percent);
		return currentPercentRead;
	}
        
}
