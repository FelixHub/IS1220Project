package myVelib;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Station {
	String name;
	GPS coordinate;
	int ID;
	boolean isOpen;
	ArrayList<ParkingSlot> parkinSlots;
	int nbRent;
	int nbReturn;

	public void update(ParkingSlot p, Bicycle b) {
		
		
	}

}
