package GoEuro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriterUtil {

	public static void writeCSV(List<Location> results,String file_name)
	{
		BufferedWriter writer;
		try 
		{
			//Even if there are no results we want an empty file to be created.
			File file = new File(file_name);  
			file.createNewFile();
			writer = new BufferedWriter(new FileWriter(file));

			if(results != null)
			{
				for(Location result: results)
				{
					String[] entries = result.toCSV();
					for(int i=0; i < entries.length; i++)
					{
						String value = entries[i];
						
						if(value != null)
						{
							value.replace("\"","\"\"");
							
							//If the value contains a ',' then we need to enclose it with "
							if(value.contains(","))
							{
								writer.write("\"");
							}
							writer.write(value);
							
							if(value.contains(","))
							{
								writer.write("\"");
							}
						}
						else
						{
							writer.write(" ");
						}
						
						if( i!= entries.length-1)
						{
							writer.write(", ");
						}
						
					}
					writer.newLine();
				}  

			} 
			
			writer.close();
		}	
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
