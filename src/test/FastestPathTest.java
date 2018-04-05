package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myVelib.MyVelib;
import myVelib.Bicycle.BicycleType;
import myVelib.Misc.GPS;
import myVelib.PathAlgorithm.FastestPath;
import myVelib.PathAlgorithm.PathFinder;
import myVelib.PathAlgorithm.ShortestPath;
import myVelib.Station.Station;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import myVelib.MyVelib;
import myVelib.Bicycle.BicycleFactory;
import myVelib.Bicycle.BicycleType;
import myVelib.Bicycle.ElectricBicycle;
import myVelib.Bicycle.MechanicalBicycle;
import myVelib.Misc.GPS;
import myVelib.Misc.User;
import myVelib.PathAlgorithm.PathFinder;
import myVelib.PathAlgorithm.ShortestPath;
import myVelib.Station.PlusStation;
import myVelib.Station.StandardStation;
import myVelib.Station.Station;
class FastestPathTest {

	@Test
	void testPath() {
		ArrayList<Station> stations = new ArrayList<Station>();
		ArrayList<User> users = new ArrayList<User>();
		BicycleFactory myFactory= new BicycleFactory();
		
		
		GPS GPS1 = new GPS(1,1);
		Station s1 = new StandardStation(GPS1,3);
		s1.getParkingSlots()[0] = new ElectricBicycle();
		s1.getParkingSlots()[1] = new MechanicalBicycle();
		
		GPS GPS2 = new GPS(4,4);
		Station s2 = new StandardStation(GPS2,3);
		s2.getParkingSlots()[0] = new ElectricBicycle();
		s2.getParkingSlots()[1] = new MechanicalBicycle();
		
		GPS GPS3 = new GPS(6,6);
		Station p3 = new PlusStation(GPS3,3);
		p3.getParkingSlots()[0] = new ElectricBicycle();
		p3.getParkingSlots()[1] = new MechanicalBicycle();
		
		GPS GPS4 = new GPS(9,9);
		Station p4 = new PlusStation(GPS4,3);
		p4.getParkingSlots()[0] = new ElectricBicycle();
		p4.getParkingSlots()[1] = new MechanicalBicycle();
		
		stations.add(s1);
		stations.add(s2);
		stations.add(p3);
		stations.add(p4);
		
		MyVelib city=new MyVelib(stations,users,10,"myVelibTest");
		
		GPS start = new GPS(0,0);
		GPS end = new GPS (8,8);
		PathFinder spath= new FastestPath();
		spath.path(start, end, city, BicycleType.MECHANICAL);
		System.out.println(spath.getStartStation());
		System.out.println(spath.getEndStation());
		assertTrue(spath.getStartStation()==s1 && spath.getEndStation()==p4);
	}

}
