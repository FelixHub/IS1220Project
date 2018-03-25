package myVelib.PathAlgorithm;

import myVelib.MyVelib;
import myVelib.Bicycle.BicycleType;
import myVelib.Misc.GPS;
import myVelib.Station.Station;
import java.lang.Math;

public class ShortestPath implements PathFinder {
	
	private Station startStation;
	private Station endStation;
	
	
	
	public ShortestPath() {
		super();
	}

	@Override
	public void path(GPS start, GPS end, MyVelib velibNW, BicycleType type) {
		
		double d=0;
		Station startS = null;
		Station endS = null;
		for (int i=0; i<velibNW.getDistanceMap().length ;i++) {
			
			for (int j=0; j< velibNW.getDistanceMap()[i].length ;j++) {
				
				double di = Math.sqrt((start.getX()-velibNW.getStations().get(i).getPosition().getX())^2+(start.getY()-velibNW.getStations().get(i).getPosition().getY())^2);
				double df = Math.sqrt((end.getX()-velibNW.getStations().get(j).getPosition().getX())^2+(end.getY()-velibNW.getStations().get(j).getPosition().getY())^2);
				
				if((d==0) || ((di+df+velibNW.getDistanceMap()[i][j] < d) 
						&& (i!=j) 
						&& (velibNW.getStations().get(j).freeParkingSpotsNb()!=0) 
						&& (velibNW.getStations().get(i).countBicycle(type)!=0) 
						&& (velibNW.getStations().get(i).getState()=="ONSERVICE") 
						&& (velibNW.getStations().get(j).getState()=="ONSERVICE"))) {
					
					startS=velibNW.getStations().get(i);
					endS=velibNW.getStations().get(j);
					d = di+df+velibNW.getDistanceMap()[i][j];
					
				}
			}
		this.startStation=startS;
		this.endStation=endS;
		
		}
	}

	@Override
	public Station getStartStation() {
		return startStation;
	}

	@Override
	public Station getEndStation() {
		return endStation;
	}
			
					
}


