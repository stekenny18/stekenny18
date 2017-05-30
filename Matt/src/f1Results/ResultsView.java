package f1Results;

public class ResultsView {
	
public static void main(String args[]){
		
		getHtml result = new getHtml();
		ResultsView res1 = new ResultsView();
		ResultsController cont = new ResultsController(result, res1);
		
		cont.updateView();
	}
	
	public String printResults(String resultType) {
	
		return resultType;
	}


}
