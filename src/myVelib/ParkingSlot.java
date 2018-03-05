package myVelib;

import java.util.ArrayList;
import java.util.Observable;

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
	
	
	public ParkingSlot(int iD, ArrayList<Bicycle> content, boolean isOnline) {
		super();
		ID = iD;
		this.content = content;
		this.isOnline = isOnline;
	}
	
	
}
