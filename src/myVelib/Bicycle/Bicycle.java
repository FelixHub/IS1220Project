package myVelib.Bicycle;

import java.util.Random;
/**
 * 
 * the abstract class bicycle has two subclasses ElectricBicycle and MechanicalBicycle.
 * 
 */

public abstract class Bicycle {
	protected int ID;
	static int count_ID;
	public BicycleType type;
	
	/**
		 *  return the ID of the Bicycle, which is unique.
		 */
	public int getID() {
		
		return ID;
	}
	
	/**
		 * return the type of the bicycle, ELECTRIC or MECHANICAL.
		 */
	public BicycleType getType() {
		
		return type;
	}
	
	/**
		 * this constructor is only to be used at the initialization of a myVelib world. 
		 * It randomly generate 70% mechanical bicycle and 30% electrical Bicycle as asked in the project description.
		 */
	public static Bicycle randomBicycle() {
		
		Random rn = new Random();
		int randomNum = rn.nextInt(10);
		if ( randomNum < 8 ) {
			return new MechanicalBicycle();
		}
		else {
			return new ElectricBicycle();
		}
	}
}
