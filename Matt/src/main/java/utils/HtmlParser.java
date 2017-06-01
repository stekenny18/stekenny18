package utils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import models.Results;

/**
 * This class uses Jsoup to parse a HTML file and return a set of results
 * to be converted into Json objects
 * @author 234M
 *
 */
public class HtmlParser {

    public File filePreParse;

    public HtmlParser(File htmlFileIn){

        filePreParse = htmlFileIn;

    }

   /**
    * This method will utilise Jsoup to parse the supplied HTML file into a document object
    * 
    * @return returns the parsed HTML file as a document
    * @throws IOException throws exception if supplied file is not of the type .html
    */
    public Document parseFromFile() throws IOException {

        File input = filePreParse;
        Document htmlFile;

        if(input.toString().contains(".html")) {


            htmlFile = Jsoup.parse(input, "UTF-8");

        } else {
            
            throw new FileNotFoundException("not a valid file");

        }


        return htmlFile;
    }
   

    /**
     * This method retrieves a set of results from the newly parsed document
     * implements Jsoup's select function to target specific aspects of the HTML file's DOM
     * 
     * @return returns an ArrayList of Result objects
     * the ArrayList contains the top 10 F1 Drivers in descending order from 1st to 10th 
     * @throws IOException
     */
    public ArrayList<Results> retrieveDriverResults() throws IOException {

        Results drivers;
        ArrayList<Results> driverResList = new ArrayList<Results>();
        int index;
        //selects from table with the following class name
        Elements driverTable = parseFromFile().select("table[class = motor-sport-results msr_season_driver_results]");
        Element tableElements = driverTable.get(0);
        Elements rows = tableElements.getElementsByTag("tr");

        if(rows.size() < 10 ) {

            index = rows.size() - 1;
        }
        else {

            index = 10;
        }

        System.out.println("Driver Leaderboard Rankings");

        for (int i = 1; i <= index; i++) {

            Element thisRow = rows.get(i);
            Elements cells = thisRow.children();
            Element positionCell = cells.first();
            Elements nameCell = cells.get(1).select("a"); //<a> tag referenced so that only text is returned not surrounding tags
            Element pointsCell = cells.last();
            drivers = new Results(positionCell.text(), nameCell.text(), pointsCell.text());
            driverResList.add(drivers);

        }

        return driverResList;

    }
    /**
     * Functions the same as the above retrieveDriverResults()
     * but instead displays the top 5 F1 teams in descending order
     * 
     * @return an ArrayList of 5 Result objects
     * @throws IOException
     */

    public ArrayList<Results> retrieveTeamResults() throws IOException {

        Results teams;
        ArrayList<Results> teamResList = new ArrayList<Results>();
        int index;
        //selects from table with the following class name
        Elements teamTable = parseFromFile().select("table[class = motor-sport-results msr_season_team_results]");
        Element tableElements = teamTable.get(0);
        Elements rows = tableElements.getElementsByTag("tr");

        if(rows.size() < 10 ) {

            index = rows.size() - 1;
        }
        else {

            index = 10;
        }

        System.out.println("\r\n Team Leaderboard Rankings");

        for (int i = 1; i <= index; i++) {

            Element thisRow = rows.get(i);
            Elements cells = thisRow.children();
            Element positionCell = cells.first();
            Elements nameCell = cells.get(1).select("a");
            Element pointsCell = cells.last();
            teams = new Results(positionCell.text(), nameCell.text(), pointsCell.text());
            teamResList.add(teams);
            //iterates twice to compensate for html rowspan
            i++;

        }

        return teamResList;

    }

}

