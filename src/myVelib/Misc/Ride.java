package myVelib.Misc;

import java.util.Observable;
import java.util.Observer;

import myVelib.MyVelib;
import myVelib.Bicycle.Bicycle;
import myVelib.Bicycle.BicycleFactory;
import myVelib.Bicycle.BicycleType;
import myVelib.PathAlgorithm.AlgType;
import myVelib.PathAlgorithm.FastestPath;
import myVelib.PathAlgorithm.PathFinder;
import myVelib.PathAlgorithm.ShortestPath;
import myVelib.Station.Station;

public class Ride implements Observer{

	private GPS start;
	private GPS end;
	private String algType;
	private PathFinder path;
	private Bicycle bicycle;
	private MyVelib velibNW;

	
	
	public Ride(GPS start, GPS end, String type, MyVelib velibNW) {
		
		this.start=start;
		this.end=end;
		this.velibNW=velibNW;
		if(algType.equals(AlgType.SHORTEST)) {
			this.path = new ShortestPath();
		}
		if(algType.equals(AlgType.FASTEST)) {
			this.path = new FastestPath();
		}
		
		path.path(start, end, velibNW, bicycle.getType());
		this.bicycle = BicycleFactory.createBicycle(type);
		//this.path.getStartStation() c'est la syntaxe pour avoir la station d'arrivée pour pouvoir faire add Observer et tout

		
	}
	
	@Override
	public void update(Observable o, Object arg) {
			path.path(start,end,velibNW,bicycle.getType());
		
	}
}
