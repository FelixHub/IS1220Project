package myVelib;

public class Clock {
	
	private final long offset;

    public Clock(){
        offset = System.currentTimeMillis();
    }

    public long getTime()
    {
        return ((System.currentTimeMillis() - offset));
    }
    
    


}
