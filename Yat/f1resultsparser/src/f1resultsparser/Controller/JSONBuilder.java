package f1resultsparser.Controller;

import java.util.List;
import com.google.gson.Gson;

/**
 * A simple class for building json from a list of objects
 * @author Yat Cheung
 *
 */

public class JSONBuilder {
	
    /**
     * Method for building a json string from provided List of objects
     * @param list      List containing objects to be output in json format
     * @return          a String of the objects in the list in json format.
     */
	public <T> String buildJson(List<T> list) {
		return new Gson().toJson(list);
	}
}
