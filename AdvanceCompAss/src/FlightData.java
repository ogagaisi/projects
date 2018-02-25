import java.util.ArrayList;
import java.util.List;

public class FlightData {
	private List<String> passengerID;
	private String flightID;
	private String fromAirport;
	private String destination;
	private int departureTime;
	private int totalFlightTime;
	
	public FlightData(){
		passengerID = new ArrayList<>();
		flightID = "";
		fromAirport = "";
		destination = "";
		departureTime = 0;
		totalFlightTime = 0;				
	}
	
	public FlightData( List<String> pID, String fID, String from, String to, int time, int fTime){
		passengerID = pID;
		flightID = fID;
		fromAirport = from;
		destination = to;
		departureTime = time;
		totalFlightTime = fTime;				
	}
	
	public void display(){
		System.out.println("[ Passenegr ID: " + passengerID +", flightID: " + flightID +", fromAirport: " + fromAirport + ", destination: " + destination + ", departureTime: " + departureTime +", totalFlightTime: " + totalFlightTime +"]");
	}

	public List<String> getPassengerID() {
		return passengerID;
	}

	public String getFlightID() {
		return flightID;
	}

	public String getFromAirport() {
		return fromAirport;
	}

	public String getDestination() {
		return destination;
	}

	public int getDepartureTime() {
		return departureTime;
	}

	public int getTotalFlightTime() {
		return totalFlightTime;
	}

	public void setPassengerID(String passengerID) {
		this.passengerID.add(passengerID);
	}

	public void setFlightID(String flightID) {
		this.flightID = flightID;
	}

	public void setFromAirport(String fromAirport) {
		this.fromAirport = fromAirport;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = Integer.parseInt(departureTime);
	}

	public void setTotalFlightTime(String  totalFlightTime) {
		this.totalFlightTime = Integer.parseInt(totalFlightTime) ;
	}
	
}
