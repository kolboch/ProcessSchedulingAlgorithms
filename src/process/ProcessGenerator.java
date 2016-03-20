package process;

import java.util.Random;
/**
 * generates randomly processes with specified parameter's borders
 * @author Karol
 *
 */
public class ProcessGenerator {
	
	Random rand = new Random();
	
	private int maxProcessTime;
	private int minProcessTime;
	
	private static int approachTime=0;
	/**
	 * ProcessGenerator constructor, takes as parameters two Integers
	 * @param int maxPT sets maxProcessTime border
	 * @param int minPT sets minProcessTime border
	 */
	public ProcessGenerator(int maxPT, int minPT){
		this.maxProcessTime = maxPT;
		this.minProcessTime = minPT;
	}
	
	/**
	 * generate process with random processTime from specified borders
	 * @return Process
	 */
	public Process generateProcess(){
		
		int processTime = rand.nextInt(maxProcessTime-minProcessTime)+ minProcessTime;
		
		Process a = new Process(processTime , approachTime++);
		
		return a;
	}
	/**
	 * generates random processes with specified processTime borders
	 * @param numberOfProcesses specified number of Processes in returned array
	 * @return Process[]
	 */
	public Process[] generateProcesses(int numberOfProcesses){
		Process[]arrayOfProcesses = new Process[numberOfProcesses];
		
		for(int i=0; i<arrayOfProcesses.length; i++){
			arrayOfProcesses[i] = generateProcess();
		}
		
		return arrayOfProcesses;
	}
	/**
	 * sets new borders for processTime for generated processes
	 * @param newMax new maxProcessTime
	 * @param newMin new minProcessTime
	 */
	public void changeBorders(int newMax, int newMin){
		this.maxProcessTime  = newMax;
		this.minProcessTime = newMin;
	}
}
