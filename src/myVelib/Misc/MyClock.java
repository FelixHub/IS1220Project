package myVelib.Misc;

/**
 * 
 * The class used to simulate time.
 *
 */
public class MyClock {
	
	private final long offset;

    public MyClock(){
        offset = System.currentTimeMillis();
    }

    public long getTime()
    {
        return ((System.currentTimeMillis() - offset)*60/1000);
    }
    
    


}
