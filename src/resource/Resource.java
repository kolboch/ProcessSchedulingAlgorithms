package resource;

import java.util.Queue;
import java.util.PriorityQueue;
import process.Process;
import process.ProcessGenerator;
import process.ComparatorProcessApproachTime;
import process.ComparatorProcessProcessTime;
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
	private Process[]doneProcesses;
	private int doneCounter=0;
	private Process currentProcess;
	/**
	 * call Resource(ProcessGenerator) constructor with new ProcessGenerator(50,10,200) as default
	 */
	public Resource(){
		this(new ProcessGenerator(50,10,200));	
	}
	/**
	 * constructs new Resource with given ProcessGenerator 
	 * @param pg ProcessGenerator
	 */
	public Resource(ProcessGenerator pg){
		this.procGen1 = pg;
		timer = new Timer();
		initial = new PriorityQueue<Process>(new ComparatorProcessApproachTime());
		dispossed = new PriorityQueue<Process>(new ComparatorProcessProcessTime());
		currentProcess = null; 
	}
	/**
	 * simulate Processes for Resource specified by ProcessGenerator
	 * @param numberOfProcesses - how many processes will be generated
	 */
	public void simulateProcesses(int numberOfProcesses){
		Process[] arrayOfProc = procGen1.generateProcesses(numberOfProcesses);
		for(Process p: arrayOfProc)
			initial.add(p);
		
		doneProcesses = new Process[numberOfProcesses];
	}
	public String printAverageWaitingTime(){
		double average = 0;
		for(Process p : doneProcesses){
			if(p != null){
				average += p.getAwaitTime();
			}
		}
		average /= doneProcesses.length;
		return String.format("%6.1f%n" , average);
	}
	
	/**
	 * prints data of done processes, prints average waiting time of processes
	 */
	public void printDoneData(){
		double averageWaitingTime=0;
		for(Process p : doneProcesses){
			if(p != null){
			averageWaitingTime += p.getAwaitTime();
			System.out.print(p.toString());
			}
		}
		averageWaitingTime /= doneProcesses.length;
		System.out.printf("For:%5d  Processes Average waiting time was:%6.1f%n" , doneProcesses.length, averageWaitingTime);
		
	}
	/**
	 * disposse current process
	 * @throws NullPointerException 
	 */
	private void disposseProcess() throws NullPointerException{
		if(currentProcess != null){
			dispossed.add(new Process(currentProcess));
			currentProcess=null;
		}
		else
			throw new NullPointerException("Cannot disposse null Process");
			
	}
	/**
	 * moves current process to done
	 * @throws IndexOutOfBoundsException
	 */
	private void moveCurrentToDone() throws IndexOutOfBoundsException{
		if(doneCounter< doneProcesses.length){
			doneProcesses[doneCounter] = new Process(currentProcess);
			doneCounter++;
		}
		else
			throw new IndexOutOfBoundsException("IsDone is full");
	}
	/**
	 * performs FCFS algorithm for simulated processes
	 */
	public void FCFSAlgorithm(){
		
		while(!initial.isEmpty()){
			if(isNextAvailable()){
				currentProcess = initial.poll();
				while(!currentProcess.isDone()){
					currentProcess.doProcessForTimeUnit();
					timer.elapsed(1);
					addWaitingTime(1);
				}
				moveCurrentToDone();
			}
			else{
				timer.elapsed(1);
			}
		}
	}
	/**
	 * performs Round Robin algorithm for simulated processes
	 * @param timeQuantum - define time quantum for round robin algorithm
	 */
	public void RRAlgorithm(int timeQuantum){
	
			while(!initial.isEmpty() || !dispossed.isEmpty() || doneCounter < doneProcesses.length){
				if(currentProcess == null){
					if(isNextAvailable())
						currentProcess = initial.poll();
					else if(dispossed.isEmpty())
						timer.elapsed(1);
					else
						currentProcess = dispossed.poll();
				}
				
				else {
					
					if(currentProcess.isDone()){
						moveCurrentToDone();
						if(isNextAvailable()){
							getNextProcess();
						}
						else if(!dispossed.isEmpty())
							currentProcess = dispossed.poll();
					}
					else{
					
					currentProcess.doProcessFor(timeQuantum);
					timer.elapsed(timeQuantum);
					
						if(isNextAvailable()){
							Process temp = initial.poll();
						
							disposseProcess();
							currentProcess = temp;
							
								
						}
						else if(!dispossed.isEmpty()){
							disposseProcess();
							currentProcess = dispossed.poll();
						}
						
					addWaitingTime(timeQuantum);
					}
				}
			}
		}
				
				
			
		
	
	/**
	 * perform Shortest Job First algorithm for simulated processes
	 */
	public void SJFAlgorithm(){
		
		
		while(!initial.isEmpty() || !dispossed.isEmpty() || doneCounter < doneProcesses.length){
			
			if(currentProcess == null){
				if(isNextAvailable())
					currentProcess = initial.poll();
				else if(dispossed.isEmpty())
					timer.elapsed(1);
				else
					currentProcess = dispossed.poll();
			}
			else {
				
				if(currentProcess.isDone()){
					moveCurrentToDone();
					if(isNextAvailable()){
						getNextProcess();
					}
					else if(!dispossed.isEmpty())
						currentProcess = dispossed.poll();
				}
				else{
				
				currentProcess.doProcessForTimeUnit();
				timer.elapsed(1);
				
					if(isNextAvailable()){
						Process temp = initial.poll();
						if(isShorter(temp)){
							disposseProcess();
							currentProcess = temp;
						}
						else {
							dispossed.add(temp);
						}
					}
				addWaitingTime(1);
				}
			}
		}
	}
	
	/**
	 * checks if Process p is shorter than current process
	 * @param p Process to check
	 * @return true if Process p is shorter, else false
	 */
	private boolean isShorter(Process p){
		return currentProcess.getProcessTime() + currentProcess.getWasDoneTime() > p.getProcessTime() + p.getWasDoneTime();
	}
	/**
	 * sets proper process as current
	 * @throws NullPointerException
	 */
	private void getNextProcess() throws NullPointerException{
		try{
			currentProcess = initial.poll();
		}catch(NullPointerException e){
			e.printStackTrace();
		}
	}
	/**
	 * checks if next process is available
	 * @return true if next process is available, else false
	 */
	private boolean isNextAvailable(){
		
		if(!initial.isEmpty() &&  timer.getCurrentTime() >= initial.peek().getApproachTime())
			return true;
		else
			return false;
	}
	
	/**
	 * adds specified time to all already approached processes and waiting for Resource
	 * @param int time - waiting time 
	 */
	private void addWaitingTime(int time){
		if(!initial.isEmpty()){
			
			for(Process p: initial){
				if(timer.getCurrentTime() > p.getApproachTime()){
					p.addAwaitTime(time);
				}
			}
			
		}
		if(!dispossed.isEmpty()){
			
			for(Process p: dispossed){
				if(timer.getCurrentTime() > p.getApproachTime())
					p.addAwaitTime(time);
			}
			
		}
	}
	
	
	
	
}
