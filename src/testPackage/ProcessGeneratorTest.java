package testPackage;
import process.ProcessGenerator;
import process.Process;
public class ProcessGeneratorTest {
	
	public static void main(String[]args){
		
		ProcessGenerator procGen = new ProcessGenerator(20,10,100);
		
		Process[]procArray = new Process[10];
		procArray = procGen.generateProcesses(10);
		
		for(Process p: procArray){
			System.out.print(p.toString());
		}
	}
}
