
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StartingPoint {
	
	/**
	 * Reads data from a file and puts it in a string list
	 * @param path the path of the file to be read
	 * @param encoding the character set the file should be encoded in
	 * @return lines a String<list> representing each line in the document.
	 * */
	public static List<String>  readFile(String path, Charset encoding){
		List<String> lines = new ArrayList<String>();
		
		try{
			 lines= Files.readAllLines(Paths.get(path), encoding);
			}
		
		catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + path + "'");                
        }
		catch(IOException ex) { // default case
			ex.printStackTrace();
		}
		System.out.println("The file at: '" + path + "' has been read");
		
		// Removes blank values from the list. 
	    Iterator<String> i = lines.iterator();
	    while (i.hasNext())
	    {
	        String line = i.next();
	        if (line.trim().isEmpty()) // Removes leading and trailing white spaces, and checks if that element is empty
	            i.remove(); // remove the element 
	    }
	    
		return lines;
	}
	
	public static void main(String[] args) {
		
		String passengerDataPath = "C:\\Users\\ogaga isiavwe\\Desktop\\csv files\\AComp_Passenger_data_no_error.csv";
		String airportDataPath = "C:\\Users\\ogaga isiavwe\\Desktop\\csv files\\Top30_airports_LatLong.csv";
		
		List<String> flightData = new ArrayList<String>();// Stores the data from "AComp_Passenger_data.csv"
		List<String> airportData = new ArrayList<String>(); // Stores the data from "Top30_airports_LatLong"
		
		flightData = readFile(passengerDataPath, StandardCharsets.UTF_8);
		airportData = readFile(airportDataPath, StandardCharsets.UTF_8);
		
		System.out.println("Passenegr Data:");
		for(String data: flightData){
			System.out.println(data);		
		}
		System.out.println("");
		System.out.println("");
		
		System.out.println("Airport Data:");
		for(String data: airportData){
			System.out.println(data);		
		}
	}

}
