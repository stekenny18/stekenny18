package graduate_training_CD;

/**
 * @author Callum Degriffa
 *super class for drivers and team
 */
public class TeamAndDriverSuper {
    private String name;
    private int points;
    
    /**
     * @param name as String 
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * @param points as integer
     */
    public void setPoints(int points) {
        this.points = points;
    }
    
    /**
     * @return String name
     */
    public String getName(){
        return name;
    }
    
    /**
     * @return integer points
     */
    public int getPoints() {
        return points;
    }
}
