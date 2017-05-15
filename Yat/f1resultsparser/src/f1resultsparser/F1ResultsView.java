package f1resultsparser;

import java.util.Scanner;
import java.util.logging.Logger;


/**
 * Class handling the display and user input.
 * Shows different menus for user to select an option from takes user input.
 * @author Yat Cheung
 *
 */
public class F1ResultsView {
	private static final Logger LOGGER = Logger.getLogger( F1ResultsView.class.getName() );

	/**
	 * Method to display task menu.
	 * Prints to console a list of tasks user can do 
	 */
	public void displayTaskMenu() {
		System.out.println("2015 F1 Results To Json"
				+ "\n====================="
				+ "\nSelect a task:"
				+ "\n1: Top 10 Drivers"
				+ "\n2: Top 5 Teams");
	}
	
	
	/**
	 * Method to display mode menu
	 * Prints to console list of modes user can use
	 */
	public void displayModeMenu() {
        System.out.println("Select mode + "
                + "\n1: From file"
                + "\n2: From website");
    }
	
	/**
	 * Method to receive user input so user can choose options
	 * Does not allow user out of method until they input 1 or 2
	 * @return i       int input representing user's choice to be handled by a different method
	 */
	public int receiveInput() {
	    Scanner reader = new Scanner(System.in);
        int i = reader.nextInt();
        while (i != 1 && i != 2 ) {
            LOGGER.info("Invalid input. Please select an option from 1 - 2" );
            i = reader.nextInt();
        }
        return i;
    }
	
	/**
	 * Method to display the returned results after request is processed
	 * @param json     the String json to display on screen
	 */
	public void displayResults(String json) {
	    System.out.println("Results: " + json);
	}
	
}

