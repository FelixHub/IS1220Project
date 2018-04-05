package myVelib;

import java.util.ArrayList;
import java.util.Scanner;

import myVelib.MyVelib.StationDoesNotExist;
import myVelib.Station.Station;

public class controls_CLUI {
	
	public ArrayList<MyVelib> velibnetworks;
	
	
	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);
		System.out.println("Welcome to the my_velib CLUI !");
		System.out.println("Enter your commands here. Enter help for informations about the commands");
		System.out.println("To end the simulation, enter exit. Be careful with uppercases...");
		String commande = "";
		while (!((commande = reader.next()).equalsIgnoreCase("exit"))) {
			
			switch(commande) {
				
			case "setup": 
			
			case "adduser":
				
			case "offline": String velibnetworkName = reader.next();
							int stationID = Integer.parseInt(reader.next());
							MyVelib my_velib = getVelibnetwork(velibnetworkName);
							Station station = my_velib.getStation(stationID);
			case "online":
				
			case "rentBike":
				
			case "returnBike":
				
			case "displayStation":
				
			case "sortStation":
				
			case "display":
				
			case "askForRidePlan":
				
				
				
			}
			
			}
			
			System.out.println( reader.next());
		}
	
	
	public StationDoesNotExist(int ID) {
	System.out.println("la station d'ID "+ID+"does not exist in this universe...");
	}

	public MyVelib getMyVelib(String name) throws NetworkDoesNotExist {
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

