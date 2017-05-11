package f1resultsparser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class F1ResultsDisplay {
	String url;
	String path;
	private static final Logger LOGGER = Logger.getLogger( F1ResultsDisplay.class.getName() );
	
	F1ResultsDisplay() {
		url = "http://www.f1-fansite.com/f1-results/2015-f1-results-standings/";
		path = "src/f1resultsparser/2015F1.html";
		
	}
	
	Document parseHTML() throws IOException {
		HTMLParser hParser = new HTMLParser();
		//return hParser.loadFromFile(path);	
		return hParser.loadFromUrl(url);
	}
	
	LinkedList<Driver> retrieveDrivers(Document doc) {
		LinkedList<Driver> drivers = new LinkedList<>();
		Element table = doc.select("table[class=motor-sport-results msr_season_driver_results]").first();
		
		Iterator<Element> driverIter = table.select("td[class=msr_driver]").iterator();
		Iterator<Element> pointsIter = table.select("td[class=msr_total]").iterator();
		
		while (driverIter.hasNext()) {
			String name = driverIter.next().select("a").first().text();
			int total = Integer.parseInt(pointsIter.next().text());
			
			Driver driver = new Driver(name, total);
			drivers.add(driver);
		}
		return drivers;
		
	}
	
	LinkedList<Team> retrieveTeams(Document doc) {
		LinkedList<Team> teams = new LinkedList<>();
		Element table = doc.select("table[class=motor-sport-results msr_season_team_results]").first();
		Element tbody = table.select("tbody").first();
		Iterator<Element> teamIter = tbody.select("td[class=msr_team]").iterator();
		Iterator<Element> pointsIter = table.select("td[class=msr_total]").iterator();
		
		while (teamIter.hasNext()) {
			String name = teamIter.next().select("a").first().text();
			int total = Integer.parseInt(pointsIter.next().text());
			
			Team team = new Team(name, total);
			teams.add(team);
		}
		
		return teams;
		
	}
	
	String driversToJson(List<Driver> list) {
		JSONParser jp = new JSONParser();
		return jp.buildJson(list);

	}
	
	String teamsToJson(List<Team> list) {
		JSONParser jp = new JSONParser();
		return jp.buildJson(list);

	}
	
	String getTopNDrivers(LinkedList<Driver> all, int n) {
		try {
			List<Driver> topN = all.subList(0,n+1);
			return driversToJson(topN);
		}
		catch(IndexOutOfBoundsException e) {
			LOGGER.info("Error: There are less than " + n + "results!");
			LOGGER.info(e.toString()); 
			LOGGER.info("Returning all " + all.size() + " results instead.");
			return driversToJson(all);
		}
		
	}
	
	String getTopNTeams(LinkedList<Team> all,int n) {
		try {
			List<Team> topN = all.subList(0,n+1);
			return teamsToJson(topN);
		}
		
		catch(IndexOutOfBoundsException e) {
			LOGGER.info("Error: There are less than " + n + "results!");
			LOGGER.info(e.toString()); 
			LOGGER.info("Returning all " + all.size() + " results instead.");
			return teamsToJson(all);
		}
	}

	int displayMenu() {
		System.out.println("2015 F1 Results To Json"
				+ "\n====================="
				+ "\nSelect a task:"
				+ "\n1: Top 10 Drivers"
				+ "\n2: Top 5 Teams");
		int i = -1;
		Scanner reader = new Scanner(System.in);
		i = reader.nextInt();
		while (i != 1 && i != 2 ) {
			LOGGER.info("Invalid input. Please select an option (1 or 2).");
			i = reader.nextInt();
		}
		reader.close();
		return i;
	}

	String getResults(Document doc, int i) {
		String json = "";
		try {
			if (i==1) {
				LinkedList<Driver> list = retrieveDrivers(doc);
				json = getTopNDrivers(list, 10);
			}
			
			else if (i==2) {
				LinkedList<Team> list = retrieveTeams(doc);
				json = getTopNTeams(list, 5);
			}
			
			else {
				throw new IllegalArgumentException("Did not receive 1 or 2 as input");
			}
			
			System.out.println("Writing to file...");
			Path file = Paths.get("results.json");
			Files.write(file, json.getBytes(), StandardOpenOption.CREATE);
			System.out.println("Results saved to results.json");
			
			return json;
		}
		
		catch(IOException e) {
			LOGGER.info("Error writing to file");
			LOGGER.info(e.toString()); 
		}
		return json;
	}
	
	public static void main(String[] args) throws IOException {
		F1ResultsDisplay fRDisplay = new F1ResultsDisplay();
		Document doc = fRDisplay.parseHTML();
		int input = fRDisplay.displayMenu();
		String json = fRDisplay.getResults(doc, input);
		
		System.out.println("json: " + json);
		
		
	}
}

