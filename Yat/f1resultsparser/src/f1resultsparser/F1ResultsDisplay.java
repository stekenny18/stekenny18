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
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class F1ResultsDisplay {
	static String url = "http://www.f1-fansite.com/f1-results/2015-f1-results-standings/";
	static String path = "src/f1resultsparser/2015F1.html";
	Document doc;
	
	F1ResultsDisplay() {
		
	}
	
	Document parseHTML() throws IOException {
		HTMLParser hParser = new HTMLParser();
		return doc = hParser.loadFromFile(path);	
	}
	
	LinkedList<Driver> retrieveDrivers() {
		LinkedList<Driver> drivers = new LinkedList<Driver>();
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
	
	LinkedList<Team> retrieveTeams() {
		LinkedList<Team> teams = new LinkedList<Team>();
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
	
	String getTopNDrivers(int n) {
		List<Driver> topN = retrieveDrivers().subList(0,n+1);
		return driversToJson(topN);
		
	}
	
	String getTopNTeams(int n) {
		List<Team> topN = retrieveTeams().subList(0,n+1);
		return teamsToJson(topN);
	}

	void displayMenu() {
		System.out.println("2015 F1 Results To Json"
				+ "\n====================="
				+ "\nSelect a task:"
				+ "\n1: Top 10 Drivers"
				+ "\n2: Top 5 Teams");
		int i = -1;
		Scanner reader = new Scanner(System.in);
		i = reader.nextInt();
		while (i != 1 && i != 2 ) {
			System.out.println("Invalid input. Please select an option (1 or 2).");
			i = reader.nextInt();
		}
		reader.close();
		getResults(i);	
	}

	void getResults(int i) {
		try {
			parseHTML();
			String json = "";
			if (i==1) {
				json = getTopNDrivers(10);
			}
			
			else if (i==2) {
				json = getTopNTeams(5);
			}
			
			System.out.println("Results: \n" + json);
			System.out.println("Writing to file...");
			Path file = Paths.get("results.json");
			Files.write(file, json.getBytes(), StandardOpenOption.CREATE);
			System.out.println("Results saved to results.json");
		}
		
		catch(IOException e) {
			System.out.println("Error writing to file");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		F1ResultsDisplay test = new F1ResultsDisplay();
		test.displayMenu();
	}
}

