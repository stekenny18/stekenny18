package controllers;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import com.google.gson.Gson;

import models.Results;
import utils.HtmlParser;

/**
 * jUnit test class to test the functionality of the ResultsController class and its methods
 * @author 234M
 *
 */
public class ResultsControllerTest {

    File f = new File("2015 F1 Results & Standings Schedule _ F1-Fansite.com.html");
    HtmlParser test = new HtmlParser(f);
    ResultsController testCont = new ResultsController();

    /**
     * Test to ensure that the correct driver results are being output by the controller
     * @throws IOException
     */
    @Test
    public void driverCorrectOutput() throws IOException {

        ArrayList<Results> arr = new ArrayList<Results>();
        arr.addAll(test.retrieveDriverResults());
        String json = new Gson().toJson(arr);

        String expected = json;
        String actual = testCont.retrieveDrivers();

        assertEquals(expected, actual);

    }

    /**
     * Test to ensure that the correct team results are being output by the controller
     * @throws IOException
     */
    @Test
    public void teamCorrectOutput() throws IOException {

        ArrayList<Results> arr = new ArrayList<Results>();
        arr.addAll(test.retrieveTeamResults());
        String json = new Gson().toJson(arr);

        String expected = json;
        String actual = testCont.retrieveTeams();

        assertEquals(expected, actual);

    }

}
