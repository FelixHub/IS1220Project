package myVelib;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import myVelib.Bicycle.Bicycle;
import myVelib.Bicycle.BicycleType;
import myVelib.Bicycle.ElectricBicycle;
import myVelib.Bicycle.MechanicalBicycle;
import myVelib.Card.Card;
import myVelib.Card.VLIBRE_Card;
import myVelib.Misc.GPS;
import myVelib.Misc.Ride;
import myVelib.Misc.User;
import myVelib.PathAlgorithm.AlgType;
import myVelib.PathAlgorithm.PathFinder;
import myVelib.Station.PlusStation;
import myVelib.Station.StandardStation;
import myVelib.Station.Station;
import myVelib.Station.Station.EmptyStationException;
import myVelib.Station.Station.FullStationException;
import myVelib.Station.Station.OffLineStationException;
import myVelib.Station.Station.UserAlreadyHaveBicycleException;
public class Ini {
	
	public static void main(String[] args) throws EmptyStationException, UserAlreadyHaveBicycleException, OffLineStationException, FullStationException, InterruptedException {
		
		int cityDimension = 10;
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("How many stations do you want in the city ?");
		int n = reader.nextInt(); 
		System.out.println("How many parkingSlot by station ?");
		int m = reader.nextInt();
		System.out.println("combien d'usager dans votre ville ?");
		int nbUser = reader.nextInt();
		reader.close();
		
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
		
		for(int i = 0; i<nbUser ; i++) {
			GPS gps = new GPS(cityDimension);
			Card card = Card.randomCard();
			users.add(new User("RandomGuy", gps, card));
		}
		MyVelib myVelib = new MyVelib(stations,users);
		
		for (User user : myVelib.getUsers()) {
			GPS randomGPS = new GPS(cityDimension);
			user.setCurrentRide(new Ride(users.get(0).getPosition(),randomGPS, "MECHANICAL", myVelib, AlgType.SHORTEST));
			user.getCurrentRide().getRidePath().getStartStation().takeBicycle(BicycleType.MECHANICAL, user);
			Thread.sleep(1000);
			displayRide(user.currentRide,user,cityDimension);
			user.getCurrentRide().getRidePath().getEndStation().parkBicycle(user.getCurrentRide().getBicycle(),user);
		}
		
		for(Station s : myVelib.getStations()) {
			System.out.println(s.getID()+" "+s.averageRateOfOccupation()+ "  "+ s.getNbRent() +"  "+ s.getNbReturn());
		}
		
		System.out.println(myVelib.mostUsedStation());
		System.out.println(myVelib.leastOccupiedStation());
		System.out.println(MyVelib.getClock().getTime());
		for(User user: myVelib.getUsers()) {
			System.out.println(user.ridesNb) ;
		}
		
	}
	public static void displayRide(Ride ride,User u, int cityDimension) {
		for(int i = 0; i< cityDimension; i ++) {
			String s = "";
			for(int j = 0; j < cityDimension; j ++) {
				boolean flag = true;
				if ( ( u.getPosition().getX() == j) && (u.getPosition().getY() == i)){
					flag = false;
					s = s + "S  ";
				}
				else if ( (u.getCurrentRide().getEnd().getX() == j) && (u.getCurrentRide().getEnd().getY() == i)){
					flag = false;
					s = s + "E  ";
				}
				else{
					for(Station station : ride.getVelibNW().stations) {
					if ( ( station.getPosition().getX() == j) && (station.getPosition().getY() == i)) {
						if(station.getState()=="OFFSERVICE") {
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
				}
				if (flag) {s = s + "#  ";}
			}
			System.out.println(s);
		}
		System.out.println("startPosition: "+u.getPosition() );
		System.out.println("EndPosition: "+ ride.getEnd() );
		System.out.println("StartStation: "+u.currentRide.getRidePath().getStartStation());
		System.out.println("EndStation: "+ u.currentRide.getRidePath().getEndStation());
		
	}
	
}

