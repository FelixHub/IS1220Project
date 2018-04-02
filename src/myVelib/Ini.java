package myVelib;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import myVelib.Bicycle.Bicycle;
import myVelib.Bicycle.ElectricBicycle;
import myVelib.Bicycle.MechanicalBicycle;
import myVelib.Card.Card;
import myVelib.Card.VLIBRE_Card;
import myVelib.Misc.GPS;
import myVelib.Misc.User;
import myVelib.Station.PlusStation;
import myVelib.Station.StandardStation;
import myVelib.Station.Station;
public class Ini {
	
	public static void main(String[] args) {

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
			GPS gps = new GPS(1000);
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
			GPS gps = new GPS(1000);
			Card card = Card.randomCard();
			users.add(new User("RandomGuy", gps, card));
		}
		MyVelib myVelib = new MyVelib(stations,users);
		System.out.println(myVelib.getStations());
	}

}
