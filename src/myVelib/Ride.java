package myVelib;

import java.util.Observable;
import java.util.Observer;

import myVelib.Bicycle.Bicycle;
import myVelib.Station.Station;

public class Ride implements Observer{
<<<<<<< HEAD
	Station startStation;
	Station destStation;
	// créer t debut et t fin
=======
>>>>>>> branch 'master' of https://github.com/FelixHub/IS1220Project.git
	
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
		// on rappelle l'algo qui choisit le chemin
	}
}
