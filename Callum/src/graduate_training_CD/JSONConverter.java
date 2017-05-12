package graduate_training_CD;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;

/**
 * @author Callum Degriffa
 *JSONConverter takes a Results object and converts it into an array of JSON objects
 */
public class JSONConverter {
    
    private Results results;
    private JsonBuilderFactory factory = Json.createBuilderFactory(null);
    private JsonArrayBuilder value = factory.createArrayBuilder(); 
    
    /**
     * @param results as Results
     * initialises results using given Results
     */
    public JSONConverter(Results results){
        this.results = results;
    }
    
    /**
     * @return String
     * builds a json array of driver objects using a not null or not empty results object
     */
    public String createJsonDrivers(){
    	
        if(results != null && results.getDrivers().size() > 0){
            for (int i = 0; i < results.getDrivers().size(); i++){
                value.add(factory.createObjectBuilder()
                        .add("Position", i+1)
                        .add("Name", results.getDrivers().get(i).getName())
                        .add("Points", results.getDrivers().get(i).getPoints()));
            }
            return value.build().toString();
        }
        
        return null;
    }
    
    /**
     * @return String
     * builds a json array of Team objects using a not null or not empty results object
     */
    public String createJsonTeams(){
        
        if(results != null && results.getTeams().size() > 0){
            for (int i = 0; i < results.getTeams().size(); i++){
                value.add(factory.createObjectBuilder()
                        .add("Position", i+1)
                        .add("Team", results.getTeams().get(i).getName())
                        .add("Points", results.getTeams().get(i).getPoints()));
            }
            return value.build().toString();
        }
        
        return null;
    }
    
}
