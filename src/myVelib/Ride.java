package myVelib;

import java.util.Observable;
import java.util.Observer;

import myVelib.Bicycle.Bicycle;
import myVelib.Station.Station;

public class Ride implements Observer{

	GPS start;
	GPS end;
	PathFinder path;
	Bicycle bycicle;
	MyVelib velibNW;
	
	
	public Ride(GPS start, GPS end, PathFinder path, Bicycle bycicle, MyVelib velibNW) {
		
		this.start=start;
		this.end=end;
		this.velibNW=velibNW;
		this.path = path;
		this.bycicle = bycicle;
		
		//this.path.getStartStation() c'est la syntaxe poura voir la station d'arrivée pour pouvoir faire add Observer et tout
		//Pcque qu'on veut la surveiller
		
	}
	
	public long startRide() {
		return velibNW.getClock().getTime();
		
	}
	
	public long stopRide() {
		return velibNW.getClock().getTime();
	}

	
	@Override
	public void update(Observable o, Object arg) {
		// on rappelle l'algo qui choisit le chemin
	}
}
