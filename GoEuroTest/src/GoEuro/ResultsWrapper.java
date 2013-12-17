package GoEuro;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ResultsWrapper {

	private List<Location> results;
	
	ResultsWrapper()
	{
		results = new ArrayList<Location>();
	}

	@JsonProperty("results")
	public List<Location> getResults() 
	{
		return results;
	}

	@JsonProperty("results")
	public void setResults(List<Location> results) 
	{
		this.results = results;
	}
}
