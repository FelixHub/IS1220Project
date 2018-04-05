package myVelib;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import myVelib.Bicycle.Bicycle;
import myVelib.Card.Card;
import myVelib.Misc.GPS;
import myVelib.Misc.MyClock;
import myVelib.Misc.User;
import myVelib.Station.PlusStation;
import myVelib.Station.StandardStation;
import myVelib.Station.Station;
import myVelib.Station.StationFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.Math;
/**
 * this is the main class of the program, which has the stations, users and the central Clock system as attributes
 * 
 *
 */

public class MyVelib {

	ArrayList<Station> stations=new ArrayList<Station>();
	private ArrayList<User> users = new ArrayList<User>();
	private static MyClock clock;
	private double [][] distanceMap;
	
	/**
	 * 
	 * @param listOfStations
	 * @param listOfUsers
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	
	public MyVelib myVelibINI() throws FileNotFoundException, IOException {
		
		final Properties prop = new Properties();
		prop.load(new FileInputStream("eval/my_velib.ini"));
		int cityDimension = Integer.parseInt(prop.getProperty("cityDim"));
		int n = Integer.parseInt(prop.getProperty("stationsNumber"));
		int m = Integer.parseInt(prop.getProperty("parkingSlotNumberByStation"));
		 
		ArrayList<Station> stations = new ArrayList<Station>();
		ArrayList<User> users = new ArrayList<User>();
		
		for(int i = 0; i<n;i++) {
			GPS gps = new GPS(cityDimension);
			Random rn = new Random();
			int randomNum = rn.nextInt(2);
			Station s;
			if (randomNum == 0) {
				s = new StandardStation(gps,m) ;
			}
			else{
				s = new PlusStation(gps,m);
			}
			for(int j = 0;j<m;j++) {
				Random rd = new Random();
				int randomNum1 = rd.nextInt(10);
				if ( randomNum1 < 8 ) {
					s.getParkingSlots()[j] = Bicycle.randomBicycle();
				}
			}
			stations.add(s);
		}
		
		return new MyVelib(stations,users);
	}
	
	
	public MyVelib(ArrayList<Station> listOfStations,ArrayList<User> listOfUsers) {
		
		this.stations=listOfStations;
		this.users=listOfUsers;
		this.clock =new MyClock();
		this.distanceMap= new double [stations.size()][stations.size()];
		int i = 0;
		int j = 0;
		for (Station si : stations)  {
			for (Station sj : stations) {
				distanceMap[i][j] = Math.sqrt( Math.pow(si.getPosition().getX()-sj.getPosition().getX(),2)
									+ Math.pow(si.getPosition().getY()-sj.getPosition().getY(), 2) );
			j++;
			}
			j = 0;
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