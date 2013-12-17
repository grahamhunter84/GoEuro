package GoEuro;

import java.util.List;

public class GoEuro {

	public static void main(String[] args) {
		
		if(args.length == 0)
		{
			System.out.println("Missing parameter. Usage is java -jar GoEuroTest.jar <STRING>");
			return;
		}
		
		String name = args[0];
		try 
		{
			//Call the web service
			System.out.println("Querying for:"+name);
			GoEuroWebService web_service = new GoEuroWebService();
			ResultsWrapper results_wrapper = web_service.callGetLocationInformation(name);
			List<Location> results = results_wrapper.getResults();
			System.out.println(results.size()+" results returned");
			
			//Output results to CSV
			CSVWriterUtil.writeCSV(results,CSV_FILE_NAME);	
			System.out.println("Results outputted to CSV file:"+ CSV_FILE_NAME);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

	private final static String CSV_FILE_NAME = "location_results.csv";
	
}
