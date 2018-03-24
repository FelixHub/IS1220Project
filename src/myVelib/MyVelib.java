package myVelib;

import java.util.ArrayList;

import myVelib.Card.Card;
import myVelib.Station.Station;

public class MyVelib {
	
	private ArrayList<Station> stations=new ArrayList<Station>();
	private ArrayList<User> users = new ArrayList<User>();
	MyClock clock;
	float [][] DistanceMap;

	public MyVelib(ArrayList<Station> listOfStations,ArrayList<User> listOfUsers) {
		
		this.stations=listOfStations;
		this.users=listOfUsers;
		this.clock=new MyClock();
		this.DistanceMap= new float [stations.size()][stations.size()];
		for (Station i : stations) {
			for (Station j : stations) {
				DistanceMap[i][j] = i.position.
			}
			
			
		}
		
		
		
	}
	// on va faire une map de 100*100 je pense, à préciser dès le début
	public void addUser(String name,Card cT) {
		
	}
	public void addStation(String nam) {
		
	}
	


	public ArrayList<Station> getStations() {
		return stations;
	}

}