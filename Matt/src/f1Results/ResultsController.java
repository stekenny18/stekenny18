package f1Results;

import java.util.ArrayList;

import com.google.gson.Gson;

public class ResultsController {
	
	getHtml test;
	private ResultsView view;

	public ResultsController(getHtml test, ResultsView view) {
		this.test = new getHtml();
		this.view = view;
	}
	
	public String getDrivers() {

		ArrayList<Results> driver = new ArrayList<Results>();
		driver.addAll(test.retrieveDriverResults());
		
		String json = new Gson().toJson(driver);
		System.out.println(json);
		return json;
	}
	
	public String getTeams() {
		
		ArrayList<Results> team = new ArrayList<Results>();
		team.addAll(test.retrieveTeamResults());
		
		String json = new Gson().toJson(team);
		System.out.println(json);
		return json;
	}
	
	public void updateView() {
		view.printResults(getDrivers());
		view.printResults(getTeams());
	}

}
