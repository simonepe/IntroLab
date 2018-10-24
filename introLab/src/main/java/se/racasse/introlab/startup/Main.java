package se.racasse.introlab.startup;

import org.apache.log4j.PropertyConfigurator;

import se.racasse.introlab.view.Interpreter;


/**
 * @author SimonePersson
 * Starts the application.
 */
public class Main {
	
	/**
	 * Application main method. Configures log4j and starts Interpreter. 
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		
		PropertyConfigurator.configure("src/main/resources/log4j.properties");
		
		new Interpreter().start();
	}

}
