package f1resultsparser;

import java.util.List;
import com.google.gson.Gson;

public class JSONParser {
	JSONParser() {
		
	}
	
	<T> String buildJson(List<T> list) {
		return new Gson().toJson(list);
		
	}
}
