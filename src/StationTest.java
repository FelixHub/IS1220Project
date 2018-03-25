import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myVelib.Misc.GPS;
import myVelib.Station.StandardStation;
import myVelib.Station.Station;

class StationTest {

	@Test
	public void testCreateStation() {
		GPS position = new GPS(30,40);
		StandardStation myStation = new StandardStation(
				position, "ONLINE", 10);
		
		String result = myStation.getState();
		String expected = "ONLINE";
		
		assertEquals(expected,result);
	}

}
