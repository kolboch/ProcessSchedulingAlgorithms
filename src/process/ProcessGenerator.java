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
	private int maxApproachTime;
	/**
	 * ProcessGenerator constructor, takes as parameters two Integers
	 * @param int maxPT sets maxProcessTime border
	 * @param int minPT sets minProcessTime border
	 * @throws IllegalArgumentException
	 */
	public ProcessGenerator(int maxPT, int minPT, int maxApproachTime)throws IllegalArgumentException{
		this.maxProcessTime = maxPT;
		this.minProcessTime = minPT;
		this.maxApproachTime = maxApproachTime;
		if(minPT>maxPT)
			throw new IllegalArgumentException("min ProcessTime cannot be higher than max ProcessTime");
	}
	
	/**
	 * generate process with random processTime from specified borders
	 * @return Process
	 */
	public Process generateProcess(){
		
		int processTime = rand.nextInt(maxProcessTime-minProcessTime)+ minProcessTime;
		
		Process a = new Process(processTime , rand.nextInt(maxApproachTime));
		
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
	public void changeBorders(int newMax, int newMin, int newApproachMax){
		this.maxProcessTime  = newMax;
		this.minProcessTime = newMin;
		this.maxApproachTime = newApproachMax;
	}
}
