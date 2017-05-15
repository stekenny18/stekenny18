package graduate_training_CD;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private List<TeamAndDriverSuper> teamOrDriverList;
    
    /**
     * Sets up htmlExtractor before each test case.
     * @throws IOException for parsing a html document in HTMLExtractor.
     */
    @Before
    public void setUp() throws IOException{
        extractor = new HTMLExtractor(url);
    }
    
    /**
     * Tests null list for team for json parsing.
     */
    @Test
    public void passNullListTest(){
        jsonFormat = new JSONConverter();
        assertEquals("should return null", null, jsonFormat.createJsonDriversOrTeam(new ArrayList<TeamAndDriverSuper>()));
    }
    
    /**
     * Tests empty list for json parsing.
     */
    @Test
    public void passEmptyListTest(){
        jsonFormat = new JSONConverter();
        assertEquals("should return empty json", null, jsonFormat.createJsonDriversOrTeam(null));
    }
    
    /**
     * Tests if returned String is of Json array format for drivers list.
     */
    @Test
    public void parseToJSONDriversTest(){
        int driversAmount = 10;
        teamOrDriverList = extractor.extractXDriversOrTeams(10, "table.msr_season_driver_results", "td.msr_driver");
        
        int jsonArraySize;
        boolean jsonArrayTrue = false;
        jsonFormat = new JSONConverter();
        
        try {
            jsonArraySize = new JSONArray(jsonFormat.createJsonDriversOrTeam(teamOrDriverList)).length();
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
     * Tests if returned string is of json array format. 
     */
    @Test
    public void parseToJSONTest(){
        int requestAmount = 1;
        String expectedJsonFormatString = "[{\"Position\":1,\"Name\":\"Mercedes\",\"Points\":703}]";
        boolean jsonArrayTrue = false;
        
        teamOrDriverList = extractor.extractXDriversOrTeams(requestAmount, "table.msr_season_team_results", "td.msr_team");
        jsonFormat = new JSONConverter();
        
        assertEquals("JSON array length should be size of requested drivers amount", expectedJsonFormatString ,jsonFormat.createJsonDriversOrTeam(teamOrDriverList));
        
    }
    
}
