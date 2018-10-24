package se.racasse.introlab.view;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import se.racasse.introlab.controller.Controller;
import se.racasse.introlab.model.OutputHandler;

/**
 * @author SimonePersson
 * Interprets input from the user.
 */
public class Interpreter {
	
	private final static Logger LOGGER = Logger.getLogger(Interpreter.class.getPackage().getName());
	private static final String PROMPT = "> ";
	private static final String OPERATION_FAILED = "Operation Failed";
	private static final String NO_SUCH_COMMAND = "Command not recognised!";
	private final Controller contr = new Controller();
	private boolean lookForInput = false;
	private final Scanner console = new Scanner(System.in);
	private final ConsoleOutput output = new ConsoleOutput();
	
	/**
	 * Empty constructor for Interpreter.
	 */
	public Interpreter() {	
	}
	
	/**
	 * Handles input from the user. 
	 */
	public void start() {
		if(lookForInput) {
			return;
		}
		lookForInput = true;
		contr.initConsoleOutput(new ConsoleOutput());
		output.handleMsg("Welcome! \n" + Instructions.inputOptions);
		
		while(lookForInput) {
			try {
				String input = new String(readNextLine());
				String[] msg = input.split("\\s+");
				
				if(msg[0].toUpperCase().equals("QUIT")) {
					contr.quit();
					lookForInput = false;
					
				}else if(msg[0].toUpperCase().equals("SHOW_ALL")) {
					contr.getAllCampaigns();
					
				}else if(msg[0].toUpperCase().equals("IMPORT")) {
					String path = msg[1];
					contr.importFile(path);
					output.handleMsg("The file was imported successully!");
					
				}else if(msg[0].toUpperCase().equals("EXPORT")) {
					int campaignId = Integer.valueOf(msg[1]);
					String path = msg[2];
					contr.exportCustomersByCampaignId(campaignId, path);
					output.handleMsg("The file was exported successully!");
					
				}else if(msg[0].toUpperCase().equals("CUSTOMER_DATA")){
					contr.customerDataById(Integer.valueOf(msg[1]));	
					
				}else if(msg[0].toUpperCase().equals("DELETE_BEFORE_DATE")) {
					contr.deleteCampaignOlderThanDate(msg[1]);
				}else {
					output.handleMsg(NO_SUCH_COMMAND + Instructions.inputOptions);
				}
			}
		catch (FileNotFoundException e){
			output.handleMsg("The file was not found!");
			LOGGER.error("File was not found", e);
		}catch (FileAlreadyExistsException e){
			output.handleMsg("That file already exists!");
			LOGGER.error("File already exists!", e);	
		}catch (Exception e) {
			output.handleMsg(OPERATION_FAILED);
			LOGGER.error(OPERATION_FAILED, e);
		}
	
		}
		
	}
	
	private String readNextLine() {
		 	System.out.print(PROMPT);
	        return console.nextLine();
	    }

	 /**
	 * @author SimonePersson
	 * Implements interface OutputHandler in model to display results from model without making call from model to view.
	 *
	 */
	private class ConsoleOutput implements OutputHandler {
	        
			/**
			 * Displays message to user.
			 * 
			 * @param msg Message to be printed.
			 */
	        public void handleMsg (String msg) {
	            System.out.println(msg);
	        }

	    }
	

}
