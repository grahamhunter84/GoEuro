package GoEuro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.exc.UnrecognizedPropertyException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class GoEuroTest {

	GoEuroWebService web_service;
	
	@Before
	public void setUp() throws Exception 
	{
		web_service = new GoEuroWebService();
	}

	@After
	public void tearDown() throws Exception 
	{
	}

	@Test
	public void testWebService() throws Exception 
	{
		System.out.println("Testing Web Service - 2 Results");
		ResultsWrapper results_wrapper;
		results_wrapper = web_service.callGetLocationInformation("Potsdam");

		assertNotNull(results_wrapper.getResults());
		assertEquals(2,results_wrapper.getResults().size());		
	}
	
	@Test
	public void testWebServiceNoResult() throws Exception 
	{
		System.out.println("Testing Web Service - No Result");
		ResultsWrapper results_wrapper;
		results_wrapper = web_service.callGetLocationInformation("no result");

		assertNotNull(results_wrapper.getResults());
		assertEquals(0,results_wrapper.getResults().size());	
	}
	
	@Test (expected = NullPointerException.class)
	public void testWebServiceNull() throws Exception 
	{
		  web_service.callGetLocationInformation(null);
	}
	
	@Test
	public void testJSONMap() throws JsonParseException, JsonMappingException, IOException 
	{

		System.out.println("Testing JSON Map");
		ResultsWrapper results_wrapper;
		ObjectMapper mapper = new ObjectMapper();
		results_wrapper = mapper.readValue(SAMPLE_JSON, ResultsWrapper.class);

		assertNotNull(results_wrapper.getResults());
		assertEquals(2,results_wrapper.getResults().size());

		results_wrapper = mapper.readValue(SAMPLE_JSON_2, ResultsWrapper.class);

		assertNotNull(results_wrapper.getResults());
		assertEquals(2,results_wrapper.getResults().size());

	}
	
	@Test (expected = JsonParseException.class)
	public void testJSONMapMalformed() throws JsonParseException, JsonMappingException, IOException 
	{
		System.out.println("Testing Malformed JSON Map");
		ObjectMapper mapper = new ObjectMapper();

		mapper.readValue(SAMPLE_JSON_MALFORMED, ResultsWrapper.class);
	}
	
	@Test (expected = UnrecognizedPropertyException.class)
	public void testJSONMapIncorrectFields() throws JsonParseException, JsonMappingException, IOException 
	{
		System.out.println("Testing Incorrect Fields JSON Map");
		ObjectMapper mapper = new ObjectMapper();

		mapper.readValue(SAMPLE_JSON_INCORRECT_FIELDS, ResultsWrapper.class);
	}
	
	@Test
	public void testCSVWriter() throws JsonParseException, JsonMappingException, IOException 
	{
		try
		{
		System.out.println("Testing CSV Writer");
		ObjectMapper mapper = new ObjectMapper();

		ResultsWrapper results_wrapper = mapper.readValue(SAMPLE_JSON, ResultsWrapper.class);
		CSVWriterUtil.writeCSV(results_wrapper.getResults(),"testcsv1.csv");	
		
		results_wrapper = mapper.readValue(SAMPLE_JSON_2, ResultsWrapper.class);
		CSVWriterUtil.writeCSV(results_wrapper.getResults(),"testcsv2.csv");	
		
		CSVWriterUtil.writeCSV(null,"testcsvblank.csv");	
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) 
	{
		Result result = JUnitCore.runClasses(GoEuroTest.class);
		System.out.println(result.getFailures().size()+" Failures");
		for (Failure failure : result.getFailures()) 
		{
			
			System.out.println(failure.toString());
		}
	}
		

	
	private String SAMPLE_JSON = "{"+
			"\"results\" : [ {"+
			"\"_type\" : \"Position\","+
			"\"_id\" : 410978,"+
			"\"name\" : \"Potsdam, USA\","+
			"\"type\" : \"location\","+
				"\"geo_position\" : {"+
				"\"latitude\" : 44.66978,"+
				"\"longitude\" : -74.98131"+
				"}"+
			"}, {"+
			"\"_type\" : \"Position\","+
			"\"_id\" : 377078,"+
			"\"name\" : \"Potsdam, Deutschland\","+
			"\"type\" : \"location\","+
			"\"geo_position\" : {"+
			"\"latitude\" : 52.39886,"+
			"\"longitude\" : 13.06566"+
			"}"+
			"} ]"+
			"}";   
	
	
	private String SAMPLE_JSON_2 = "{"+
			"\"results\" : [ {"+
			"\"_type\" : \"Position\","+
			"\"_id\" : 410978,"+
			"\"type\" : \"location\","+
				"\"geo_position\" : {"+
				"\"latitude\" : 44.66978,"+
				"\"longitude\" : -74.98131"+
				"}"+
			"}, {"+
			"\"_type\" : \"Position\","+
			"\"_id\" : 377078,"+
			"\"name\" : \"Potsdam, Deutschland\","+
			"\"type\" : \"location\""+
			"} ]"+
			"}";   
	
	private String SAMPLE_JSON_INCORRECT_FIELDS = "{"+
			"\"results\" : [ {"+
			"\"_type\" : \"Position\","+
			"\"_id\" : 410978,"+
			"\"name\" : \"Potsdam, USA\","+
			"\"error\" : \"location\","+
			"}, {"+
			"\"_type\" : \"Position\","+
			"\"_id\" : 377078,"+
			"\"error2\" : \"Potsdam, Deutschland\","+
			"\"type\" : \"location\","+
			"\"geo_position\" : {"+
			"\"latitude\" : 52.39886,"+
			"\"error3\" : 13.06566"+
			"}"+
			"} ]"+
			"}";   
	
	private String SAMPLE_JSON_MALFORMED = "{"+
			"\"results\" : [ {"+
			"\"_type\" : \"Position\","+
			"\"_id\" : 410978,"+
			"\"name\" : \"Potsdam, USA\","+
			"\"type\" : \"location\","+
			"}, {"+
			"\"_type\" : \"Position\","+
			"\"_id\" : 377078,"+
			"\"name\" : \"Potsdam, Deutschland\","+
			"\"type\" : \"location\","+
			"\"geo_position\" : {";
}
