package myVelib;

import java.util.ArrayList;

import myVelib.Card.Card;
import myVelib.Station.Station;
import java.lang.Math;


public class MyVelib {

	private ArrayList<Station> stations=new ArrayList<Station>();
	private ArrayList<User> users = new ArrayList<User>();
	Clock clock;
	private double [][] distanceMap;

	public MyVelib(ArrayList<Station> listOfStations,ArrayList<User> listOfUsers) {
		
		this.stations=listOfStations;
		this.users=listOfUsers;
		this.clock=new Clock();
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
		

	
	public void addUser(String name,Card cT) {
		
	}
	public void addStation(String nam) {
		
	}
	


	public ArrayList<Station> getStations() {
		return stations;
	}

}