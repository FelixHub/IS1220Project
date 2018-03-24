package myVelib.Station;

import java.util.HashMap;
import java.util.Map;

import myVelib.GPS;
import myVelib.User;
import myVelib.Bicycle.Bicycle;
import myVelib.Bicycle.BicycleType;
import myVelib.Bicycle.ElectricBicycle;
import myVelib.Bicycle.MechanicalBicycle;

public abstract class Station {
	
	GPS position;
<<<<<<< HEAD
	int ID;
	String state;
=======
	StationState state;
>>>>>>> branch 'master' of https://github.com/FelixHub/IS1220Project.git
	Bicycle[] parkingSlots;
	int capacity;
	int nbRent;
	int nbReturn;
	static int count_ID;

	public GPS getPosition() {
		return position;
	}

	public StationState getState() {
		return state;
	}
	
	
	
	class FullStationException extends Exception{}
	class EmptyStationException extends Exception{}
	class OffLineStationException extends Exception{}

	
	public void parkBicycle(Bicycle bicycle, User user) throws FullStationException {
		try {
			if(freeParkingSpotsNb() == 0) throw new FullStationException();
			else {
				for(int i = 0; i < capacity; i++) {
					if (parkingSlots[i] == null) {
						parkingSlots[i] = bicycle;
						// c'est là où on récupère le temps de la fin du ride et où on lance 
						// le débit de la carte de user + rajout de temps si dans une station +
						break;
					}
				}
			}
		} catch(FullStationException e) { System.out.println("there's no more parking spot at station"+ID);}
	}
	
	public Bicycle takeBicycle(BicycleType type) throws EmptyStationException {
		try {
			if (countBicycle(type) == 0) throw new EmptyStationException();
			else {
				for(int i = 0; i < capacity; i++) {
					if ((parkingSlots[i].getType()).equals(type)) {
						Bicycle temp = parkingSlots[i];
						parkingSlots[i] = null;
						return temp;
						// à ce moment là doit se déclencher le décompte en temps de ride
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

}


























//public abstract class Station extends Observable {
//ArrayList<Observer> observers;
	//boolean isFull;
	
/**
	
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}
	
	public void notifyObserver() {
		
		for (ParkingSlot parkingSlot : parkingSlots) {
			if (parkingSlot.isEmpty()) { isFull = false; }
			else { isFull = isFull && true; };
			}
		if (this.isFull) {
			for (Observer ob : observers) 
				ob.update(this,this.ID);
	}
	
	
	
	}
	
	

}

package myVelib.Station;

import java.util.ArrayList;
import java.util.Observable;

import myVelib.Bicycle.Bicycle;

public class ParkingSlot extends Observable {
	int ID;
	ArrayList<Bicycle> content;
	boolean isOnline;
	Station station;
	
	void empty() {
		content.remove(0);
	}
	
	void fill(Bicycle b) {
		if (content.isEmpty()) {
			content.add(b);
			station.notifyObserver();
		}
	}
	
	boolean isEmpty() {
		if (content.size() < 1) return true;
		return false;
	}
	
	
	public ParkingSlot(int iD, ArrayList<Bicycle> content, boolean isOnline, Station station) {
		super();
		ID = iD;
		this.content = content;
		this.isOnline = isOnline;
		this.station = station;
	}
	
	
}

**/
