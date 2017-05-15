package graduate_training_CD;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        List<TeamAndDriverSuper> teamOrDriverList;
        if(extractor.getHTMLDoc() != null){
            JSONConverter jsonFormat = new JSONConverter();
            teamOrDriverList = extractor.extractXDriversOrTeams(10, "table.msr_season_driver_results", "td.msr_driver");
            System.out.println(jsonFormat.createJsonDriversOrTeam(teamOrDriverList));
            teamOrDriverList = extractor.extractXDriversOrTeams(1, "table.msr_season_team_results", "td.msr_team");
            System.out.println(jsonFormat.createJsonDriversOrTeam(teamOrDriverList));
        System.out.println("json: ");
        
        
        
        }else{
            System.out.println("HTMLDOC not found");
        }
    }
}
