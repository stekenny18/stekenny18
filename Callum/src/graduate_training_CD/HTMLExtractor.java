package graduate_training_CD;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HTMLExtractor {
    private Document HTMLDoc;
    private Elements elements;
    private Results results;
    
    public HTMLExtractor(){
        results = new Results();
    }
    
    public void setDocument(String url) throws IOException{
    	File input = new File(url);
        HTMLDoc = Jsoup.parse(input , "UTF-8");
    }
    
    public Document getDoc(){
        return HTMLDoc;
    }
    
    public Elements getElement(){
        return elements;
    }
    
    public void setElement(String name){
    	this.elements = HTMLDoc.select(name);
    }
    public Results getResults(){
    	return results;
    }
    
    public void extractDrivers(){
    	for (int i = 0; i < 10; i++){
    	Driver driver = new Driver();
    	driver.setDriverName(elements.select("tbody").select("tr").get(i).select("td").get(0).select("a").text());
    	driver.setPoints(Integer.valueOf(elements.select("tbody").select("tr").get(i).select("td").get(10).text()));
    	results.addDriver(driver);
    	}
    	
    }
    
    public void extractTeams(){
    	for (int i = 0; i < 10; i++){
        	Team team = new Team();
        	team.setTeamName(elements.select("tbody").select("tr").get(i).select("td").get(1).select("a").text());
        	team.setPoints(Integer.valueOf(elements.select("tbody").select("tr").get(i).select("td:last-child").text()));
        	results.addTeam(team);
        	i++;
        	}
    	
    }
    
    
   
    
}
