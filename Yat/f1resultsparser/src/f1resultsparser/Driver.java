package f1resultsparser;

public class Driver {
	String name;
	int points;
	
	Driver(String name, int points) {
		this.name = name;
		this.points = points;
	}
	
	@Override
	public String toString() {
		return this.name + " " + this.points;
	}

}
