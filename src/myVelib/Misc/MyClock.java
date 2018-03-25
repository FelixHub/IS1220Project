package myVelib.Misc;

public class MyClock {
	
	private final long offset;

    public MyClock(){
        offset = System.currentTimeMillis();
    }

    public long getTime()
    {
        return ((System.currentTimeMillis() - offset));
    }
    
    


}
