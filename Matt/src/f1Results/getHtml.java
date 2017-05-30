package f1Results;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

public class getHtml {
	
	/*private static String getHtmlData() {
		
		Document PreParse;
		String PostParse;
		
		try {
			PreParse = Jsoup.connect("http://www.google.com/").get();
		} catch (IOException e) {
			
			throw new RuntimeException(e); 
		}
		String proxyParse = PreParse.title();
		PostParse = proxyParse;
		
		return PostParse; 

	}*/
	
	public Document getFile() {
		
		File input = new File ("2015 F1 Results & Standings Schedule _ F1-Fansite.com.html");
		Document htmlFile;
	
		try {
		
			htmlFile = Jsoup.parse(input, "UTF-8");
		
		} catch (IOException e) {
		
			throw new RuntimeException(e); 
		}
	
		return htmlFile;
	}
	
	public ArrayList<Results> retrieveDriverResults() {
		
		Results drivers;
		ArrayList<Results> driverResList = new ArrayList<Results>();
		
		Elements driverTable = getFile().select("table[class = motor-sport-results msr_season_driver_results]");
		Element tableEl = driverTable.get(0);
		Elements rows = tableEl.getElementsByTag("tr");
		
		System.out.println("Driver Leaderboard Rankings");
		
		
		for (int i = 1; i <= 10; i++) {
			
			Element thisRow = rows.get(i);
			Elements cels = thisRow.children();
			Element positionCel = cels.first();
			Elements nameCel = cels.get(1).select("a");
			Element pointsCel = cels.last();
			//System.out.println(positionCel.text() + " " + nameCel.text() + " " + pointsCel.text());
			drivers = new Results(positionCel.text(), nameCel.text(), pointsCel.text());
			driverResList.add(drivers);
	
		}
		
		return driverResList;
		
	}
	
	public ArrayList<Results> retrieveTeamResults() {
	
		Results teams;
		ArrayList<Results> teamResList = new ArrayList<Results>();
		
		Elements teamTable = getFile().select("table[class = motor-sport-results msr_season_team_results]");
		Element tableEl = teamTable.get(0);
		Elements rows = tableEl.getElementsByTag("tr");
		
		System.out.println("\r\n Team Leaderboard Rankings");
		
		
		for (int i = 1; i <= 10; i++) {
			
			Element thisRow = rows.get(i);
			Elements cels = thisRow.children();
			Element positionCel = cels.first();
			Elements nameCel = cels.get(1).select("a");
			Element pointsCel = cels.last();
			//System.out.println(positionCel.text() + " " + nameCel.text() + " " + pointsCel.text());
			teams = new Results(positionCel.text(), nameCel.text(), pointsCel.text());
			teamResList.add(teams);
			i++;
			
		}
		
		return teamResList;
				
	}

}

