package testPackage;
import process.Process;
public class ProcessTest {
	
	public static void main(String[]args){
		
		Process a = new Process();
		Process b= new Process(27, 12);
		
		System.out.print(a);
		System.out.print(b);
		
		b.addAwaitTime(10);
		System.out.println(b.isDone());
		
		System.out.print(b);
		
	}
}
