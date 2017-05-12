package graduate_training_CD;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Callum Degriffa
 */
public class HTMLExtractorTest {
    String url;
    HTMLExtractor extractor;

    /**
     * @throws IOException for parsing a html document in HTMLExtractor
     */
    @Before
    public void setUp() throws IOException{
        String url = "resources/2015 F1 Results & Standings Schedule _ F1-Fansite.com.html";
        extractor = new HTMLExtractor(url);

    }

    /**
     * tests if driver list is size of given list size
     */
    @Test
    public void driverListSizeTest(){
        int listSize = 22;
        extractor.extractXDrivers(listSize, "table.msr_season_driver_results");

        assertEquals("expected results = " + listSize, listSize, extractor.getResults().getDrivers().size());

    }

    /**
     * Tests if team list size is of given list size
     */
    @Test
    public void teamListSizeTest(){
        int listSize = 5;
        extractor.extractXTeams(listSize, "table.msr_season_team_results");

        assertEquals("expected results = " + listSize, listSize, extractor.getResults().getTeams().size());
    }

    /**
     * Tests if list size given is too large 
     * expected result is to return default list size of driver table size
     */
    @Test
    public void driverListTooLargeTest(){
        int tooBigListSize = 26;

        extractor.extractXDrivers(tooBigListSize, "table.msr_season_driver_results");

        assertEquals("should default to the size of table", extractor.getElement().select("td.msr_driver").size(), extractor.getResults().getDrivers().size());

    }

    /**
     * tests if list size given is too large
     * expected result is to return default list size of team table size
     */
    @Test
    public void teamListTooLargeTest(){
        int tooBigListSize = 15;

        extractor.extractXTeams(tooBigListSize, "table.msr_season_team_results");

        assertEquals("expected results = " + extractor.getElement().select("td.msr_team").size(),  extractor.getElement().select("td.msr_team").size(), extractor.getResults().getTeams().size());
    }
    
    /**
     * Testing for inputting empty url
     */
    @Test
    public void emptyUrlTest(){
        boolean noException = false;
        
        try {
            extractor = new HTMLExtractor("");
            noException = true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertTrue("should be parsed with no errors", noException);
    }
    
    /**
     * testing for inputting null url
     */
    @Test
    public void nullUrlTest(){
        boolean noException = false;
        
        try {
            extractor = new HTMLExtractor(null);
            noException = true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertTrue("should not throw an exception", noException);
    }
    
    /**
     * testing for inputting 'incorrect' or non reachable urls
     */
    @Test
    public void incorrectUrlTest(){
        boolean noException = false;
        
        try {
            extractor = new HTMLExtractor("google.co.uk");
            noException = true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }

        assertTrue("should not throw an exception", noException);
    }

}
