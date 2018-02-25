
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class StartingPoint {
	
	/**
	 * Reads data from a file and puts it in a string list
	 * @param path the path of the file to be read
	 * @param encoding the character set the file should be encoded in
	 * @param lines a String<list> representing each line in the document.
	 * @param structuredData a List<List<String>> which stores all the elements in each line
	 * */
	public static List<List<String>>  readFile(String path, Charset encoding){
		List<String> lines = new ArrayList<String>();
		List<List<String>> structuredData = new ArrayList<>();
		
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
	        if (line.trim().isEmpty()) {// Removes leading and trailing white spaces, and checks if that element is empty
	            i.remove(); // remove the element
	        }
	        else{
	        	structuredData.add(new ArrayList<>(Arrays.asList(line.split(",")))); // Splits line into a string array e.g "1,2,3" --> ["1", "2", "3"], Arrays.asList() converts the array into an unmodifiable List, and new ArrayList<>() ensures the list is modifiable
	        	i.remove(); // Memory management 
	        }
	        
	    }
	     	    		
		return structuredData;
	}
	
	/**
	 * The Map function 
	 * @param flightData a list of lists representing the data in the file*/
	public static List<FlightData> mapper( List<List<String>> flightData){
		
		
		boolean thereIsRefId = false; // keeps track if something has been stored in reference ID
		FlightData fData = new FlightData();  // A reference of the FlightData class
		List<FlightData> outPutList = new ArrayList<>();
		String referenceID = "";
		
		for (int i = 0; i < flightData.size(); i++){ // Main loop, each element here represents a line in the file
			
			for (int j = 0; j < flightData.get(i).size(); j++){ // Loops through each comma separated value in a line

						 
				 if (thereIsRefId){
				 
					 if (flightData.get(i).get(1).equals(referenceID)){ // the current ID is the same as the reference
						 // current flight id == referenceID
						 switch(j){
						 	case 0:
						 		// passenger ID: List
						 		fData.setPassengerID(flightData.get(i).get(j));
						 		break;
						 		
						 	case 1:
						 		// Flight ID:
						 		fData.setFlightID(flightData.get(i).get(j));
						 		break;
						 		
						 	case 2:
						 		// fromAirort
						 		fData.setFromAirport(flightData.get(i).get(j));						 		
						 		break;
						 		
						 		
						 	case 3:
						 		// destination Airport
						 		fData.setDestination(flightData.get(i).get(j));
						 		break;
						 		
						 	case 4:
						 		// departure time
						 		fData.setDepartureTime(flightData.get(i).get(j));
						 		break;
						 	
						 	case 5:
						 		// total flight time
						 		fData.setTotalFlightTime(flightData.get(i).get(j));
						 		break;
						 	
						 	default:
						 		
						 		
						 }
						 
					 }
					 else{
						//No more data for old flight ID
						 outPutList.add(fData);
						 referenceID = flightData.get(i).get(1);
						 fData = new FlightData();
						 fData.setPassengerID(flightData.get(i).get(j));
					 }

					 
				 }
				 else{
					 // This is the first flight ID
					 //get flightID and store it
					 referenceID = flightData.get(i).get(1); // flightID is in the second element (index:1)
					 thereIsRefId = true;
					 fData.setPassengerID(flightData.get(i).get(j));


				 }
			}	
		}
		
		fData.display();
		
		 return new ArrayList<FlightData>();
	}
	
	public static void main(String[] args) {
		
		String passengerDataPath = "C:\\Users\\ogaga isiavwe\\Desktop\\csv files\\AComp_Passenger_data_no_error.csv";
		String airportDataPath = "C:\\Users\\ogaga isiavwe\\Desktop\\csv files\\Top30_airports_LatLong.csv";
		List<FlightData> mapperOutput = new ArrayList<>();
		List<List<String>> flightData = new ArrayList<>();// Stores the data from "AComp_Passenger_data.csv"
		List<List<String>> airportData = new ArrayList<>(); // Stores the data from "Top30_airports_LatLong"

		
		flightData = readFile(passengerDataPath, StandardCharsets.UTF_8);
		airportData = readFile(airportDataPath, StandardCharsets.UTF_8);
		
		mapper(flightData);
		/*for(List<String> data: flightData){
			System.out.println(data);		
		}*/
		
	}

}
