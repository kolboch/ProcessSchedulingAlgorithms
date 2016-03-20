package resource;
/**
 * performs measuring actions like timeStart, timeStop, periodOfTime, average time;
 * @author Karol
 *
 */
public class Timer {
	
	private int currentTime;
	/**
	 * Timer constructor
	 * sets currentTime to 0
	 */
	public Timer(){
		this.currentTime =0;
	}
	/**
	 * return currentTime
	 * @return Integer value of currentTime
	 */
	public int getCurrentTime(){
		return currentTime;
	}
	/**
	 * method adds elapsed time to currentTime
	 * @param timeElapsed, value added to currentTime
	 */
	public void elapsed(int timeElapsed){
		this.currentTime += timeElapsed;
	}
	
	public void resetTime(){
		this.currentTime =0;
	}
}
