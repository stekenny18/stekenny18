package graduate_training_CD;

import static org.junit.Assert.*;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Callum Degriffa
 */
public class JSONConverterTest {
    
    private String url = "resources/2015 F1 Results & Standings Schedule _ F1-Fansite.com.html";
    private HTMLExtractor extractor;
    private JSONConverter jsonFormat;
    
    /**
     * @throws IOException for parsing a html document in HTMLExtractor
     * sets up htmlExtractor before each test case
     */
    @Before
    public void setUp() throws IOException{
        extractor = new HTMLExtractor(url);
    }
    
    /**
     * tests null list for drivers for json parsing
     */
    @Test
    public void passNullDriversListTest(){
        jsonFormat = new JSONConverter(null);
        assertEquals("should return null", null, jsonFormat.createJsonDrivers());
    }
    
    /**
     * tests null list for team for json parsing
     */
    @Test
    public void passNullTeamListTest(){
        jsonFormat = new JSONConverter(null);
        assertEquals("should return null", null, jsonFormat.createJsonTeams());
    }
    
    /**
     * tests empty driver list for json parsing
     */
    @Test
    public void passEmptyDriverListTest(){
        jsonFormat = new JSONConverter(new Results());
        assertEquals("should return empty json", null, jsonFormat.createJsonDrivers());
    }
    
    /**
     * tests empty team list for json parsing
     */
    @Test
    public void passEmptyTeamListTest(){
        jsonFormat = new JSONConverter(new Results());
        assertEquals("should return empty json", null, jsonFormat.createJsonTeams());
    }
    
    /**
     * tests if returned String is of Json array format for drivers list
     */
    @Test
    public void parseToJSONDriversTest(){
        int driversAmount = 10;
        extractor.extractXDrivers(driversAmount, "table.msr_season_driver_results");
        
        int jsonArraySize;
        boolean jsonArrayTrue = false;
        jsonFormat = new JSONConverter(extractor.getResults());
        
        try {
            jsonArraySize = new JSONArray(jsonFormat.createJsonDrivers()).length();
            jsonArrayTrue = true;
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            jsonArraySize = 0;
        }
        
        assertTrue("returned string should be of json format", jsonArrayTrue);
        assertEquals("JSON array length should be size of requested drivers amount", driversAmount,jsonArraySize);
        
    }
    
    /**
     * tests if returned string is of json array format 
     */
    @Test
    public void parseToJSONTeamTest(){
        int teamAmount = 8;
        extractor.extractXTeams(teamAmount, "table.msr_season_team_results");
        
        int jsonArraySize;
        boolean jsonArrayTrue = false;
        jsonFormat = new JSONConverter(extractor.getResults());
        
        try {
            jsonArraySize = new JSONArray(jsonFormat.createJsonTeams()).length();
            jsonArrayTrue = true;
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            jsonArraySize = 0;
            }
            
        assertTrue("returned string should be of json array format", jsonArrayTrue);
        assertEquals("JSON array length should be size of requested drivers amount", teamAmount ,jsonArraySize);
        
    }
    
}
