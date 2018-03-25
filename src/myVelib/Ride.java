package myVelib;

import java.util.Observable;
import java.util.Observer;

import myVelib.Bicycle.Bicycle;
import myVelib.Bicycle.BicycleType;
import myVelib.Station.Station;

public class Ride implements Observer{

	GPS start;
	GPS end;
	String algType;
	PathFinder path;
	Bicycle bicycle;
	MyVelib velibNW;
	long bicycleRideStart;
	long bicycleRideEnd;
	
	
	public Ride(GPS start, GPS end, PathFinder path, BicycleType type, MyVelib velibNW) {
		
		this.start=start;
		this.end=end;
		this.velibNW=velibNW;
		this.path = path;
		this.bicycle = bicycle;
		this.bicycleRideStart = 0;
		this.bicycleRideEnd = 0;
		//this.path.getStartStation() c'est la syntaxe pour avoir la station d'arrivée pour pouvoir faire add Observer et tout

		
	}
	

	public void setBicycleRideStart() {
		this.bicycleRideStart = velibNW.getClock().getTime();
		
	}
	
	public void setBicycleRideEnd() {
		this.bicycleRideEnd = velibNW.getClock().getTime();
	}
	


	public long getBicycleRideStart() {
		return bicycleRideStart;
	}

	public long getBicycleRideEnd() {
		return bicycleRideEnd;
	}

	
	@Override
	public void update(Observable o, Object arg) {
		if (this.algType=="Shortest"){
				path = new ShortestPath.Path(start,end,velibNW,bicycle.getType());
		}
		
	}
}
