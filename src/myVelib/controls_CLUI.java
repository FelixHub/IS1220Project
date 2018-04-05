package myVelib;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import myVelib.MyVelib.StationDoesNotExist;
import myVelib.Station.Station;

public class controls_CLUI {
	
	public ArrayList<MyVelib> velibnetworks;
	
	
	public static void main(String[] args) throws NetworkDoesNotExist, StationDoesNotExist, FileNotFoundException, IOException {
		controls_CLUI basics = new controls_CLUI();
		Scanner reader = new Scanner(System.in);
		System.out.println("Welcome to the my_velib CLUI !");
		System.out.println("Enter your commands here. Enter help for informations about the commands");
		System.out.println("To end the simulation, enter exit. Be careful with uppercases...");
		String commande = "";
		while (!((commande = reader.next()).equalsIgnoreCase("exit"))) {
			
			switch(commande) {
			
			case "importFile":
				
			case "setup": String name = reader.next();
						  basics.velibnetworks.add( MyVelib.myVelibINI(reader.next()));
			
			case "adduser":
				
			case "offline": String velibnetworkName = reader.next();
							int stationID = Integer.parseInt(reader.next());
							MyVelib my_velib = basics.getMyVelib(velibnetworkName);
							Station station = my_velib.getStation(stationID);
							station.putOffLine();
							System.out.println("the station"+stationID+"of network"+velibnetworkName+"is now offline");
							
			case "online":	String velibnetworkName1 = reader.next();
							int stationID1 = Integer.parseInt(reader.next());
							MyVelib my_velib1 = basics.getMyVelib(velibnetworkName1);
							Station station1 = my_velib1.getStation(stationID1);
							station1.putOnline();
							System.out.println("the station"+stationID1+"of network"+velibnetworkName1+"is now online");
				
			case "rentBike":
				
			case "returnBike":
				
			case "displayStation":
				
			case "sortStation":
				
			case "display":
				
			case "askForRidePlan":
				
				
				
			}
			
			}
			
			System.out.println("goodbye !");
		}
	
	
public class NetworkDoesNotExist extends Throwable {
		
		public NetworkDoesNotExist(String name) {
		System.out.println("la station d'ID "+name+"does not exist in this universe...");
		}
	}

	public MyVelib getMyVelib(String name) throws NetworkDoesNotExist{
		boolean flag = false;
		MyVelib ss = null;
		for(MyVelib mv : velibnetworks) {
			if (mv.getName() == name) {
				flag = true;
				ss = mv;
			}
		}
		if (!(flag)) {
			throw new NetworkDoesNotExist(name);
		}
		return ss;
	}

}

