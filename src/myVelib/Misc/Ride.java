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
	private PathFinder ridePath;
	private Bicycle bicycle;
	private MyVelib velibNW;

	
	
	public Ride(GPS start, GPS end, String type, MyVelib velibNW, AlgType algType ) {
		this.start=start;
		this.end=end;
		this.velibNW=velibNW;
		switch (algType) {
			case SHORTEST:
				this.ridePath=new ShortestPath();
				break;
			case FASTEST:
				this.ridePath=new FastestPath();
				break;
			case AVOIDPLUS:
				this.ridePath=new AvoidPlus();
				break;
			case PREFERPLUS:
				this.ridePath=new PreferPlus();
				break;
		}
		ridePath.path(start, end, velibNW, bicycle.getType());
		this.bicycle = null;
		ridePath.getEndStation().addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
			ridePath.path(start,end,velibNW,bicycle.getType());
		
	}

	public void setBicycle(Bicycle b) {
		bicycle = b;
	}

	public GPS getStart() {
		return start;
	}

	public GPS getEnd() {
		return end;
	}

	public AlgType getAlgType() {
		return algType;
	}

	public Bicycle getBicycle() {
		return bicycle;
	}
	
}
