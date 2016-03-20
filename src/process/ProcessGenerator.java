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
	
	public ProcessGenerator(int maxPT, int minPT){
		this.maxProcessTime = maxPT;
		this.minProcessTime = minPT;
	}
	
	public Process generateProcess(){
		
		int processTime = rand.nextInt(maxProcessTime-minProcessTime)+ minProcessTime;
		
		Process a = new Process(processTime , approachTime++);
		
		return a;
	}
}
