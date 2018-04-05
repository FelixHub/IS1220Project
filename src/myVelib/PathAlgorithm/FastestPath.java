package myVelib.PathAlgorithm;

import myVelib.MyVelib;
import myVelib.Bicycle.BicycleType;
import myVelib.Misc.GPS;
import myVelib.Station.Station;
/**
 * 
 * It implements PathFinder in order to find Start/EndStation with the fastest path algorithm.
 *
 */
public class FastestPath implements PathFinder {
	
	private Station startStation;
	private Station endStation;
	
	
	

	public FastestPath() {
		super();
	}

	@Override
	public void path(GPS start, GPS end, MyVelib velibNW, BicycleType type) {
		
		double t=0;
		Station startS = null;
		Station endS = null;
		//calcul de StartStation et EndStation par brute force, les if supplémentaires servent à éviter les cas pathologiques (station pleine, etc..)
		// A l'exception que le type de vélo est ici important puisque la vitesse est différente.
		for (int i=0; i<velibNW.getDistanceMap().length ;i++) {
			
			for (int j=0; j< velibNW.getDistanceMap()[i].length ;j++) {
				
				double di = Math.sqrt(Math.pow(start.getX()-velibNW.getStations().get(i).getPosition().getX(),2)+Math.pow(start.getY()-velibNW.getStations().get(i).getPosition().getY(),2));
				double df = Math.sqrt(Math.pow(end.getX()-velibNW.getStations().get(j).getPosition().getX(),2)+Math.pow(end.getY()-velibNW.getStations().get(j).getPosition().getY(),2));
				
				if (type.equals(BicycleType.MECHANICAL)){
					
					if((t==0) || ((di+df)*9/10+velibNW.getDistanceMap()[i][j]*6/25 < t) && 
							(i!=j) && (velibNW.getStations().get(j).freeParkingSpotsNb()!=0) && 
							(velibNW.getStations().get(i).countBicycle(type)!=0)) {
					
						startS=velibNW.getStations().get(i);
						endS=velibNW.getStations().get(j);
						t = (di+df)*9/10+velibNW.getDistanceMap()[i][j]*6/25;
					}
				}

				if (type.equals(BicycleType.ELECTRIC)){
							
					if((t==0) || ((di+df)*9/10+velibNW.getDistanceMap()[i][j]*9/50 < t) && (i!=j) && (velibNW.getStations().get(j).freeParkingSpotsNb()!=0) && (velibNW.getStations().get(i).countBicycle(type)!=0)) {
							
						startS=velibNW.getStations().get(i);
						endS=velibNW.getStations().get(j);
						t = (di+df)*9/10+velibNW.getDistanceMap()[i][j]*9/50;
			
						
					}
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
		
		
		
		
		
		
	





	


