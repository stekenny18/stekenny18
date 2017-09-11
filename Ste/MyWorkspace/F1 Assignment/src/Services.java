import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Services {
	/**
	 * To get around possible JDW network connection issues that would block the website.
	 */
      private Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("jdw_websense.jdwilliams.local", 8080));   
     
      protected ArrayList<Results> teamResults = new ArrayList<>();
      protected ArrayList<Results> driverResults = new ArrayList<>();
      
      private static final int THIRDTABLE = 2;
      private static final int FOURTHTABLE = 3;
      private static final int DRIVERTABLE = 0;
      private static final int DRIVERPOINTS = 10;
      private static final int TEAMNAME = 1;
      private static final int TEAMPOINTS = 22;
      private static final int TABLESIZE = 10;
      private static int driverPosition = 1;
      private static int teamPosition = 1;
 
    /**
     * Uses the jsoup library to extract HTML from the URL and returns Document jdoc. 
     * @return Document jdoc
     * @throws IOException
     */
    protected Document getDocument() throws IOException{
        Document jdoc = Jsoup.connect("http://www.f1-fansite.com/f1-results/2015-f1-results-standings/").proxy(proxy).get(); 
    	return jdoc;
    }
    /**
     * Selects rows from the third table in jdoc to create drivers Elements. 
     * @param jdoc
     */
	protected void retreiveDriverInformation(Document jdoc){
		
		Element driverTable = jdoc.select("table").get(THIRDTABLE); 
	    Elements driverRows = driverTable.select("tr");
		
		for (int i = 1; i <= TABLESIZE; i++) {   
		    Element row = driverRows.get(i);
		    Elements drivers = row.select("td");
		    addDriversToList(drivers);
		}
	}
    /**
     * Takes drivers Elements and populates the driverResults ArrayList with String data.	
     * @param drivers
     */
	protected void addDriversToList(Elements drivers) {
		if (drivers.get(DRIVERTABLE).hasText() && drivers.get(DRIVERPOINTS).hasText()){ 
	    	
	    	driverResults.add(new Results (drivers.get(DRIVERTABLE).text(),drivers.get(DRIVERPOINTS).text()));
	     }
	}
    /**
     * Selects rows from the fourth table in jdoc to create teams Elements. 
     * @param jdoc
     */
	protected void retreiveTeamInformation(Document jdoc){
		
		Element teamTable = jdoc.select("table").get(FOURTHTABLE); 
	    Elements teamRows = teamTable.select("tr");
		
		for (int i = 1; i <= TABLESIZE; i+=2) {  
		    Element row = teamRows.get(i);
		    Elements teams = row.select("td");
		    addTeamsToList(teams);
		}
	}
	/**
	 * Takes teams Elements and populates the teamResults ArrayList with String data.	    
	 * @param teams
	 */
	protected void addTeamsToList(Elements teams){
		if (teams.get(TEAMNAME).hasText() && teams.get(TEAMPOINTS).hasText()){ 
		   
		   teamResults.add(new Results (teams.get(TEAMNAME).text(),teams.get(TEAMPOINTS).text()));
		  }
	 }	
	/**
	 * Outputs driverResults Arraylist to the console.		
	 * @throws IOException 
	 */
	protected void createDriverResults() throws IOException{
		 retreiveDriverInformation(getDocument());
		 System.out.println("Driver \t\t\t Total Points");	
		 for(Results result : driverResults) {	 
			 System.out.println(driverPosition + " " + result.name + "\t\t\t" + result.points);
			 driverPosition++;
		 }
		 System.out.println();
	}
	 
	/**
	 * Outputs teamResults Arraylist to the console.
	 * @throws IOException 
	 */
	 protected void createTeamResults() throws IOException{
		 retreiveTeamInformation(getDocument());
		 System.out.println("Team \t\t\t Total Points");	
		 for(Results result : teamResults) {
			  System.out.println(teamPosition + " " + result.name + "\t\t\t" + result.points);
			  teamPosition++;
	 }
   }
}


