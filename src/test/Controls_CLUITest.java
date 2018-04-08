package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import myVelib.Controls_CLUI;
import myVelib.MyVelib;
import myVelib.Misc.User;
import myVelib.Station.Station;

class Controls_CLUITest {
	Controls_CLUI c = new Controls_CLUI();
	MyVelib myvelib = new MyVelib(new ArrayList<Station>(),new ArrayList<User>(),10,"ok");
	@Test
	void testMain() {
		fail("Not yet implemented");
	}

	@Test
	void testGetMyVelib() {
		fail("Not yet implemented");
	}

	@Test
	void testGetUser() {
		fail("Not yet implemented");
	}

	@Test
	void testGetStation() {
		fail("Not yet implemented");
	}

}
