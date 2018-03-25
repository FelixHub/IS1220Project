package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myVelib.Misc.GPS;
import myVelib.PathAlgorithm.ShortestPath;

class ShortestPathTest {

	@Test
	void testPath() {
		GPS start=new GPS(2,3);
		GPS end = new GPS()
		
		
		ShortestPath stationFinder =new ShortestPath();
		
		stationFinder.path(start, end, velibNW, type);
	}

	@Test
	void testGetStartStation() {
		fail("Not yet implemented");
	}

	@Test
	void testGetEndStation() {
		fail("Not yet implemented");
	}

}
