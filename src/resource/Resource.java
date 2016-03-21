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
	
	public Resource(){
		this(new ProcessGenerator(50,10,200));	
	}
	
	public Resource(ProcessGenerator pg){
		this.procGen1 = pg;
		timer = new Timer();
		initial = new PriorityQueue<Process>(new ComparatorProcessApproachTime());
		dispossed = new PriorityQueue<Process>(new ComparatorProcessProcessTime());
		currentProcess = null;
	}
	
	public void simulateProcesses(int numberOfProcesses){
		Process[] arrayOfProc = procGen1.generateProcesses(numberOfProcesses);
		for(Process p: arrayOfProc)
			initial.add(p);
		
		doneProcesses = new Process[numberOfProcesses];
	}
	
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
	
	private void disposseProcess() throws NullPointerException{
		if(currentProcess != null){
			dispossed.add(currentProcess);
		}
		else
			throw new NullPointerException("Cannot disposse null Process");
			
	}
	
	private void moveCurrentToDone() throws IndexOutOfBoundsException{
		if(doneCounter< doneProcesses.length)
			doneProcesses[doneCounter++] = currentProcess;
		else
			throw new IndexOutOfBoundsException("IsDone is full");
	}
	
	public void FCFSAlgorithm(){
		resetTimer();
		while(!initial.isEmpty()){
			if(isNextAvailable()){
				currentProcess = initial.poll();
				
				while(!currentProcess.isDone()){
					currentProcess.doProcessForTimeUnit();
					addWaitingTime(1);
					timer.elapsed(1);
				}
				
				moveCurrentToDone();
			}
			else
				timer.elapsed(1);
		}
	}
	
	public void RRAlgorithm(int timeQuantum){
		resetTimer();
		
		while(!initial.isEmpty() || !dispossed.isEmpty()){
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
	
	public void SJFAlgorithm(){
		
		resetTimer();
		while(!initial.isEmpty() || !dispossed.isEmpty()){
			
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
						else 
							dispossed.add(temp);
							
					}
				addWaitingTime(1);
				}
				
				
			}
			
			
			
			
		}
	}
	
	private void resetTimer(){
		this.timer.resetTime();
	}
	private boolean isShorter(Process p){
		return currentProcess.getProcessTime() + currentProcess.getWasDoneTime() > p.getProcessTime() + p.getWasDoneTime();
	}
	private void getNextProcess() throws NullPointerException{
		try{
			currentProcess = initial.poll();
		}catch(NullPointerException e){
			e.printStackTrace();
		}
	}
	
	private boolean isNextAvailable(){
		
		if(!initial.isEmpty() &&  initial.peek().getApproachTime() >= timer.getCurrentTime())
			return true;
		else
			return false;
	}
	
	private void addWaitingTime(int time){
		if(initial!=null){
			for(Process p: initial){
				if(p.getApproachTime() > timer.getCurrentTime())
					p.addAwaitTime(time);
			}
		}
		if(dispossed != null){
			for(Process p: dispossed){
				if(p.getApproachTime() > timer.getCurrentTime())
					p.addAwaitTime(time);
			}
		}
	}
	
	
	
	
}
