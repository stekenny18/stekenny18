package f1resultsparser.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import f1resultsparser.Model.Driver;
import f1resultsparser.Model.Team;
import f1resultsparser.Model.Enums.Modes;
import f1resultsparser.Model.Enums.Tasks;
import f1resultsparser.View.F1ResultsView;

/**
 * Class specifically for the parsing of top drivers and teams from 2015 from F1 fansite.
 * @author Yat Cheung
 *
 */

public class F1Parser {
    private static final Logger LOGGER = Logger.getLogger( F1ResultsView.class.getName() );
    private HTMLParser hParser;
    private JSONBuilder jsonBuilder;
    
    
    /**
     * Class constructor. Creates HTMLParser object and JSONBuilder object required for the task.
     * @param location      String of the url/path of the HTML to be passed into HTMLParser constructor
     * @param mode          String of the mode to be passed into HTMLParser constructor (must be "file" or "url") 
     */
    public F1Parser(String location, String mode) {
        hParser = new HTMLParser(location, mode);
        jsonBuilder = new JSONBuilder();
    }
    
    /**
     * Executes relevant method to retrieve results depending on parameter input.
     * If parameter is 1, executes retrieveAllDrivers and retrieveTopNDrivers and  returns top 10 drivers as Json
     * If parameter is 2, executes retireveAllTeams and retrieveTopNTeams and returns top 5 teams as Json
     * Writes results to file "results.json"
     * @param i     int i denoting option selected. 1 = Top 10 drivers, 2 = Top 5 teams 
     * @return      String json of results, top 10 drivers or top 5 teams depending on option selected
     * @throws IOException  if there was an error writing json string to file.
     */
    public String returnResultsOfOption(int i) throws IOException {
        String json = "";
        Document doc = hParser.loadHTML();
        if ((i-1) == Tasks.DRIVERS.ordinal()) {
            LinkedList<Driver> list = retrieveAllDrivers(doc);
            json = retrieveTopNDrivers(list, 10);
        } else if ((i-1) == Tasks.TEAMS.ordinal()) {
            LinkedList<Team> list = retrieveAllTeams(doc);
            json = retrieveTopNTeams(list, 5);
        } else {
            throw new IllegalArgumentException("Did not receive 1 or 2 as input");
        }

        System.out.println("Writing to file...");
        Path file = Paths.get("results.json");
        Files.write(file, json.getBytes(), StandardOpenOption.CREATE);
        System.out.println("Results saved to results.json");

        return json;
    }
    
    /**
     * Method to retrieve all drivers from the table in the HTML document.
     * Extracts their names and total points, creating a Driver object for each and adding to a list.
     * @param doc   Document of the HTML to extract the information from
     * @return      a LinkedList of Driver objects
     */
    
    public LinkedList<Driver> retrieveAllDrivers(Document doc) {
        LinkedList<Driver> drivers = new LinkedList<Driver>();
        
        Element table = hParser.selectFirstElementByName("table[class=motor-sport-results msr_season_driver_results", doc);
        
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
    
    /**
     * Method to retrieve all teams from the table in the HTML document.
     * Extracts their names and total points, creating a Team object for each and adding to a list. 
     * @param doc   Document of the HTML to extract the information from
     * @return      a LinkedList of Team objects
     */
    public LinkedList<Team> retrieveAllTeams(Document doc) {
        LinkedList<Team> teams = new LinkedList<Team>();
        
        Element table = hParser.selectFirstElementByName("table[class=motor-sport-results msr_season_team_results", doc);
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
    
    /**
     * Method to retrieve top t drivers in json form. Achieves this by passing in a LinkedList of all Drivers and creating a sublist
     * of size n then using a JSONBuilder object to construct a json String of the sublist.
     * @param all   a LinkedList of all Driver objects
     * @param n     an int representing how many top Drivers wanted in the json output. If n is greater than the number of Drivers
     *              in the list, it returns the entire list in json format instead.
     * @return      a String of the top n drivers in json format. 
     */
    
    public String retrieveTopNDrivers(LinkedList<Driver> all, int n) {
        if (n > all.size()) {
            LOGGER.info("There are less results than the " + n + " results requested!");
            LOGGER.info("Returning all " + all.size() + " results instead.");
            return driversToJson(all);
        } else {
            List<Driver> topN = all.subList(0,n);
            return driversToJson(topN);
        }
    }

    
    /**
     * Method to retrieve top n teams in json form. Achieves this by passing in a LinkedList of all Teams and creating a sublist
     * of size n then using a JSONBuilder object to construct a json String of the sublist.
     * @param all   a LinkedList of all Team objects
     * @param n     an int representing how many top Teams wanted in the json output. If n is greater than the number of Teams
     *              in the list, it returns the entire list in json format instead.
     * @return      a String of the top n teams in json format. 
     */
    public String retrieveTopNTeams(LinkedList<Team> all ,int n) {
        
        if (n > all.size()) {
            LOGGER.info((String.format("There are less results than the %d results requested!", n)));
            LOGGER.info((String.format("Returning all %d results instead.", all.size())));
            return teamsToJson(all);
        } else {
            List<Team> topN = all.subList(0,n);
            return teamsToJson(topN);
        }
    }
    
    /**
     * Method to convert a List of Driver objects into a json String
     * @param list      List of Driver objects to be converted
     * @return          json String of all Driver objects in provided List
     */
    public String driversToJson(List<Driver> list) {
        return jsonBuilder.buildJson(list);

    }
    
    /**
    * Method to convert a List of Team objects into a json String
     * @param list      List of Team objects to be converted
     * @return          json String of all Team objects in provided List
     */
    public String teamsToJson(List<Team> list) {
        return jsonBuilder.buildJson(list);
    }
}
