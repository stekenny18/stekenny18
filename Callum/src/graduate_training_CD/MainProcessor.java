package graduate_training_CD;

import java.io.IOException;

/**
 * @author Callum Degriffa
 *MainProcessor acts as the controller for the application running all the processes,
 *that the application provides.
 */
public class MainProcessor {
    
    /**
     * this main method is the controller for the whole application.
     * @param args
     * @throws IOException for HTMLExtractor initialisation
     */
    public static void main(String [] args) throws IOException{
        String url = "resources/2015 F1 Results & Standings Schedule _ F1-Fansite.com.html";
        HTMLExtractor extractor = new HTMLExtractor(url);
        
        extractor.extractXDrivers(10, "table.msr_season_driver_results");
        extractor.extractXTeams(5, "table.msr_season_team_results");
        
        System.out.println("json: ");
        JSONConverter jsonFormat = new JSONConverter(extractor.getResults());
        System.out.println(jsonFormat.createJsonDrivers());
        System.out.println(jsonFormat.createJsonTeams());
    }
}
