package graduate_training_CD;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;

/**
 * @author Callum Degriffa
 *JSONConverter takes a Results object and converts it into an array of JSON objects
 */
public class JSONConverter {
    
    private JsonBuilderFactory factory = Json.createBuilderFactory(null);
    private JsonArrayBuilder value = factory.createArrayBuilder(); 
    
    /**
     * @param results as Results
     * initialises results using given Results
     */
    public JSONConverter(){
         
    }
    
    /**
     * @return String
     * builds a json array of driver objects using a not null or not empty results object
     */
    public String createJsonDriversOrTeam(List<TeamAndDriverSuper> teamOrDriverList){
    	
        if(teamOrDriverList != null && teamOrDriverList.size() > 0){
            for (int i = 0; i < teamOrDriverList.size(); i++){
                value.add(factory.createObjectBuilder()
                        .add("Position", i+1)
                        .add("Name", teamOrDriverList.get(i).getName())
                        .add("Points", teamOrDriverList.get(i).getPoints()));
            }
            return value.build().toString();
        }
        
        return null;
    }
    
}
