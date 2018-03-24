package myVelib;

import myVelib.Station.Station;

public class FastestPath implements PathFinder {
	
	private Station startStation;
	private Station endStation;
	

	@Override
	public void Path(GPS start, GPS end, MyVelib velibNW) {
		
		double d=0;
		Station startS = null;
		Station endS = null;
		for (int i=0; i<velibNW.getDistanceMap().length ;i++) {
			
			for (int j=0; j< velibNW.getDistanceMap()[i].length ;j++) {
				
				double di = Math.sqrt((start.getX()-velibNW.getStations().get(i).getPosition().getX())^2+(start.getY()-velibNW.getStations().get(i).getPosition().getY())^2);
				double df = Math.sqrt((end.getX()-velibNW.getStations().get(j).getPosition().getX())^2+(end.getY()-velibNW.getStations().get(j).getPosition().getY())^2);
				
				if((d==0) || ((di+df+velibNW.getDistanceMap()[i][j] < d) && i!=j)) {
					
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
		
		
		
		
		
		
	





	


