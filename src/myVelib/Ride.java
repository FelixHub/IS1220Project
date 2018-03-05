package myVelib;

import java.util.Observable;
import java.util.Observer;

public class Ride implements Observer{
	Station startStation;
	Station destStation;
	
	public Ride(GPS departure,GPS destination, Bicycle bicycle) {
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
