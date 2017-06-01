package views;

import java.io.IOException;

import controllers.ResultsController;

/**
 * Alleged viewer class - no longer used however
 * consider moving main to another class
 * @author 234M
 *
 */
public class ResultsView {

    /**
     * Instantiates new ResultsController object so its methods can be called
     * both print statements print the Json driver and team results respectively
     * @param args
     * @throws IOException
     */
    public static void main(String args[]) throws IOException {

        ResultsController cont = new ResultsController();

        System.out.println(cont.retrieveDrivers());
        System.out.println(cont.retrieveTeams());

    }

}
