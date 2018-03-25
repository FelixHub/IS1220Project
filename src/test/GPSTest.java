package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myVelib.Misc.GPS;

class GPSTest {


	@Test
	void testGetX() {
		GPS p = new GPS(2,3);
		assertTrue(p.getX()==2);
		
	}

	@Test
	void testSetX() {
		GPS p = new GPS(2,3);
		p.setX(1);
		assertTrue(p.getX()==1);
	}

	@Test
	void testGetY() {
		GPS p = new GPS(2,3);
		assertTrue(p.getY()==3);
	}

	@Test
	void testSetY() {
		GPS p = new GPS(2,3);
		p.setY(1);
		assertTrue(p.getY()==1);
	}

}
