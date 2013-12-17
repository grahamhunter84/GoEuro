package GoEuro;

import org.codehaus.jackson.annotate.JsonProperty;

public class Location {

	private String _type;
	private String _id;

	private String name;
	private String type;
	
	private GeoPosition geo_position;

	public String[] toCSV()
	{
		String [] csv_array = new String[NUM_CSV_PARAMS];
		
		int index = 0;
		csv_array[index++] = _type;
		csv_array[index++] = _id;
		csv_array[index++] = name;
		csv_array[index++] = type;
		csv_array[index++] = geo_position != null ? geo_position.getLatitude() : "";
		csv_array[index++] = geo_position != null ? geo_position.getLongitude(): "";
		return csv_array;
	}
	
	@JsonProperty("_type")
	String get_Type() 
	{
		return _type;
	}

	@JsonProperty("_type")
	void set_Type(String _type) 
	{
		this._type = _type;
	}

	@JsonProperty("_id")
	String get_Id() 
	{
		return _id;
	}

	@JsonProperty("_id")
	void set_Id(String _id) 
	{
		this._id = _id;
	}

	String getName() 
	{
		return name;
	}

	void setName(String name)
	{
		this.name = name;
	}

	String getType() 
	{
		return type;
	}

	void setType(String type) 
	{
		this.type = type;
	}

	@JsonProperty("geo_position")
	private GeoPosition getGeo_position() 
	{
		return geo_position;
	}

	@JsonProperty("geo_position")
	private void setGeo_position(GeoPosition geo_position) 
	{
		this.geo_position = geo_position;
	}
	
	private static final int NUM_CSV_PARAMS = 6;
}
