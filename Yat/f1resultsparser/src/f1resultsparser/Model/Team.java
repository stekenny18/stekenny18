package f1resultsparser.Model;

/**
 * Simple class for storing Team data. Data includes name and total points earned.
 * @author Yat Cheung
 *
 */

public class Team {

	private String name;
	private int points;
	
	/**
     * Class constructor
     * @param name     String representing Team's name
     * @param points   Number representing their total points
     */
	public Team(String name, int points) {
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
