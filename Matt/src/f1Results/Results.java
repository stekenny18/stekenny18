package f1Results;

public class Results {
	
	private String position;
	private String name;
	private String points;
	
	public Results(String pos, String nam, String point) {
		position = pos;
		name = nam;
		points = point;
	}

	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position){
		this.position = position;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPoints() {
		return points;
	}
	
	public void setPoints(String points) {
		this.points = points;
	}

	
	
	
	
	
	

}
