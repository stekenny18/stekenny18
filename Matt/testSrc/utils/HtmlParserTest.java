package utils;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

import org.junit.Test;

import utils.HtmlParser;

/**
 * jUnit test class to test the functionality of the HtmlParser class
 * 
 * @author 234M
 *
 */
public class HtmlParserTest {

    /**
     * Tests that a FileNotFoundException is thrown if the supplied file cannot
     * be found and cannot access driver results
     * 
     * @throws IOException
     */
    @Test(expected = FileNotFoundException.class)
    public void fileDoesNotExist() throws IOException {

        File file = new File("file.html");
        HtmlParser test = new HtmlParser(file);
        test.retrieveDriverResults();

    }

    /**
     * Tests that a FileNotFoundException is thrown if the supplied file cannot
     * be found and cannot access team results
     * @throws IOException
     */
    @Test(expected = FileNotFoundException.class)
    public void fileDoesNotExist1() throws IOException {

        File file = new File("file.html");
        HtmlParser test = new HtmlParser(file);
        test.retrieveTeamResults();

    }

    /**
     * Tests that a FileNotFoundException is thrown if the given file is not in the HTML format
     * @throws IOException
     */
    @Test(expected = FileNotFoundException.class)
    public void FileIsNotHtml() throws IOException {

        File file = new File("file");
        HtmlParser test = new HtmlParser(file);
        test.retrieveDriverResults();
        test.retrieveTeamResults();
    }

    /**
     * Test to confirm no more than ten driver results are stored into the ArrayList as Results objects
     * @throws IOException
     */
    @Test
    public void driverListDefault() throws IOException {

        File file = new File("2015 F1 Results & Standings Schedule _ F1-Fansite.com.html");
        HtmlParser test = new HtmlParser(file);

        assertEquals("expected 10 ", 10, test.retrieveDriverResults().size());
    }

    /**
     * Test to confirm that an IndexOutOfBoundsException is not thrown if results have less than ten rows
     * @throws IOException
     */
    @Test
    public void driverListLessThan10() throws IOException {

        File file = new File("dummy1.html");
        HtmlParser test = new HtmlParser(file);

        assert (test.retrieveDriverResults().size() < 10);
    }

    /**
     * Test to confirm no more than 5 team results are stored into the ArrayList as Results objects
     * @throws IOException
     */
    @Test
    public void TeamListDefault() throws IOException {

        File file = new File("2015 F1 Results & Standings Schedule _ F1-Fansite.com.html");
        HtmlParser test = new HtmlParser(file);

        assertEquals("expected 5 ", 5, test.retrieveTeamResults().size());
    }

    /**
     * Test to confirm that an IndexOutOfBoundsException is not thrown if results have less than five rows
     * @throws IOException
     */
    @Test
    public void teamListLessThan10() throws IOException {

        File file = new File("dummy1.html");
        HtmlParser test = new HtmlParser(file);

        assert (test.retrieveTeamResults().size() < 10);
    }

}
