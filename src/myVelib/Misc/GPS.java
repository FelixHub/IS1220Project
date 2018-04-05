package myVelib.Misc;

import java.util.Random;
/**
 * This class is used to set the position of a user or a station on a squared grid with integer coordinates
 * 
 *
 */
public class GPS {
	private int x;
	private int y;
	public GPS(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public GPS(int sup) {
		Random r = new Random();
		this.x =r.nextInt(sup);
		this.y=r.nextInt(sup);
		
	}
	
	public String toString() {
		return "("+x+","+y+")";
	}
}
