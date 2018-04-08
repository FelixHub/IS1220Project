package myVelib;

import java.io.File;
import java.io.FileInputStream;
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
/**
 * the main class of our program, which contain the CLUI.
 *
 */
public class Controls_CLUI {
	
	public ArrayList<MyVelib> velibnetworks;
	public ArrayList<User> usersGlobal;
	public ArrayList<Station> stationsGlobal;
	public Controls_CLUI() {
		this.velibnetworks = new ArrayList<MyVelib>();
		this.usersGlobal = new ArrayList<User>();
		this.stationsGlobal = new ArrayList<Station>();
	}
	/**
	 * 
	 * @param args
	 * @throws NetworkDoesNotExist
	 * @throws StationDoesNotExist
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws myVelib.MyVelib.StationDoesNotExist
	 * @throws UserDoesNotExist
	 * @throws EmptyStationException
	 * @throws UserAlreadyHaveBicycleException
	 * @throws OffLineStationException
	 * @throws FullStationException
	 */
	public static void main(String[] args) throws NetworkDoesNotExist, StationDoesNotExist, FileNotFoundException, IOException, myVelib.MyVelib.StationDoesNotExist, UserDoesNotExist, EmptyStationException, UserAlreadyHaveBicycleException, OffLineStationException, FullStationException {
		Controls_CLUI basics = new Controls_CLUI();
		Scanner reader = new Scanner(System.in);
		System.out.println("Welcome to the my_velib CLUI !");
		System.out.println("Enter your commands here. Enter help for informations about the commands");
		System.out.println("To end the simulation, enter exit. Be careful with uppercases...");
		String commande = "";
		boolean flagg = true;
		while ( (!((commande = reader.next()).equalsIgnoreCase("exit"))) && flagg ) {
			System.out.println(" ");
			switch(commande) {
			
			case "runtest":	String path = reader.next();
							File file = new File(path);
							reader = new Scanner(file);
							break;
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
							
			
			case "setgps":	int userID0 = Integer.parseInt(reader.next());
				 			User user0 = basics.getUser(userID0);
				 			user0.setGPS(new GPS (Integer.parseInt(reader.next()),Integer.parseInt(reader.next())));
							break;
							
		case "stationsetgps":	int stationID0 = Integer.parseInt(reader.next());
								int x =Integer.parseInt(reader.next());
								int y = Integer.parseInt(reader.next());
 								basics.getStation(stationID0).setGPS(new GPS (x,y));
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
							user2.setBicycle(station2.takeBicycle(typeb, user2));
							user2.possessBicycle = true;
							System.out.println("User "+user2.getName()+" rented a "+ typeb+" bike at station "+ station2.getID());
							break;
							
			case "returnbike": int userID3 = Integer.parseInt(reader.next());
							   int stationID3 = Integer.parseInt(reader.next());
							   Station station3 = basics.getStation(stationID3);
							   double a = basics.getUser(userID3).getChargesAmount();
							   if (basics.getUser(userID3).possessBicycle == true) {
								   station3.parkBicycle(basics.getUser(userID3).bicycle,basics.getUser(userID3));
								   System.out.println("User "+userID3+" has parked his bike at station "+ stationID3);
								   double b = basics.getUser(userID3).getChargesAmount();
								   System.out.println("The ride has costed "+ (b-a)+ "euros.");
							   }
							   else {
								   System.out.println("the user possess no bike to return...");
							   }
							   break;
							   
			case "displaystation": 	String velibnetworkName4 = reader.next();
									int stationID4 = Integer.parseInt(reader.next());
									MyVelib my_velib4 = basics.getMyVelib(velibnetworkName4);
									Station station4 = my_velib4.getStation(stationID4);
									System.out.println("* * * Station "+ stationID4+" * * *");
									System.out.println("Number of renting operations : "+ station4.getNbRent());
									System.out.println("Number of return operations : "+ station4.getNbReturn());
									System.out.println("Average rate of occupation : "+ station4.averageRateOfOccupation());
									System.out.println("* * * * * * * * * * * *");
									break;
									
			case "displayuser":	int userID8 = Integer.parseInt(reader.next());
								User user8 = basics.getUser(userID8);
								System.out.println("* * * User "+ userID8+" "+user8.getName()+" * * *");
								System.out.println("Number of ride performed : "+ user8.getRidesNb());
								System.out.println("Total time spent on a bike : "+ user8.getTimeSpentOnBike() );
								System.out.println("Total charge amount : "+ user8.getChargesAmount());
								System.out.println("Current time credit : "+ user8.getTimeCreditBalance());
								System.out.println("GPS coordinate : "+ user8.getPosition());
								System.out.println("* * * * * * * * * * * *");
								break;
				
			case "display":	String velibnetworkName5 = reader.next();
							MyVelib my_velib5 = basics.getMyVelib(velibnetworkName5);
							my_velib5.displayState();
							System.out.println("Users number : "+ my_velib5.getUsers().size());
							System.out.println("Stations number : "+ my_velib5.getStations().size());
							break;
							
			case "askforrideplan":	int userID6 = Integer.parseInt(reader.next());
									int x1 = Integer.parseInt(reader.next());
									int y1 = Integer.parseInt(reader.next());
									String type6 = reader.next();
									String algtype6 = reader.next();
									String velibName6 = reader.next();
									GPS dest = new GPS(x1,y1);
									AlgType alg = null;
									switch(algtype6) {
										case"avoidplus" : alg = AlgType.AVOIDPLUS; break;
										case"fastest": alg = AlgType.FASTEST; break;
										case"preferplus": alg = AlgType.PREFERPLUS; break;
										case"shortest": alg = AlgType.SHORTEST; break;
										default : alg = AlgType.SHORTEST; break;
										}
									basics.getUser(userID6).setCurrentRide(new Ride(basics.getUser(userID6).getPosition(),dest, type6, basics.getMyVelib(velibName6), alg));
									basics.getUser(userID6).displayRide(basics.getUser(userID6));
									break;
				
			case "displayride": int userID7 = Integer.parseInt(reader.next());
			 					User user7 = basics.getUser(userID7);
								User.displayRide(user7);
								break;	
			
			case "passingtime": int time = Integer.parseInt(reader.next());
								MyVelib.getClock().addTime(time*1000/60);
								System.out.println(time +" minutes have passed.");
								break;
			
			case "putofflineendstation" : 	int userID10 = Integer.parseInt(reader.next());
			System.out.println("the station "+basics.getUser(userID10).currentRide.getRidePath().getEndStation().getID()+" is now offline.");
											basics.getUser(userID10).currentRide.getRidePath().getEndStation().putOffLine();
											break;
											
			case "startride" : int userID21 = Integer.parseInt(reader.next());
			basics.getUser(userID21).setBicycle(basics.getUser(userID21).currentRide.getRidePath().getStartStation().takeBicycle(basics.getUser(userID21).currentRide.getType(), basics.getUser(userID21)));
			basics.getUser(userID21).possessBicycle = true;
			System.out.println("User "+basics.getUser(userID21).getName()+" rented a "+ basics.getUser(userID21).currentRide.getType()+" bike at station "+ basics.getUser(userID21).currentRide.getRidePath().getStartStation().getID());
			break;
			
			case "endride" :int userID31 = Integer.parseInt(reader.next());
			   if (basics.getUser(userID31).possessBicycle == true) {
				   double a1 = basics.getUser(userID31).getChargesAmount();
				   System.out.println("User "+userID31+" has parked his bike at station "+  basics.getUser(userID31).currentRide.getRidePath().getEndStation().getID());
				   basics.getUser(userID31).currentRide.getRidePath().getEndStation().parkBicycle(basics.getUser(userID31).bicycle,basics.getUser(userID31));
				   double b1 = basics.getUser(userID31).getChargesAmount();
				   System.out.println("The ride has costed "+ (b1-a1)+ "euros.");
			   }
			   else {
				   System.out.println("the user possess no bike to return...");
			   }
			   break;
			
			case "help": System.out.println("setup <MyVelibNetworkName>\r\n" + 
					"runtest <Path>\r\n" + 
					"adduser <UserName> <CardType> <VelibName>\r\n" + 
					"setgps <UserID> <x> <y>\r\n" + 
					"stationsetgps <StationID> <x> <y>\r\n" + 
					"offline <VelibNetworkName> <StationID>\r\n" + 
					"online <VelibNetworkName> <StationID>\r\n" + 
					"rentbike <userID> <StationID>\r\n" + 
					"returnbike <userID> <StationID>\r\n" + 
					"displaystation <VelibNetworkName> <StationID>\r\n" + 
					"displayuser <userID>\r\n" + 
					"display <VelibNetworkName>\r\n" + 
					"askforrideplan <userID> <x> <y> <BikeType> <AlgType> <VelibNetworkName>\r\n" + 
					"displayride <userID>\r\n" + 
					"passingtime <time(int)>\r\n" + 
					"putofflineendstation <userID>\r\n" + 
					"startride <userID>\r\n" + 
					"endride <userID>\r\n");break;
				
			default : System.out.println("command not recognized."); flagg = false; break;
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
		for(MyVelib mv : velibnetworks) {
			if (mv.getName().equalsIgnoreCase(name)) {
				flag = true;
				return mv;
			}
		}
		if (!(flag)) {
			throw new NetworkDoesNotExist(name);
		}
		return null;
	}
	
public class UserDoesNotExist extends Throwable {
		
		public UserDoesNotExist(int ID) {
		System.out.println("the user of ID "+ID+" does not exist in this universe...");
		}
	}
	public User getUser(int ID) throws UserDoesNotExist {
		boolean flag = false;
		for(User u : usersGlobal) {
			if (u.getID() == ID) {
				flag = true;
				return u;
			}
		}
		if (!(flag)) {
			throw new UserDoesNotExist(ID);
		}
		return null;
	}
	
public class StationDoesNotExist extends Throwable {
		
		public StationDoesNotExist(int ID) {
		System.out.println("the station of ID "+ID+" does not exist in this universe...");
		}
	}
	public Station getStation(int ID) throws StationDoesNotExist {
		boolean flag = false;
		for(Station u : stationsGlobal) {
			if (u.getID() == ID) {
				flag = true;
				return u;
			}
		}
		if (!(flag)) {
			throw new StationDoesNotExist(ID);
		}
		return null;
	}
	

}

