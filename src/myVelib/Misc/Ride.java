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

/**
 * This class manages the information of the ride currently followed by a user.
 * 
 *
 */
public class Ride implements Observer{

	private GPS start;
	private GPS end;
	private AlgType algType;
	private PathFinder ridePath;
	private Bicycle bicycle;
	private MyVelib velibNW;

	/**
	 * this constructor run the path method from subclasses from PathFinder in order to get the StartStation and EndStation.
	 * @param start
	 * @param end
	 * @param type
	 * @param velibNW
	 * @param algType
	 */
	
	public Ride(GPS start, GPS end, String type, MyVelib velibNW, AlgType algType ) {
		this.start=start;
		this.end=end;
		this.velibNW=velibNW;
		this.bicycle = BicycleFactory.createBicycle(type);
		switch (algType) {
			case SHORTEST:
				this.setRidePath(new ShortestPath());
				break;
			case FASTEST:
				this.setRidePath(new FastestPath());
				break;
			case AVOIDPLUS:
				this.setRidePath(new AvoidPlus());
				break;
			case PREFERPLUS:
				this.setRidePath(new PreferPlus());
				break;
		}
		getRidePath().path(start, end, velibNW, bicycle.getType());
		getRidePath().getEndStation().addObserver(this);
	}
	
	/**
	 * if a station's state changes while the user is moving, the observer/observable design rerun the path() method to change the EndStation accordingly.
	 */
	
	@Override
	public void update(Observable o, Object arg) {
			getRidePath().path(start,end,getVelibNW(),bicycle.getType());
		
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

	public PathFinder getRidePath() {
		return ridePath;
	}

	public void setRidePath(PathFinder ridePath) {
		this.ridePath = ridePath;
	}

	public MyVelib getVelibNW() {
		return velibNW;
	}
	
}
