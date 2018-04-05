package myVelib;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import myVelib.Bicycle.Bicycle;
import myVelib.Bicycle.BicycleFactory;
import myVelib.Bicycle.BicycleType;
import myVelib.Bicycle.ElectricBicycle;
import myVelib.Bicycle.MechanicalBicycle;
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
	private int cityDimension;
	private String name;
	/**
	 * 
	 * @param listOfStations
	 * @param listOfUsers
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	
	public static MyVelib myVelibINI(String name) throws FileNotFoundException, IOException {
		
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
		
		return new MyVelib(stations,users,cityDimension,name);
	}
	
	
	public MyVelib(ArrayList<Station> listOfStations,ArrayList<User> listOfUsers, int cityDimension, String name) {
		
		this.name = name;
		this.cityDimension = cityDimension;
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
	
	public static  MyVelib myVelibExample() {
		
		ArrayList<Station> stations = new ArrayList<Station>();
		ArrayList<User> users = new ArrayList<User>();
		BicycleFactory myFactory= new BicycleFactory();
		
		
		GPS GPS1 = new GPS(0,7);
		Station s1 = new StandardStation(GPS1,3);
		s1.getParkingSlots()[0] = new ElectricBicycle();
		s1.getParkingSlots()[1] = new MechanicalBicycle();
		
		GPS GPS2 = new GPS(6,3);
		Station s2 = new StandardStation(GPS2,3);
		s2.getParkingSlots()[0] = new ElectricBicycle();
		s2.getParkingSlots()[1] = new MechanicalBicycle();
		
		GPS GPS3 = new GPS(3,4);
		Station p3 = new PlusStation(GPS3,3);
		p3.getParkingSlots()[0] = new ElectricBicycle();
		p3.getParkingSlots()[1] = new MechanicalBicycle();
		
		GPS GPS4 = new GPS(9,7);
		Station p4 = new PlusStation(GPS4,3);
		p4.getParkingSlots()[0] = new ElectricBicycle();
		p4.getParkingSlots()[1] = new MechanicalBicycle();
		
		stations.add(s1);
		stations.add(s2);
		stations.add(p3);
		stations.add(p4);
		
		return new MyVelib(stations,users,10,"myVelibTest");
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
	public void addUser(User u) {
		users.add(u);
	}
	
	public String getName() {
		return name;
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
	public class StationDoesNotExist extends Throwable {
		
		public StationDoesNotExist(int ID) {
		System.out.println("la station d'ID "+ID+" does not exist in this universe...");
		}
	}
	public Station getStation(int ID) throws StationDoesNotExist {
		boolean flag = false;
		Station ss = null;
		for(Station s : stations) {
			if (s.getID() == ID) {
				flag = true;
				ss =s;
			}
		}
		if (!(flag)) {
			throw new StationDoesNotExist(ID);
			
		}
		return ss;
	}


	public int getCityDimension() {
		return cityDimension;
	}
	
	public void displayState() {
		String s = "";
		boolean flag = true;
		for(int i = 0; i< cityDimension; i ++) {
			s = "";
			for(int j = 0; j < cityDimension; j ++) {
				flag = true;
				for(Station station : stations) {
					if ( ( station.getPosition().getX() == j) && (station.getPosition().getY() == i)) {
						if(station.getState()=="OFFLINE") {
							s = s + "X  ";
						}
						else if (station.type == "PLUS") {
							s = s + station.getID()+"P ";
						}
						else {
							s = s + station.getID()+"N ";
						}
						flag = false;
						break;
						}
				}
				if (flag) {s = s + "#  ";}
			}
			System.out.println(s);
		}
	}
}