package f1resultsparser.tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;

import f1resultsparser.Controller.HTMLParser;

public class HTMLParserTest {
    HTMLParser hParserModeFile;
    HTMLParser hParserModeUrl;
    
    @Before
    public void setUp() throws Exception {
        hParserModeFile = new HTMLParser("src/f1resultsparser/2015F1.html", "file");
        hParserModeUrl = new HTMLParser("http://www.f1-fansite.com/f1-results/2015-f1-results-standings/", "url");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testInvalidConstructor() throws IllegalArgumentException {
        new HTMLParser("src/f1resultsparser/2015F1.html", "fail" );
    }
    
    @Test
    public void loadHTMLFileTest() throws IOException {
        hParserModeFile.loadHTML();
    }
    
    @Test
    public void loadHTMLUrlTest() throws IOException {
        hParserModeUrl.loadHTML();
    }

    @Test
    public void testLoadFromValidFile() throws IOException {
        hParserModeFile.loadFromFile("src/f1resultsparser/2015F1.html");
        }

    @Test(expected=FileNotFoundException.class)
    public void testLoadFromInvalidFile() throws IOException {
        hParserModeFile.loadFromFile("failcase");
    }

    @Test
    public void testLoadFromValidUrl() throws IOException {
        
        hParserModeUrl.loadFromUrl("http://www.f1-fansite.com/f1-results/2015-f1-results-standings/");

    }

    @Test(expected=IllegalArgumentException.class)
    public void testLoadFromInvalidUrl() throws IOException {
        String str = "";
        hParserModeUrl.loadFromUrl(str);
    }
    
    @Test
    public void testSelectFirstElementByNameFromDocValid() {
        Document docTest = new Document("test doc");
        Element elementTest = docTest.appendElement("testName");
        
        Element extractedElem = hParserModeFile.selectFirstElementByName("testName", docTest);
        
        assertEquals(elementTest, extractedElem);
        
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testSelectFirstElementByNameFromDocNullCase() {
        Document docTest = new Document("test doc");
        docTest.appendElement("testName");
        
        hParserModeFile.selectFirstElementByName(null, docTest);
        
    }
    @Test(expected=IllegalArgumentException.class)
    public void testSelectFirstElementByNameFromDocEmptyCase() {
        Document docTest = new Document("test doc");
        docTest.appendElement("testName");
        
        hParserModeFile.selectFirstElementByName("", docTest);
        
    }
    
    @Test
    public void testSelectFirstElementByNameFromDocNotExist() {
        Document docTest = new Document("test doc");
        docTest.appendElement("testName");
        
        assertEquals(hParserModeFile.selectFirstElementByName("testName2", docTest), null);
        
    }
    
    @Test
    public void testSelectFirstElementByNameFromElementValid() {
        Element eleTest = new Element("test ele");
        Element elementTest = eleTest.appendElement("testName");
        
        Element extractedElem = hParserModeFile.selectFirstElementByName("testName", eleTest);
        
        assertEquals(elementTest, extractedElem);
        
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testSelectFirstElementByNameFromElementNullCase() {
        Element eleTest = new Element("test ele");
        eleTest.appendElement("testName");
        
        hParserModeFile.selectFirstElementByName(null, eleTest);
        
    }
    @Test(expected=IllegalArgumentException.class)
    public void testSelectFirstElementByNameFromElementEmptyCase() {
        Element eleTest = new Element("test ele");
        eleTest.appendElement("testName");
        
        hParserModeFile.selectFirstElementByName("", eleTest);
        
    }
    
    @Test
    public void testSelectFirstElementByNameFromElementNotExist() {
        Element eleTest = new Element("test ele");
        eleTest.appendElement("testName");
        
        assertEquals(hParserModeFile.selectFirstElementByName("testName2", eleTest), null);
        
    }

}
