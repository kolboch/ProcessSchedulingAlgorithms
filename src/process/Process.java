package process;



/**
 * process Class, has information about process,waiting,and come time.
 * @author Karol
 *
 */
public class Process {
	
	private int processTime;
	private int approachTime;
	private int wasDoneTime;
	private int awaitTime;
	
	/**
	 * takes two integers as parameters
	 * @param processTime sets processTime
	 * @param approachTime sets approachTime
	 */
	public Process (int processTime, int approachTime){
		this.processTime = processTime;
		this.approachTime = approachTime;
		this.wasDoneTime =0;
		this.awaitTime =0;
	}
	/**
	 * default
	 * sets processTime to 0
	 * sets approachTIme to 0
	 * sets wasDoneTime to 0
	 * sets awaitTime to 0
	 */
	public Process(){
		this.processTime = 0 ;
		this.approachTime = 0;
		this.wasDoneTime =0;
		this.awaitTime =0;
	}
	/**
	 * 
	 * @return integer value of processTime
	 */
	public int getProcessTime(){
		return processTime;
	}
	/**
	 * 
	 * @return integer value of approachTime
	 */
	public int getApproachTime(){
		return approachTime;
	}
	/**
	 * 
	 * @return integer value of how long process was done in last run
	 */
	public int getWasDoneTime(){
		return wasDoneTime;
	}
	/**
	 * adds integer parameter to wasDoneTime
	 * @param time- integer
	 */
	public void wasDoneBy(int time){
		this.wasDoneTime += time;
	}
	/**
	 * sets wasDoneTime to 0
	 */
	public void resetWasDoneBy(){
		this.wasDoneTime =0;
	}
	/**
	 * 
	 * @return true if processTime <= 0 , otherwise false
	 */
	public boolean isDone(){
		return processTime <= 0;
	}
	/**
	 * 
	 * @return integer value of Process awaitTime 
	 */
	public int getAwaitTime(){
		return this.awaitTime;
	}
	/**
	 * increases awaitTime by integer parameter value
	 * @param time 
	 */
	public void addAwaitTime(int time){
		this.awaitTime += time;
	}
	/**
	 * @return String containing information about processTime, wasDoneTime, approachTime and awaitTime
	 */
	public String toString(){
		return String.format("ProcessTime:%4d WasDoneTime:%3d approachTime:%3d awaitTime:%4d%n",processTime, wasDoneTime, approachTime, awaitTime);
	}
	/**
	 * reduces processTime by time value
	 * increases wasDoneTime by time value
	 * @param int time
	 */
	public void doProcessFor(int time){
		this.processTime -= time;
		this.wasDoneTime += time;
	}
	/**
	 * reduces procesTime by 1 and
	 * increases wasDoneTime by 1
	 */
	public void doProcessForTimeUnit(){
		this.processTime -= 1;
		this.wasDoneTime += 1;
	}
	
	
	
}
