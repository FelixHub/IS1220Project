package myVelib.PathAlgorithm;

import myVelib.MyVelib;
import myVelib.Bicycle.BicycleType;
import myVelib.Misc.GPS;
import myVelib.Station.StandardStation;
import myVelib.Station.Station;
/**
 * 
 * It implements PathFinder in order to find Start/EndStation with the shortest path algorithm and the avoid plus option.
 *
 */
public class AvoidPlus implements PathFinder {

	private Station startStation;
	private Station endStation;

	public AvoidPlus() {
		super();
	}

	@Override
	public void path(GPS start, GPS end, MyVelib velibNW, BicycleType type) {
		
		Station startS = null;
		Station endS = null;
		double ds=0;
		double de=0;
		//calcul de StartStation et EndStation par brute force, les if suppl�mentaires servent � �viter les cas pathologiques (station pleine, etc..)
		for (int i=0; i<velibNW.getStations().size() ;i++) {
			
			double di = Math.sqrt(Math.pow(start.getX()-velibNW.getStations().get(i).getPosition().getX(),2)+Math.pow(start.getY()-velibNW.getStations().get(i).getPosition().getY(),2));
			
			if((ds==0) || ((di < ds) 
					&& (velibNW.getStations().get(i).countBicycle(type)!=0)
					&& (velibNW.getStations().get(i).getState()=="ONSERVICE"))) {
				
				ds=di;
				startS=velibNW.getStations().get(i);
			}
		}
		for (int j=0; j<velibNW.getStations().size() ;j++) {
			
			double dj = Math.sqrt(Math.pow(start.getX()-velibNW.getStations().get(j).getPosition().getX(),2)+Math.pow(start.getY()-velibNW.getStations().get(j).getPosition().getY(),2));

			if((de==0) || ((dj < de) 
					&& (velibNW.getStations().get(j).freeParkingSpotsNb()!=0) 
					&& (velibNW.getStations().get(j) instanceof StandardStation)
					&& (velibNW.getStations().get(j).getState()=="ONSERVICE"))) {
				
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
