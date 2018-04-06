package myVelib.Station;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import myVelib.MyVelib;
import myVelib.Bicycle.Bicycle;
import myVelib.Bicycle.BicycleType;
import myVelib.Bicycle.ElectricBicycle;
import myVelib.Bicycle.MechanicalBicycle;
import myVelib.Misc.GPS;
import myVelib.Misc.User;
/**
 * a class representing a station in a myVelib system.
 * This is a subclass of Observable because we implement here an Observer pattern :
 * a station is observed by the Ride classes for whom it is their EndStation. 
 * The Ride classes are notified if the station is put OFFLINE or if it become FULL.
 * 
 */ 
public abstract class Station extends Observable {
	
	
	
	GPS position;
	int ID;
	/**
	 * the state of the station is "OFFLINE" or "ONSERVICE".
	 */
	String state;
	/**
	 * the type of the station is "PLUS" or "STANDARD".
	 */
	public String type; 
	/**
	 * Parking slots are represented by the index of an array of length the number of  station's parkingSpot
	 */
	protected Bicycle[] parkingSlots;
	int capacity;
	int nbRent;
	int nbReturn;
	MyVelib StationNetwork;
	public static int count_ID;
	/**
	 * to make the average occupation time statistic, we keep udapted a two dimensionnal array wich contain :
	 * - total time the i-eme parkingSpot was occupied in first line
	 * - last time a bicycle was park in the i-eme parkingSpot 
	 */
	long[][] occupationRecord;
	
	ArrayList<Observer> incomingRides;
	
	
	public void removeObserver(Observer observer) {
		incomingRides.remove(observer); 
	}
	public void addObserver(Observer observer) {
		incomingRides.add(observer);
	}
	/**
	 *  Station class notify its Ride observer of its indisponibility.
	 */
	public void notifyObserver() {
		if ( (freeParkingSpotsNb() == 0) || (state.equals("OFFLINE")) ){
			for (Observer ob : incomingRides) {
				ob.update(this,this.ID);
			}
		}
	}
	
	public class FullStationException extends Exception{}
	public class EmptyStationException extends Exception{}
	public class OffLineStationException extends Exception{}
	public class UserAlreadyHaveBicycleException extends Exception{}

	
	/**
	 * park user's bicycle at the destination station and run methods to process costs, duration of ride, statistics etc..
	 * @param bicycle
	 * @param user
	 * @throws FullStationException
	 * @throws OffLineStationException
	 */
	
	public void parkBicycle(Bicycle bicycle, User user) throws FullStationException, OffLineStationException {
		try {
			if(freeParkingSpotsNb() == 0) throw new FullStationException();
			else if (state.equals("OFFLINE")) throw new OffLineStationException();
			else {
				for(int i = 0; i < capacity; i++) {

					if (parkingSlots[i] == null) {
						parkingSlots[i] = bicycle;
						long returnTime = MyVelib.getClock().getTime();
						occupationRecord[i][1] = returnTime;
						nbReturn ++;
						user.addCharges( user.getUserCard().rideCost(
								returnTime - user.TimeOfLastRenting,bicycle));
						user.ridesNb ++;
						user.setTimeSpentOnBike(returnTime - user.TimeOfLastRenting);
						removeObserver(user.getCurrentRide());
						user.setCurrentRide(null);
						user.bicycle = null;
						user.possessBicycle = false;
						if ((this.type).equalsIgnoreCase("PLUS")){
							user.getUserCard().setTimeCredit(user.getUserCard().getTimeCredit()+5);
							user.timeCreditBalance = user.timeCreditBalance + 5;
						}
						notifyObserver();
						break;
					}
				}
			} 
		} catch(FullStationException e) { System.out.println("there's no more parking spot at station"+ID);}
		  catch(OffLineStationException e) {System.out.println("this station is offline, you can't park here");}
	}
	
	/**
	 * 
	 * @param type
	 * @param user
	 * @return
	 * @throws EmptyStationException
	 * @throws UserAlreadyHaveBicycleException
	 * @throws OffLineStationException
	 */
	public Bicycle takeBicycle(BicycleType type, User user) throws EmptyStationException, UserAlreadyHaveBicycleException, OffLineStationException {
		try {
			if (countBicycle(type) == 0) throw new EmptyStationException();
			else if (user.possessBicycle) throw new UserAlreadyHaveBicycleException();
			else if (state.equals("OFFLINE")) throw new OffLineStationException();
			else {
				for(int i = 0; i < capacity; i++) {
					if(parkingSlots[i] == null) {
						continue;
					}
					else if (parkingSlots[i].type == type ) {
						user.TimeOfLastRenting = MyVelib.getClock().getTime();
						occupationRecord[i][0] = occupationRecord[i][0] + (user.TimeOfLastRenting - occupationRecord[i][1]);
						nbRent ++;
						Bicycle temp = parkingSlots[i];
						parkingSlots[i] = null;
						if (user.getCurrentRide() != null) {
							user.getCurrentRide().setBicycle(temp);
						
						}
						user.setBicycle(temp);
						user.possessBicycle=true;	
						return temp;
					}
				}
				
			}
		}catch(UserAlreadyHaveBicycleException e) {System.out.println("You can't rent two bikes at the same time.");}
		 catch(EmptyStationException e ) {System.out.println("the desired bicycle type is not available at station"+ID);}
	return null;
	}
	
	/**
	 * return the number of bicycle of a certain type available at the station 
	 * @param type
	 * @return
	 */
	
	public int countBicycle(BicycleType type) {
		int t = 0;
		for(Bicycle slot : parkingSlots) {
			if(slot == null) {
				continue;
			}
			else if ((slot.getType()).equals(type)) {
				t ++;
			}
		}
		return t;
	}
	
	/**
	 * return the number of available parking spot at the station
	 * @return
	 */
	public int freeParkingSpotsNb() {
		int s = 0;
		for(Bicycle slot : getParkingSlots()) {
			if (slot == null) {
				s ++;
			}
		}
		return s;
	}
	
	
	
	public GPS getPosition() {
		return position;
	}
	public void setPosition(GPS position) {
		this.position = position;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getID() {
		return ID;
	}
	public int getNbRent() {
		return nbRent;
	}
	public int getNbReturn() {
		return nbReturn;
	}
	public void setParkingSlots(Bicycle[] parkingSlots) {
		this.parkingSlots = parkingSlots;
	}
	
	public void putOffLine() {
		state = "OFFLINE";
		notifyObserver();
	}
	public void putOnline() {
		state = "ONSERVICE";
	}
	
	public float averageRateOfOccupation() {
		//long te = 0;
		long ts = MyVelib.getClock().getTime();
		float s = 0;
		for(int i = 0; i< capacity;i++) {
			occupationRecord[i][0] = occupationRecord[i][0] + (ts - occupationRecord[i][1]);
			occupationRecord[i][1] = ts;
			s = s + occupationRecord[i][0];
			}
		s = s/(ts*capacity);
		return s;
	}
	
	public Station(String stationType,GPS position, int capacity) {
		StationFactory.createStation(stationType,position, capacity);
	}
	public Station() {
	}
	public Bicycle[] getParkingSlots() {
		return parkingSlots;
	}
	public String toString() {
		return  type + "station"+getID();
	}
 
}
