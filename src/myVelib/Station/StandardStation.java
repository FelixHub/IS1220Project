package myVelib.Station;
/**
 * a class representing a standard station with integrated constructor
 */

import java.util.ArrayList;
import java.util.Observer;

import myVelib.Bicycle.Bicycle;
import myVelib.Misc.GPS;

public class StandardStation extends Station {

	public StandardStation(GPS position, int capacity) {
		super();
		this.position = position;
		this.ID = Station.count_ID;
		Station.count_ID ++ ;
		this.capacity = capacity;
		this.parkingSlots = new Bicycle[capacity];
		this.nbRent = 0;
		this.nbReturn = 0;
		this.state = "ONSERVICE";
		this.type = "STANDARD";
		this.incomingRides = new ArrayList<Observer>();
		this.occupationRecord = new long[capacity][2];
		for(int i = 0; i< capacity;i++) {
			occupationRecord[i][0] = 0;
			occupationRecord[i][1] = 0;
		}
	}
}
