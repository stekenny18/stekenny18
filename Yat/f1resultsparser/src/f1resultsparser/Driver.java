package f1resultsparser;

/**
 * Simple class for storing Driver data. Data includes name and total points earned.
 * @author Yat Cheung
 *
 */

public class Driver {
	String name;
	int points;
	
	/**
	 * Class constructor
	 * @param name     String representing Driver's name
	 * @param points   Number representing their total points
	 */
	public Driver(String name, int points) {
		this.name = name;
		this.points = points;
	}
	
	/**
	 * Overridden toString() method.
	 * @return     String in the format "name points"
	 */
	@Override
	public String toString() {
		return this.name + " " + this.points;
	}

}
