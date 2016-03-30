package testPackage;

import resource.Resource;
import process.ProcessGenerator;

public class ResourceTestRR {

	public static void main(String[]args){
		
		ProcessGenerator procGen = new ProcessGenerator(40,10,50);
		
		Resource resource = new Resource(procGen);
		
		resource.simulateProcesses(5);
		
		resource.RRAlgorithm(10);
		
		resource.printDoneData();
		
		
	}
}
