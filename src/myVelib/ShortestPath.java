package myVelib;

import java.util.ArrayList;

import myVelib.Station.Station;

public class ShortestPath implements PathFinder {
	
	public Station startStation;
	public Station endStation;
	

	@Override
	public void Path(GPS start, GPS end, MyVelib velibNW) {
		
		double d=0;
		for (int i=0; i<velibNW.getDistanceMap().length ;i++) {
			
			for (int j=i; j< velibNW.getDistanceMap()[i].length ;j++) {
				
				double di = Math.sqrt((start.getX()-velibNW.getStations().get(i).getPosition().getX())^2+(si.getPosition().getY()-sj.getPosition().getY())^2);
				double df = Math.sqrt((si.getPosition().getX()-sj.getPosition().getX())^2+(si.getPosition().getY()-sj.getPosition().getY())^2);
				if(d==0 || distances[i][j]<d) {
					
				}
			}
		}
	}
	
					
					
					
					
					
					
}

