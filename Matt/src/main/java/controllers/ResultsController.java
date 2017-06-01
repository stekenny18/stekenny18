package controllers;

import java.io.File;
import java.io.IOException;

import com.google.gson.Gson;

import utils.HtmlParser;

/**
 * A controller class that declares a HTML file to be parsed
 * containing methods to manipulate the data from said file
 * @author 234M
 *
 */
public class ResultsController {

    HtmlParser parser;

    File f = new File("2015 F1 Results & Standings Schedule _ F1-Fansite.com.html");

    public ResultsController() {
        this.parser = new HtmlParser(f);

    }
    /**
     * Converts ArrayList of driver results into Json objects
     * @return A Json object of the top 10 drivers in the F1 Leaderboard
     * @throws IOException
     */
    public String retrieveDrivers() throws IOException {
        
        return new Gson().toJson(parser.retrieveDriverResults());
    }

    /**
     * Converts ArrayList of team results into Json objects
     * @return A Json object of the top 5 teams in the F1 Leaderboard
     * @throws IOException
     */
    public String retrieveTeams() throws IOException {

        return new Gson().toJson(parser.retrieveTeamResults());
        
    }

}
