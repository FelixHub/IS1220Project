package myVelib;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Station extends Observable {
	String name;
	GPS coordinate;
	int ID;
	boolean isOpen;
	ArrayList<ParkingSlot> parkingSlots;
	int nbRent;
	int nbReturn;
	ArrayList<Observer> observers = new ArrayList<Observer>();
	boolean isFull;
	
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}
	
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
				ob.update(this,this.name);
	}
	
	
	
	}
	
	

}
