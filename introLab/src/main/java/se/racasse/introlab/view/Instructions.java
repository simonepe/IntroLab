package se.racasse.introlab.view;

/**
 * @author SimonePersson
 * Contains information on what actions are available.
 */
public class Instructions {
	public static final String inputOptions = "The following commands are available: \n"
			+ "QUIT \t terminates the application \n"
			+ "SHOW_ALL \t fetches all campaigns with their respective customers \n"
			+ "IMPORT /file path \t imports campaigns from file with given path \n"
			+ "EXPORT /id /file path \t exports customers from campaign with given id to file with given path \n"
			+ "CUSTOMER_DATA /id \t fecthes customers from campaign with given id \n"
			+ "DELETE_BEFORE_DATE /date \t deletes all campaigns older than the given date with the format yyyy-mm-dd \n";

}
