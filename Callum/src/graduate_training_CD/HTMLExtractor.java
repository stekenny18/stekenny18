package graduate_training_CD;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * @author Callum Degriffa
 *	HTMLExtractor parses HTML files then extracts the appropriate data feeding them into lists
 */
public class HTMLExtractor {
    private Document HTMLDoc;
    private Elements elements;
    private Results results;
    
    /**
     * @param url as String.
     * @throws IOException
     * HTMLExtractor constructor initialises results, parses a HTML doc using the url param
     */
    public HTMLExtractor(String url) throws IOException{
        if (url != null && url != ""){
        File input = new File(url);
            if (input.isFile()){
                HTMLDoc = Jsoup.parse(input , "UTF-8");
                results = new Results();
            }
            else{
                HTMLDoc = null;
            }
        }
    }
    
    /**
     * @param amount as integer
     * @param passedElements as String
     * extracts a given amount of drivers from a given table creating a results set.
     */
    public void extractXDrivers(int amount, String passedElementName){
        elements = retrieveElement(passedElementName, "tbody");
        int defaultSize = elements.select("td.msr_total").size();
        
        if(amount > defaultSize){
            amount = defaultSize;
        }
        
        for (int i = 0; i < amount; i++){
            Driver driver = new Driver();
            driver.setName(elements.select("td.msr_driver").get(i).select("a").text());
            driver.setPoints(Integer.valueOf(elements.select("td.msr_total").get(i).text()));
            results.addDriver(driver);
        }
        
    }
    
    /**
     * @param amount as integer 
     * @param passedElement as String
     * extracts a given amount of teams from a given table creating a results set.
     */
    public void extractXTeams(int amount, String passedElementName){
        elements = retrieveElement(passedElementName, "tbody");
        int defaultSize = elements.select("td.msr_total").size();
        
        if(amount > defaultSize){
            amount = defaultSize;
        }
        
        for (int i = 0; i < amount; i++){
            Team team = new Team();
            team.setName(elements.select("td.msr_team").get(i).select("a").text());
            team.setPoints(Integer.valueOf(elements.select("td.msr_total").get(i).text()));
            results.addTeam(team);
        }
    }
    private Elements retrieveElement(String passedElementName, String elementType){
        return HTMLDoc.select(passedElementName).select(elementType);
    }
    
    /**
     * @return elements
     */
    public Elements getElement(){
        return elements;
    }
    
    /**
     * @return results
     */
    public Results getResults(){
        return results;
    }
    
    /**
     * @return HTMLDoc as Document
     */
    public Document getHTMLDoc(){
        return HTMLDoc;
    }
}
