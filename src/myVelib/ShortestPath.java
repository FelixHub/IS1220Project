package myVelib;

import java.util.ArrayList;

import myVelib.Station.Station;
import java.lang.Math;

public class ShortestPath implements PathFinder {
	
	public Station startStation;
	public Station endStation;
	
	

	@Override
	public void Path(GPS start, GPS end, double [][] distances) {
		double d=0;
		for (int i=0; i<distances.length ;i++) {
			for (int j=i; j< distances[i].length ;j++) {
				double di = Math.sqrt((si.getPosition().getX()-sj.getPosition().getX())^2+(si.getPosition().getY()-sj.getPosition().getY())^2);
				if(d==0 || distances[i][j]<d) {
					
					
					
					
					
					
				}