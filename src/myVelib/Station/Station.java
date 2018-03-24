package myVelib.Station;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import myVelib.GPS;
import myVelib.Bicycle.Bicycle;

public class Station extends Observable {
	
	GPS position;
	int ID;
	StationState state;
	Bicycle[] parkingSlots;
	int capacity;
	int nbRent;
	int nbReturn;
	//ArrayList<Observer> observers;
	//boolean isFull;
	
	static int count_ID;
	
	
	
	

}
	
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
**/
