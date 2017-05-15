package f1resultsparser.Controller;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Class for the parsing of HTML pages allowing for data to be scraped
 * from websites.
 * Requires Jsoup library
 * 
 * @author Yat Cheung
 *
 */

public class HTMLParser {
    public static final String fileString = "file";
    public static final String urlString = "url";
    public Document doc;
    private String location;
    private String mode;

 /**
  * Class Constructor
  * @param location     a String containing the URL/file path of the HTML file to be parsed
  * @param mode         a String to indicate whether we are loading from a URL or file path. Accepts only "file" or "url" as argument (case-sensitive)
  */
	public HTMLParser(String location, String mode) {
	    this.setMode(mode);
	    this.setLocation(location);
	}
	
	/**
	 * Method to return a Document object of the HTML file
	 * @return Document    object of the HTML file
	 * @throws IOException     if there was an error opening the HTML
	 * @throws IllegalArgumentException    if mode is anything other than "file" or "url"
	 */
	
	public Document loadHTML() throws IOException
	{

	    if (fileString.equals(mode)) {
	        doc = loadFromFile(location);
	    }

	    if (urlString.equals(mode)) {
	        doc = loadFromUrl(location);
	    }

	    return doc;
	}
	
	/**
	 * Loads and parses a file on disk containing HTML
	 * 
	 * @param path			the String containing the path to the HTML file to be loaded and parsed
	 * @return doc			the Document object of the parsed HTML file
	 * @throws IOException  if an error occurs whilst loading the file
	 */
	public Document loadFromFile(String path) throws IOException {
		File input = new File(path);
		return Jsoup.parse(input, "UTF-8");
	}
	
	/**
	 * Fetches and parses a HTML document from the web
	 * 
	 * @param url			the String containing the URL to the HTML file to be loaded and parsed
	 * @return doc			the Document object of the parsed HTML file
	 * @throws IOException  if an error occurs whilst fetching the URL
	 */
	public Document loadFromUrl(String urlString) throws IOException {
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("jdw_websense.jdwilliams.local", 8080));

        return Jsoup.connect(urlString).proxy(proxy).get();

	}
	
	/**
	 * Method to select an Element from a Document by name and return it.
	 * @param elementName  a String of the name of the Element being looked for
	 * @param from         the Document in which we are looking for the Element
	 * @return             the first Element encountered in the Document with the provided name
	 */
	public Element selectFirstElementByName(String elementName, Document from) throws IllegalArgumentException {
	    return from.select(elementName).first();
	}
	
	   /**
     * Method to select an Element from an Element by name and return it.
     * @param elementName   a String of the name of the Element being looked for
     * @param from          the Element in which we are looking for the Element
     * @return              the first Element encountered in the Element with the provided name
     */
    
	
	public Element selectFirstElementByName(String elementName, Element from) throws IllegalArgumentException {
        return from.select(elementName).first();
    }
	
	/**
	 * Setter for class constructor
	 * Ensures only "file" or "url" is accepted as arguments
	 * @param mode         String mode to set the class' mode field to
	 * @throws IllegalArgumentException if anything other than "file" or "url" is provided as an argument
	 */
	private void setMode(String mode) throws IllegalArgumentException {

	    if (fileString.equals(mode) || urlString.equals(mode)) {
	        this.mode = mode;
	    } else {
	        throw new IllegalArgumentException();
	    }
	}

	/**
	 * Setter for class constructor
	 * @param location
	 */
	
	private void setLocation(String location) {
	    this.location = location;
	}
	
	
}
