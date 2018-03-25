package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myVelib.Misc.MyClock;

class MyClockTest {

	@Test
	void testGetTime() {
		MyClock clock=new MyClock();
		assertNotNull(clock.getTime());
	}

}
