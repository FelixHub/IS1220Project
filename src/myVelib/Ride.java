package myVelib;

import java.util.Observable;
import java.util.Observer;

public class Ride implements Observer{
	Station startStation;
	Station destStation;
	// créer t debut et t fin
	
	public Ride(GPS departure,GPS destination, Bicycle bicycle) {
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// on rappelle l'algo qui choisit le chemin
	}
}
