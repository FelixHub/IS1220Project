package myVelib.Station;

import java.util.ArrayList;
import java.util.Observer;

import myVelib.GPS;
import myVelib.Bicycle.Bicycle;

public class StandardStation extends Station {

	public StandardStation(GPS position, StationState state, int capacity) {
		super();
		this.position = position;
		this.ID = Station.count_ID;
		Station.count_ID ++ ;
		this.capacity = capacity;
		this.parkingSlots = new Bicycle[capacity];
		this.nbRent = 0;
		this.nbReturn = 0;
		this.state = state;
		//this.observers = new ArrayList<Observer>();
	}
}
