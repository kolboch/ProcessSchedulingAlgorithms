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
	
	public Process (int processTime, int approachTime){
		this.processTime = processTime;
		this.approachTime = approachTime;
		this.wasDoneTime =0;
	}
	
	public Process(){
		this.processTime = 0 ;
		this.approachTime = 0;
	}
	
	public int getProcessTime(){
		return processTime;
	}
	
	public int getApproachTime(){
		return approachTime;
	}
	
	public int getWasDoneTime(){
		return wasDoneTime;
	}
	
	public void wasDoneBy(int time){
		this.wasDoneTime += time;
	}
	public void resetWasDoneBy(){
		this.wasDoneTime =0;
	}
	
	public boolean isDone(){
		return processTime <= 0;
	}
}
