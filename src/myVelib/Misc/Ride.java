package myVelib.Misc;

import java.util.Observable;
import java.util.Observer;

import myVelib.MyVelib;
import myVelib.Bicycle.Bicycle;
import myVelib.Bicycle.BicycleFactory;
import myVelib.Bicycle.BicycleType;
import myVelib.PathAlgorithm.AlgType;
import myVelib.PathAlgorithm.AvoidPlus;
import myVelib.PathAlgorithm.FastestPath;
import myVelib.PathAlgorithm.PathFinder;
import myVelib.PathAlgorithm.PreferPlus;
import myVelib.PathAlgorithm.ShortestPath;
import myVelib.Station.Station;

public class Ride implements Observer{

	private GPS start;
	private GPS end;
	private AlgType algType;
	private PathFinder path;
	private Bicycle bicycle;
	private MyVelib velibNW;

	
	
	public Ride(GPS start, GPS end, String type, MyVelib velibNW, AlgType algType ) {
		
		this.start=start;
		this.end=end;
		this.velibNW=velibNW;
		
		switch (algType) {
			case SHORTEST:
				this.path=new ShortestPath();
				break;
			case FASTEST:
				this.path=new FastestPath();
				break;
			case AVOIDPLUS:
				this.path=new AvoidPlus();
				break;
			case PREFERPLUS:
				this.path=new PreferPlus();
				break;
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
