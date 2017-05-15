package graduate_training_CD;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    
    /**
     * HTMLExtractor constructor initialises results, then parses a HTML doc using the url param.
     * @param url as String.
     * @throws IOException
     */
    public HTMLExtractor(String url) throws IOException{
        if (url != null && !url.isEmpty()){
        File input = new File(url);
            if (input.isFile()){
                HTMLDoc = Jsoup.parse(input , "UTF-8");
            }
            else{
                HTMLDoc = null;
            }
        }
    }
    
    /**
     * @param amount as integer
     * @param passedElements as String
     * Extracts a given amount of drivers from a given table creating a results set.
     */
    public List<TeamAndDriverSuper> extractXDriversOrTeams(int amount, String passedElementName, String DriverOrteamElements){
        elements = retrieveElement(passedElementName, "tbody");
        int defaultSize = elements.select("td.msr_total").size();
        
        List<TeamAndDriverSuper> teamOrDriverList = new ArrayList<TeamAndDriverSuper>();
        
        if(amount > defaultSize){
            amount = defaultSize;
        }
        
        for (int i = 0; i < amount; i++){
            TeamAndDriverSuper teamAndDriverSuper = new TeamAndDriverSuper();
            teamAndDriverSuper.setName(elements.select(DriverOrteamElements).get(i).select("a").text());
            teamAndDriverSuper.setPoints(Integer.valueOf(elements.select("td.msr_total").get(i).text()));
            teamOrDriverList.add(teamAndDriverSuper);
        }
        return teamOrDriverList;
    }
    
    /**
     * @param passedElementName
     * @param elementType
     * @return A requested element.
     */
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
     * @return HTMLDoc as Document
     */
    public Document getHTMLDoc(){
        return HTMLDoc;
    }

    public void setElement(String elementName) {
        this.elements = HTMLDoc.select(elementName);
        
    }
}
