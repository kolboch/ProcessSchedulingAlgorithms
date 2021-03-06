package testPackage;
import process.ProcessGenerator;
import resource.Resource;
import output.OutputToFile;

public class ResourceTestSJF {

	public static void main(String[]args){
		
		ProcessGenerator procGen = new ProcessGenerator(40,10,50);
		
		Resource resource = new Resource(procGen);
		
		resource.simulateProcesses(10);
		
		resource.SJFAlgorithm();
		
		resource.printDoneData();
		
		OutputToFile out = new OutputToFile();
		
		out.dataToFile("D:\\javaeclipseprog\\Tutoriale\\TestDocumentOutput.txt", resource.printAverageWaitingTime());
		
		
	}
}
