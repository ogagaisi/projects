
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
						 //fData.display();
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
		
		
		
		
		outPutList.add(fData);

		
		return outPutList;
	}
	
	/**
	 * This handles error detection and correction
	 * */
	public static void errorCorrect(List<List<String>>in){
		 
		 String zero[] = {"0","0","0","0","0","0"}; 
		 String empty[] ={"","","","","",""};
		 String emptyAndZero[] ={"","","","","0","0"};
		 
		 
		for(int i = in.size() -1 ; i >= 0 ; i--){
			
			 if(in.get(i).isEmpty() || in.get(i).equals(Arrays.asList(emptyAndZero)) || in.get(i).equals(Arrays.asList(zero)) || in.get(i).equals(Arrays.asList(empty))){//Blank rows */
				 System.out.println("A blank row was detected and removed: " + in.get(i));				 
				 in.remove(i);
			 }
			 
			 else if(in.get(i).get(0).isEmpty()){// passenger id is empty 
				 System.out.println("A row with no passanger ID has been found: and removed" + in.get(i));
				 in.remove(i);
			 }
			 else{
			 }
			 
		}				 		
		
		in.sort((l1, l2) -> l1.get(1).compareTo(l2.get(1)));
	}
	 
	public static List<List<String>> reducer(List<FlightData> fData){
		List<List<String>> outPut = new ArrayList<>();
		List<String> fIDPassenger = new ArrayList<>(); // The Flight ID and its corresponding number of passenegrs 
		
		for(FlightData data: fData){
			 fIDPassenger.add(data.getFlightID());
			 fIDPassenger.add(""+ data.getPassengerID().size());
			 outPut.add(fIDPassenger);
			 fIDPassenger = new ArrayList<>(); // Java acceses variables by reference, this ensures new sets of Flight data are not put in the same variable 
		}
		
		return outPut;
				
		
	}
	
	public static void createReducerOutPut(List<List<String>> input){
		
		Html html = new Html();
		html.addLines(new String[]{"<html>",
		"<head>",
		"<title>Reducer Output</title>",
		"<style type='text/css'>",
		"body {",
		"font-family: arial;",
		"}", 
		"table, th, td {",
		    "border: 1px solid black;",
	    "border-collapse: collapse;",
		"}",
		"th, td {",
	    "padding: 5px;",
	    "text-align: left;",
		"}",
		"</style>",
		"</head>",
		"<body>",
		"<h1 style='text-align:center'>Reducer's Output</h1>",
		"<table style='margin: 0 auto'>",
		  "<tr>",
		    "<th style ='width:50px'>No.</th>",
		    "<th style ='width:250px'>Flight ID</th>",
		    "<th style ='width:250px'>Number of Passengers</th>",
		  "</tr>"
		});
		 for(int i = 0; i<input.size(); i++){
			    html.addLines(new String[]{"<tr>",
			    "<td>"+(i+1)+"</td>",
			    "<td>"+input.get(i).get(0)+"</td>",// Flight ID
			    "<td>"+input.get(i).get(1)+"</td>", // Number of passengers
			    "</tr>"});
			  }
		html.addLines(new String[]{
			"</table>",
			"</body>",
			"</html>"
		});
		
		Path file = Paths.get("Output\\reducerOutput.html");
		try {
			Files.write(file, html.getCodeList(), Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		
		String passengerDataPath = "csv files\\AComp_Passenger_data_no_error.csv";
		String airportDataPath = "csv files\\Top30_airports_LatLong.csv";
		List<FlightData> mapperOutput = new ArrayList<>();
		List<List<String>> flightData = new ArrayList<>();// Stores the data from "AComp_Passenger_data.csv"
		List<List<String>> airportData = new ArrayList<>(); // Stores the data from "Top30_airports_LatLong"

		
		flightData = readFile(passengerDataPath, StandardCharsets.UTF_8);
		airportData = readFile(airportDataPath, StandardCharsets.UTF_8);
		
		errorCorrect(flightData);
		
		
		
		
		System.out.println("Structure of the file after error correction and sorting: "); // --> write to a file
		for(List<String> data: flightData){
			System.out.println(data);		
		}
		 System.out.println("");
		 System.out.println("");
		 
		
		 mapperOutput = mapper(flightData);		 
		 
		System.out.println("Mappers output: ");  // --> write to a file
		 for(FlightData data: mapperOutput){
				data.display();		
		}
			 System.out.println("");
			 System.out.println("");
		
		List<List<String>> redOut =  reducer(mapperOutput);
		createReducerOutPut(redOut);
		System.out.println("reducer's output: ");  // --> write to a file
		 for(List<String> data: redOut){
			 System.out.println(data);	
		}
			 
		 
		
	}

}
