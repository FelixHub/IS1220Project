package myVelib;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import myVelib.MyVelib.StationDoesNotExist;
import myVelib.PathAlgorithm.AlgType;
import myVelib.Bicycle.Bicycle;
import myVelib.Bicycle.BicycleType;
import myVelib.Bicycle.ElectricBicycle;
import myVelib.Bicycle.MechanicalBicycle;
import myVelib.Card.Card;
import myVelib.Misc.GPS;
import myVelib.Misc.Ride;
import myVelib.Misc.User;
import myVelib.Station.Station;
import myVelib.Station.Station.EmptyStationException;
import myVelib.Station.Station.FullStationException;
import myVelib.Station.Station.OffLineStationException;
import myVelib.Station.Station.UserAlreadyHaveBicycleException;

public class Controls_CLUI {
	
	public ArrayList<MyVelib> velibnetworks;
	public ArrayList<User> usersGlobal;
	public ArrayList<Station> stationsGlobal;
	public Controls_CLUI() {
		this.velibnetworks = new ArrayList<MyVelib>();
		this.usersGlobal = new ArrayList<User>();
		this.stationsGlobal = new ArrayList<Station>();
	}
	
	public static void main(String[] args) throws NetworkDoesNotExist, StationDoesNotExist, FileNotFoundException, IOException, myVelib.MyVelib.StationDoesNotExist, UserDoesNotExist, EmptyStationException, UserAlreadyHaveBicycleException, OffLineStationException, FullStationException {
		Controls_CLUI basics = new Controls_CLUI();
		Scanner reader = new Scanner(System.in);
		System.out.println("Welcome to the my_velib CLUI !");
		System.out.println("Enter your commands here. Enter help for informations about the commands");
		System.out.println("To end the simulation, enter exit. Be careful with uppercases...");
		String commande = "";
		while (!((commande = reader.next()).equalsIgnoreCase("exit"))) {
			
			switch(commande) {
			
			case "setup": String name = reader.next();
							MyVelib mv = MyVelib.myVelibINI(name);
							basics.velibnetworks.add(mv);
							basics.stationsGlobal.addAll(mv.getStations());
							System.out.println("The "+ name+" network has been created.");
							break;
							
			case "adduser": String userName = reader.next();
							String cardType = reader.next();
							String velibName = reader.next();
							MyVelib my_velib0 = basics.getMyVelib(velibName);
							User u = new User(userName,new GPS(my_velib0.getCityDimension()), Card.Card(cardType));
							my_velib0.addUser(u);
							basics.usersGlobal.add(u);
							System.out.println("User "+userName+" of ID "+u.getID()+" with "+u.getUserCard()+" card, has been added to the "+velibName+" network.");
							break;
							
			case "offline": String velibnetworkName = reader.next();
							int stationID = Integer.parseInt(reader.next());
							MyVelib my_velib = basics.getMyVelib(velibnetworkName);
							Station station = my_velib.getStation(stationID);
							station.putOffLine();
							System.out.println("the station "+stationID+" of network "+velibnetworkName+" is now offline.");
							break;
							
			case "online":	String velibnetworkName1 = reader.next();
							int stationID1 = Integer.parseInt(reader.next());
							MyVelib my_velib1 = basics.getMyVelib(velibnetworkName1);
							Station station1 = my_velib1.getStation(stationID1);
							station1.putOnline();
							System.out.println("the station "+stationID1+" of network "+velibnetworkName1+" is now online.");
							break;
							
			case "rentbike": int userID2 = Integer.parseInt(reader.next());
							int stationID2 = Integer.parseInt(reader.next());
							String type = reader.next();
							Station station2 = basics.getStation(stationID2);
							User user2 = basics.getUser(userID2);
							BicycleType typeb = BicycleType.MECHANICAL;
							if (type.equalsIgnoreCase("ELECTRIC")) {
								typeb = BicycleType.ELECTRIC;
							}
							if (user2.getCurrentRide() != null) {
								//trouver une solution pour appeler le bon network...
								user2.setCurrentRide(new Ride(station2.getPosition(),station2.getPosition(), type, basics.velibnetworks.get(0), AlgType.SHORTEST));
							}
							station2.takeBicycle(typeb, user2);
							System.out.println("User "+user2.getName()+" rented a "+ typeb+" bike at station "+ station2.getID());
							break;
							
			case "returnbike": int userID3 = Integer.parseInt(reader.next());
							   int stationID3 = Integer.parseInt(reader.next());
							   Station station3 = basics.getStation(stationID3);
							   User user3 = basics.getUser(userID3);
							   if (user3.getCurrentRide() != null) {
								   Bicycle bicycle = user3.getCurrentRide().getBicycle();
								   station3.parkBicycle(bicycle,user3);
							   }
							   else {
								   System.out.println("the user possess no bike to return...");
							   }
							   break;
							   
			case "displayStation": 	String velibnetworkName4 = reader.next();
									int stationID4 = Integer.parseInt(reader.next());
									MyVelib my_velib4 = basics.getMyVelib(velibnetworkName4);
									Station station4 = my_velib4.getStation(stationID4);
									System.out.println("Number of renting operations from station "+
											stationID4 + " : "+ station4.getNbRent());
									System.out.println("Number of return operations from station "+
											stationID4 + " : "+ station4.getNbReturn());
									//réparer averagerateofoccupation qui ne marche pas très bien...
									System.out.println("Average rate of occupation :"+ station4.averageRateOfOccupation());
									break;
									
			case "sortStation":
				
			case "display":	String velibnetworkName5 = reader.next();
							MyVelib my_velib5 = basics.getMyVelib(velibnetworkName5);
							my_velib5.displayState();
							
							break;
			case "askForRidePlan":	int userID6 = Integer.parseInt(reader.next());
									User user6 = basics.getUser(userID6);
									int x = Integer.parseInt(reader.next());
									int y = Integer.parseInt(reader.next());
									String type6 = reader.next();
									String algtype6 = reader.next();
									String velibName6 = reader.next();
									MyVelib my_velib6 = basics.getMyVelib(velibName6);
									GPS dest = new GPS(x,y);
									AlgType alg = null;
									switch(algtype6) {
										case"avoidplus" : alg = AlgType.AVOIDPLUS; break;
										case"fastest": alg = AlgType.FASTEST; break;
										case"preferplus": alg = AlgType.PREFERPLUS; break;
										case"shortest": alg = AlgType.SHORTEST; break;
										default : alg = AlgType.SHORTEST; break;
									}
									if (algtype6.equalsIgnoreCase("AvoidPlus"))
									System.out.println(user6.getCurrentRide().getVelibNW().getCityDimension());
									user6.setCurrentRide(new Ride(user6.getPosition(),dest, type6, my_velib6, alg));
									user6.displayRide(user6);
									break;
				
			case "displayRide": int userID7 = Integer.parseInt(reader.next());
			 					User user7 = basics.getUser(userID7);
								User.displayRide(user7);
								break;		
			}
			
			
			
			}
			
			System.out.println("goodbye !");
		}
	
	
public class NetworkDoesNotExist extends Throwable {
		
