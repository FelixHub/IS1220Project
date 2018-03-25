package myVelib;

import java.util.ArrayList;

import myVelib.Card.Card;
import myVelib.Station.Station;
import java.lang.Math;
/**
 * this is the main class of the program, which has the stations, users and the central Clock system as attributes
 * 
 *
 */

public class MyVelib {

	private ArrayList<Station> stations=new ArrayList<Station>();
	private ArrayList<User> users = new ArrayList<User>();
	private static MyClock clock;
	private double [][] distanceMap;
	

	public MyVelib(ArrayList<Station> listOfStations,ArrayList<User> listOfUsers) {
		
		this.stations=listOfStations;
		this.users=listOfUsers;
		this.clock =new MyClock();
		this.distanceMap= new double [stations.size()][stations.size()];
		int i = 0;
		int j = 0;
		for (Station si : stations)  {
			for (Station sj : stations) {
				distanceMap[i][j] = Math.sqrt((si.getPosition().getX()-sj.getPosition().getX())^2+(si.getPosition().getY()-sj.getPosition().getY())^2);
				j++;
			}
			i++;
		}
	}		
	public double[][] getDistanceMap() {
		return distanceMap;

	}
	

	
	public ArrayList<User> getUsers() {
		return users;
	}
	public static MyClock getClock() {
		return clock;
	}
	public void addUser(String name,Card cT) {
		
	}
	public void addStation(String nam) {
		
	}
	


	public ArrayList<Station> getStations() {
		return stations;
	}
	
	public int mostUsedStation() {
		int maxID=0;
		int max = 0;
		for(Station station : stations ) {
			if (station.getNbRent()+station.getNbReturn() > max ) {
				maxID = station.getID();
				max = station.getNbRent()+station.getNbReturn();
			}
		}
		
		return maxID;
	}
	public int leastOccupiedStation() {
		int leastID = 0;
		float least = 1;
		for(Station station : stations ) {
			if(station.averageRateOfOccupation() < least) {
				leastID = station.getID();
				least = station.averageRateOfOccupation();
			}
		}
		
		return leastID;
	}

}