package graduate_training_CD;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MainProcessor {
	
	public static void main(String [] args) throws IOException{
		String url = "resources/2015 F1 Results & Standings Schedule _ F1-Fansite.com.html";
		
		HTMLExtractor extractor = new HTMLExtractor();
		JSONConverter jsonFormat = new JSONConverter();
		
		extractor.setDocument(url);
		
		extractor.setElement("table.msr_season_stats");
		extractor.extractDrivers();
		
		extractor.setElement("table.msr_season_team_results");
		extractor.extractTeams();
		
		
		
		//JsonObjectBuilder json = Json.createObjectBuilder().add("key1", "value1").add("key2", "value2");
		
		System.out.println("json: ");
		jsonFormat.setResults(extractor.getResults());
		System.out.println(jsonFormat.createJsonDrivers());
		System.out.println(jsonFormat.createJsonTeams());
		
		//System.out.println(element.select("tr").get(0).select("th").get(0).text());
		
		
	}
}
