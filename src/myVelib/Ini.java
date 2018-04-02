package myVelib;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import myVelib.Bicycle.Bicycle;
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
public class Ini {
	
	public static void main(String[] args) {
		
		int cityDimension = 20;
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
		for(int i = 0; i<m;i++) {
		//System.out.println(stations.get(0).getParkingSlots()[i]);
		}
		MyVelib myVelib = new MyVelib(stations,users);
		
		
		GPS randomGPS = new GPS(cityDimension);
		users.get(0).setCurrentRide(new Ride(users.get(0).getPosition(),randomGPS, "MECHANICAL", myVelib, AlgType.FASTEST));
		User bruce = users.get(0);
		
		for(int i = 0; i< cityDimension; i ++) {
			String s = "";
			for(int j = 0; j < cityDimension; j ++) {
				boolean flag = true;
				if ( ( bruce.getPosition().getX() == j) && (bruce.getPosition().getY() == i)){
					flag = false;
					s = s + "S ";
				}
				else if ( ( randomGPS.getX() == j) && (randomGPS.getY() == i)){
					flag = false;
					s = s + "E ";
				}
				else{
					for(Station station : myVelib.stations) {

					if ( ( station.getPosition().getX() == j) && (station.getPosition().getY() == i)) {
						s = s + station.getID()+" ";
						flag = false;
						break;
						}
					}
				}
				if (flag) {s = s + "# ";}
			}
			System.out.println(s);
		}
		
		System.out.println("startPosition: "+bruce.getPosition() );
		System.out.println("EndPosition: "+ randomGPS );
		System.out.println("StartStation: "+bruce.currentRide.getRidePath().getStartStation());
		System.out.println("EndStation: "+ bruce.currentRide.getRidePath().getEndStation());
	}

}


