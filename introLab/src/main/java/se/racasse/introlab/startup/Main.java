package se.racasse.introlab.startup;

import org.apache.log4j.PropertyConfigurator;

import se.racasse.introlab.controller.Controller;
import se.racasse.introlab.view.Interpreter;


/**
 * @author SimonePersson
 * Starts the application.
 */
public class Main {
	private static Controller contr;
	
	/**
	 * Application main method. Configures log4j and starts Interpreter. 
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		
		PropertyConfigurator.configure("src/main/resources/log4j.properties");

		//contr = new Controller();
		
		new Interpreter().start();
	}

}
