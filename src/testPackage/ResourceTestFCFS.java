package testPackage;

import resource.Resource;
import process.ProcessGenerator;


public class ResourceTestFCFS {

	public static void main(String[]args){
		
		ProcessGenerator procGen = new ProcessGenerator(40,10,1000);
		Resource resource = new Resource(procGen);
		Resource resource2 = new Resource(procGen);
		Resource resource3 = new Resource(procGen);
		
		resource.simulateProcesses(100);
		resource2.simulateProcesses(100);
		resource3.simulateProcesses(100);
		
		resource.FCFSAlgorithm();
		resource2.SJFAlgorithm();
		
		resource3.RRAlgorithm(10);
		System.out.println("FCFS: " + resource.printAverageWaitingTime());
		System.out.println("SJF: " + resource2.printAverageWaitingTime());
		System.out.println("RR: " + resource3.printAverageWaitingTime());
		
	}
}
