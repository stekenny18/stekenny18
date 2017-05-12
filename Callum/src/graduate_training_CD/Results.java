package graduate_training_CD;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Callum Degriffa
 *this class stores a list of Team objects and a list of Driver objects
 */
public class Results {
    private List<Driver> topDrivers;
    private List<Team> topTeams;
    
    /**
     * constructor initialises team and driver lists
     */
    public Results(){
        topDrivers = new ArrayList<Driver>();
        topTeams = new ArrayList<Team>();
    }
    
    /**
     * @return topDrivers list
     */
    public List<Driver> getDrivers(){
    	return topDrivers;
    }
    
    /**
     * @return topTeams
     */
    public List<Team> getTeams(){
    	return topTeams;
    }
    
    /**
     * @param driver as Driver object
     */
    public void addDriver(Driver driver){
    	topDrivers.add(driver);
    }
    
    /**
     * @param team as Team object
     */
    public void addTeam(Team team){
    	topTeams.add(team);
    }
    
    
}
