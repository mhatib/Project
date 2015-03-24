package gamehelpers;

import com.badlogic.gdx.utils.TimeUtils;

public class Timer{
    private long start;
    private long secsToWait;
    private boolean timerIsOn;

    public Timer(long secsToWait)
    {
        this.secsToWait = secsToWait;
        this.timerIsOn = false;
    }

    public void start()
    {
        start = TimeUtils.millis() / 1000;
        timerIsOn = true;
    }

    public boolean hasCompleted()
    {
    	if(timerIsOn){
    		if(TimeUtils.millis() / 1000 - start >= secsToWait){
    			return true;
    		} else 
    			return false;
    	}
    	else
    		return false;
    }
    
    public float getTimeElapsed(){
    	if(timerIsOn)
    		return (float) (TimeUtils.millis() / 1000.0 - start);
    	else 
			return secsToWait;
		
    }
    
    public boolean getTimerIsOn(){
    	return timerIsOn;
    }
    
    public void stop(){
    	timerIsOn = false;
    	start = -1;
    }
}