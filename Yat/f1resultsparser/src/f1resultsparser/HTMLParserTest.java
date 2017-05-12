package f1resultsparser;

import static org.junit.Assert.*;

import java.io.IOException;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

public class HTMLParserTest {
	HTMLParser hParser;
	@Before
	public void setUp() throws Exception {
		hParser = new HTMLParser();
	}

	@Test
	public void testLoadFromFile() {
		try {
			Document d = hParser.loadFromFile("src/f1resultsparser/2015F1.html");
			assertThat(d, instanceOf(Document.class));
		}
		
		catch(IOException e) {
			fail("File not found?");
		}
		
		try {
			hParser.loadFromFile("failcase");
			fail("IOException expected");
		}
		
		catch (IOException e) {
			
		}
	}

	@Test
	public void testLoadFromValidUrl() {
		try {
			Document d = hParser.loadFromUrl("http://www.f1-fansite.com/f1-results/2015-f1-results-standings/");
			assertThat(d, instanceOf(Document.class));
		}
		
		catch (IOException e) {
			fail("Exception but URL valid");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testLoadFromInvalidUrl() throws IOException {
		hParser.loadFromUrl("www.iydaggdyuga.com");
	}

}
