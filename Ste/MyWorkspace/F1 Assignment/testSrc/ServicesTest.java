import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

public class ServicesTest extends Services {
	
     private static final int FIRSTTABLE = 0;
     private static final int FIRSTELEMENT = 0;
     private static final String TOPDRIVERNAME = "Lewis Hamilton";
     private static final String TOPDRIVERPOINTS = "381";
     private static final String TOPTEAMNAME = "Mercedes";
     private static final String TOPTEAMPOINTS = "703";
     private static final String BOTTOMDRIVERNAME = "Max Verstappen";
     private static final String BOTTOMDRIVERPOINTS = "49";
     private static final String BOTTOMTEAMNAME = "Force India";
     private static final String BOTTOMTEAMPOINTS = "136";
	
	 Services testService = new Services();
	
	 Document idoc;
	
	@Before
	public void setup() throws IOException {
		File file = new File("Resources/2015 F1 Results & Standings Schedule _ F1-Fansite.com.html");
		idoc = Jsoup.parse(file, "UTF-8");	
	}

	@Test
	public void testGetDocument() throws IOException {
		
		assertEquals("2015 F1 World Championship", testService.getDocument().select("table").get(FIRSTTABLE).select("caption").text());
	}
	
	@Test
	public void testCreateDriverResults() throws IOException {
		
		testService.createDriverResults();
		
		assertEquals(TOPDRIVERNAME, testService.driverResults.get(FIRSTELEMENT).name);
		assertEquals(TOPDRIVERPOINTS, testService.driverResults.get(FIRSTELEMENT).points);
		assertEquals(BOTTOMDRIVERNAME, testService.driverResults.get(testService.driverResults.size()-1).name);
	    assertEquals(BOTTOMDRIVERPOINTS, testService.driverResults.get(testService.driverResults.size()-1).points);
		assertEquals(10, testService.driverResults.size());
	}

	@Test
	public void testCreateTeamResults() throws IOException {
		
		testService.createTeamResults();
		
		assertEquals(TOPTEAMNAME, testService.teamResults.get(FIRSTELEMENT).name);
		assertEquals(TOPTEAMPOINTS, testService.teamResults.get(FIRSTELEMENT).points);
		assertEquals(BOTTOMTEAMNAME, testService.teamResults.get(testService.teamResults.size()-1).name);
	    assertEquals(BOTTOMTEAMPOINTS, testService.teamResults.get(testService.teamResults.size()-1).points);
		assertEquals(5, testService.teamResults.size());
	}
}