		public NetworkDoesNotExist(String name) {
		System.out.println("the network named "+name+" does not exist in this universe...");
		}
	}

	public MyVelib getMyVelib(String name) throws NetworkDoesNotExist{
		boolean flag = false;
		MyVelib ss = null;
		for(MyVelib mv : velibnetworks) {
			if (mv.getName().equalsIgnoreCase(name)) {
				flag = true;
				ss = mv;
			}
		}
		if (!(flag)) {
			throw new NetworkDoesNotExist(name);
		}
		return ss;
	}
	
public class UserDoesNotExist extends Throwable {
		
		public UserDoesNotExist(int ID) {
		System.out.println("the user of ID "+ID+" does not exist in this universe...");
		}
	}
	public User getUser(int ID) throws UserDoesNotExist {
		boolean flag = false;
		User ss = null;
		for(User u : usersGlobal) {
			if (u.getID() == ID) {
				flag = true;
				ss = u;
			}
		}
		if (!(flag)) {
			throw new UserDoesNotExist(ID);
		}
		return ss;
	}
	
public class StationDoesNotExist extends Throwable {
		
		public StationDoesNotExist(int ID) {
		System.out.println("the station of ID "+ID+" does not exist in this universe...");
		}
	}
	public Station getStation(int ID) throws StationDoesNotExist {
		boolean flag = false;
		Station ss = null;
		for(Station u : stationsGlobal) {
			if (u.getID() == ID) {
				flag = true;
				ss = u;
			}
		}
		if (!(flag)) {
			throw new StationDoesNotExist(ID);
		}
		return ss;
	}
	

}

