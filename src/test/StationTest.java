package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myVelib.Bicycle.BicycleFactory;
import myVelib.Bicycle.BicycleType;
import myVelib.Misc.GPS;
import myVelib.Station.Station;
import myVelib.Station.StationFactory;

class StationTest {

	@Test
	void testCountBicycle() {
		Station s = StationFactory.createStation("PLUS",new GPS(20),15);
		s.getParkingSlots()[0] = BicycleFactory.createBicycle("MECHANICAL");
		int x = s.countBicycle(BicycleType.MECHANICAL);
		
		assertTrue(x == 1);
	}

}
