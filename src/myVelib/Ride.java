package myVelib;

import java.util.Observable;
import java.util.Observer;

public class Ride implements Observer{
	
	MyClock clock;
	GPS start_gps;
	GPS dest_gps;
	PathFinder path;
	Bicycle bycicle;
	
	
	public Ride(GPS start_gps, GPS dest_gps, PathFinder path, Bicycle bycicle) {
		this.start_gps = start_gps;
		this.dest_gps = dest_gps;
		this.path = path;
		this.bycicle = bycicle;
		this.clock=new MyClock();
		
	}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
