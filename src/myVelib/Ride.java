package myVelib;

import java.util.Observable;
import java.util.Observer;

import myVelib.Bicycle.Bicycle;
import myVelib.Bicycle.BicycleFactory;
import myVelib.Bicycle.BicycleType;
import myVelib.Station.Station;

public class Ride implements Observer{

	GPS start;
	GPS end;
	String algType;
	PathFinder path;
	Bicycle bicycle;
	MyVelib velibNW;

	
	
	public Ride(GPS start, GPS end, PathFinder path, String type, MyVelib velibNW) {
		
		this.start=start;
		this.end=end;
		this.velibNW=velibNW;
		this.path = path;
		this.bicycle = BicycleFactory.createBicycle(type);
		//this.path.getStartStation() c'est la syntaxe pour avoir la station d'arriv�e pour pouvoir faire add Observer et tout

		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (this.algType=="Shortest"){
				path = new ShortestPath.Path(start,end,velibNW,bicycle.getType());
		}
		
	}
}
