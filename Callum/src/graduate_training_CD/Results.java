package graduate_training_CD;

import java.util.ArrayList;
import java.util.List;

public class Results {
    private List<Driver> topDrivers;
    private List<Team> topTeams;
    
    public Results(){
        topDrivers = new ArrayList<Driver>();
        topTeams = new ArrayList<Team>();
    }
    
    public List<Driver> getDrivers(){
    	return topDrivers;
    }
    
    public List<Team> getTeams(){
    	return topTeams;
    }
    
    public void addDriver(Driver driver){
    	topDrivers.add(driver);
    }
    
    public void addTeam(Team team){
    	topTeams.add(team);
    }
    
    
}
