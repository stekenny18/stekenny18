package f1resultsparser;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Class for the parsing of HTML pages allowing for data to be scraped
 * from websites.
 * Requires Jsoup library
 * @author Yat Cheung
 *
 */

public class HTMLParser {
	/**
	 * Class constructor
	 */
	public HTMLParser() {
		
	}
	
	/**
	 * Loads and parses a file on disk containing HTML
	 * 
	 * @param path			the String containing the path to the HTML file to be loaded and parsed
	 * @return doc			the Document object of the parsed HTML file
	 * @throws IOException  if an error occurs whilst loading the file
	 */
	Document loadFromFile(String path) throws IOException {
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
	Document loadFromUrl(String urlString) throws IOException {
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("jdw_websense.jdwilliams.local", 8080));

        return Jsoup.connect(urlString).proxy(proxy).get();

	}
}
