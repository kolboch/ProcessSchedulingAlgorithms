package resource;

import java.util.Queue;
import java.util.PriorityQueue;
import process.Process;
import process.ProcessGenerator;

/**
 * main function is performing different algorithms on delivered processes:
 * RR - round robin, SJF - shortest job first, FCFS- first come first served
 * uses build Timer class to measure data for analysis
 * @author Karol
 *
 */
public class Resource {
	
	private Timer timer;
	private ProcessGenerator procGen1;
	private Queue<Process> initial;
	private Queue<Process> dispossed;
	
	public Resource(){
		timer = new Timer();
		procGen1 = new ProcessGenerator(50,10,200);
	}
	
	
	
	
}
