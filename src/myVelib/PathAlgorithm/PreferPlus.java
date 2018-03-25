package myVelib.PathAlgorithm;

import myVelib.MyVelib;
import myVelib.Bicycle.BicycleType;
import myVelib.Misc.GPS;
import myVelib.Station.PlusStation;
import myVelib.Station.StandardStation;
import myVelib.Station.Station;

public class PreferPlus implements PathFinder {

	private Station startStation;
	private Station endStation;
 
	public PreferPlus() {
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
			
			if((ds==0) || ((di < ds) 
					&& (velibNW.getStations().get(i).countBicycle(type)!=0)
					&& (velibNW.getStations().get(i).getState()=="ONSERVICE"))) {
				
				ds=di;
				startS=velibNW.getStations().get(i);
			}
		}
		for (int j=0; j<velibNW.getStations().size() ;j++) {
			
			double dj = Math.sqrt((end.getX()-velibNW.getStations().get(j).getPosition().getX())^2+(end.getY()-velibNW.getStations().get(j).getPosition().getY())^2);

			if((de==0) || ((dj < de) 
					&& (velibNW.getStations().get(j).freeParkingSpotsNb()!=0)
					&& (velibNW.getStations().get(j).getState()=="ONSERVICE"))) {
				
				de=dj;
				endS=velibNW.getStations().get(j);
			}
		}
		
		double d=0;
		for (int k=0; k<velibNW.getStations().size() ;k++) {
			
			double dk = Math.sqrt((end.getX()-velibNW.getStations().get(k).getPosition().getX())^2+(end.getY()-velibNW.getStations().get(k).getPosition().getY())^2);
		
			if ((d==0) || ((dk < d) && (d<=1.1*de)
					&& (velibNW.getStations().get(k).freeParkingSpotsNb()!=0)
					&& (velibNW.getStations().get(k).getState()=="ONSERVICE")
					&& (velibNW.getStations().get(k) instanceof PlusStation))) {
				
				d=dk;
				endS=velibNW.getStations().get(k);
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
