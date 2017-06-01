package models;

 /**
  * Simple POJO class that sorts data parsed from HTML file into an object
  * Consits of three string parameters; position, name, and points
  * @author 234M
  *
  */
public class Results {
	
	private String position;
	private String name;
	private String points;
	
	public Results(String pos, String nam, String point) {
		position = pos;
		name = nam;
		points = point;
	}
	
	@Override
	public String toString (){
	    return position + " " + name + " " + points;
	}

	

	
	
	
	
	
	

}
