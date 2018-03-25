package PathAlgorithm;

import myVelib.GPS;
import myVelib.MyVelib;
import myVelib.Bicycle.BicycleType;
import myVelib.Station.Station;

public class UniformRepartition implements PathFinder {

	private Station startStation;
	private Station endStation;

	public UniformRepartition() {
		super();
	}

	@Override
	public void path(GPS start, GPS end, MyVelib velibNW, BicycleType type) {
		
		Station startS = null;
		Station endS = null;
		double ds=0;
		double de=0;
		for (int i=0; i<velibNW.getStations().size() ;i++) {
			
			double di = Math.sqrt((start.getX()-velibNW.getStations().get(i).getPosition().getX())^2+(start.getY()-velibNW.getStations().get(i).getPosition().getY())^2);
			
			if((ds==0) || ((di < ds) && (velibNW.getStations().get(i).countBicycle(type)!=0))) {
				
				ds=di;
				startS=velibNW.getStations().get(i);
			}
		}
		for (int j=0; j<velibNW.getStations().size() ;j++) {
			
			double dj = Math.sqrt((start.getX()-velibNW.getStations().get(j).getPosition().getX())^2+(start.getY()-velibNW.getStations().get(j).getPosition().getY())^2);

			if((de==0) || ((dj < de) && (velibNW.getStations().get(j).freeParkingSpotsNb()!=0))) {
				
				de=dj;
				endS=velibNW.getStations().get(j);
			}
		}
		this.startStation=startS;
		this.endStation=endS;

	}

	@Override
	public Station getStartStation() {
		return this.startStation;

	}

	@Override
	public Station getEndStation() {

		return this.endStation;
	}

}
