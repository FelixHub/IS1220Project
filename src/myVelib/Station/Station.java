package myVelib.Station;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import myVelib.GPS;
import myVelib.MyVelib;
import myVelib.User;
import myVelib.Bicycle.Bicycle;
import myVelib.Bicycle.BicycleType;
import myVelib.Bicycle.ElectricBicycle;
import myVelib.Bicycle.MechanicalBicycle;

public abstract class Station extends Observable {
	
	
	
	GPS position;
	int ID;
	String state;
	String type;
	Bicycle[] parkingSlots;
	int capacity;
	int nbRent;
	int nbReturn;
	static int count_ID;
	long[][] occupationRecord;
	
	ArrayList<Observer> incomingRides;
	
	public void removeObserver(Observer observer) {
		incomingRides.remove(observer);
	}
	public void addObserver(Observer observer) {
		incomingRides.add(observer);
	}
	public void notifyObserver() {
		if (freeParkingSpotsNb() == 0){
			for (Observer ob : incomingRides) {
				ob.update(this,this.ID);
			}
		}
	}
	
	class FullStationException extends Exception{}
	class EmptyStationException extends Exception{}
	class OffLineStationException extends Exception{}
	class UserAlreadyHaveBicycleException extends Exception{}

	
	public void parkBicycle(Bicycle bicycle, User user) throws FullStationException {
		try {
			if(freeParkingSpotsNb() == 0) throw new FullStationException();
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
						user.setCurrentRide(null);
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
	}
	
	public Bicycle takeBicycle(BicycleType type, User user) throws EmptyStationException, UserAlreadyHaveBicycleException {
		try {
			if (countBicycle(type) == 0) throw new EmptyStationException();
			if (user.possessBicycle) throw new UserAlreadyHaveBicycleException();
			else {
				for(int i = 0; i < capacity; i++) {
					if ((parkingSlots[i].getType()).equals(type)) {
						user.TimeOfLastRenting = MyVelib.getClock().getTime();
						occupationRecord[i][0] = occupationRecord[i][0] + (user.TimeOfLastRenting - occupationRecord[i][1]);
						nbRent ++;
						Bicycle temp = parkingSlots[i];
						parkingSlots[i] = null;
						return temp;
					}
				}
				
			}
		} catch(EmptyStationException e ) {System.out.println("the desired bicycle type is not available at station"+ID);}
	return null;
	}
	
	
	public int countBicycle(BicycleType type) {
		int t = 0;
		for(Bicycle slot : parkingSlots) {
			if ((slot.getType()).equals(type)) {
				t ++;
			}
		}
		return t;
	}
	
	public int freeParkingSpotsNb() {
		int s = 0;
		for(Bicycle slot : parkingSlots) {
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

}
