
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StartingPoint {
	
	public static List<String>  readFile(String path, Charset encoding){// Reads data from a file and puts it in a string list
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
		return lines;
	}
	
	public static void main(String[] args) {
		
		String path = "C:\\Users\\ogaga isiavwe\\Desktop\\csv files\\AComp_Passenger_data_no_error.csv";
		List<String> flightData = new ArrayList<String>();// Stores the data from "AComp_Passenger_data.csv"
		
		flightData = readFile(path, StandardCharsets.UTF_8);
		
		for(String data: flightData){
			System.out.println(data);		
		}
	}

}
