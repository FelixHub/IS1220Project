package myVelib.Station;

import java.util.ArrayList;
import java.util.Observer;

import myVelib.Bicycle.Bicycle;
import myVelib.Misc.GPS;

public class PlusStation extends Station {
	
	public PlusStation(GPS position, int capacity) {
		
		super();
		this.position = position;
		this.ID = Station.count_ID;
		Station.count_ID ++ ;
		this.capacity = capacity;
		this.parkingSlots = new Bicycle[capacity];
		this.nbRent = 0;
		this.nbReturn = 0;
		this.state = "ONSERVICE";
		this.type = "PLUS";
		this.incomingRides = new ArrayList<Observer>();
		this.occupationRecord = new long[capacity][2];
		for(int i = 0; i< capacity;i++) {
			occupationRecord[i][0] = 0;
			occupationRecord[i][1] = 0;
		}
	}
	
	
}
