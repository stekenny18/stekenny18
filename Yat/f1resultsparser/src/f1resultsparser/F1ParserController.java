/**
 * 
 */
package f1resultsparser;

import java.io.IOException;

/**
 * Main class. 
 * Application for output of top 5 teams or top 10 drivers depending on what the user inputs.
 * Output is a file "results.json" with json String of requested results, as well as being printed in the console.
 * @author Yat Cheung
 *
 */
public class F1ParserController {
    
    public static void main(String[] args) throws IOException {
        String url = "http://www.f1-fansite.com/f1-results/2015-f1-results-standings/";
        String path = "src/f1resultsparser/2015F1.html";
        String mode = "";
        String location = "";

        F1ResultsView view = new F1ResultsView();
        view.displayTaskMenu();
        int option = view.receiveInput();
        
        view.displayModeMenu();
        int modeInt = view.receiveInput();
        
        if (modeInt == 1) {
            mode = "file";
            location = path;
        }
        
        if (modeInt == 2) {
            mode = "url";
            location = url;
        }
        
        
        F1Parser fParser = new F1Parser(location, mode);
        view.displayResults(fParser.returnResultsOfOption(option));
    }
}
