package graduate_training_CD;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class JSONConverter {
	

	private Results results;
	
	
	public JSONConverter(){
		
	}
	
	public void setResults(Results results){
		this.results = results;
	}
	
	public String createJsonDrivers(){
		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		JsonArrayBuilder value = factory.createArrayBuilder(); 
		
		for (int i = 0; i < results.getDrivers().size(); i++){
			value.add(factory.createObjectBuilder()
			         .add("Position", i+1)
			         .add("Name", results.getDrivers().get(i).getDriverName())
			         .add("Points", results.getDrivers().get(i).getPoints()));
		}
		
		return value.build().toString();
	}
	
	public String createJsonTeams(){
		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		JsonArrayBuilder value = factory.createArrayBuilder(); 
		
		for (int i = 0; i < results.getTeams().size(); i++){
			value.add(factory.createObjectBuilder()
			         .add("Position", i+1)
			         .add("Team", results.getTeams().get(i).getTeamName())
			         .add("Points", results.getTeams().get(i).getPoints()));
		}
		
		return value.build().toString();
	}
	
}
