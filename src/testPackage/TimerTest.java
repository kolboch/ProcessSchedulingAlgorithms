package testPackage;
import resource.*;
public class TimerTest {
	
	public static void main(String[]args){
		
		Timer  a = new Timer();
		
		System.out.print("Time after initialization: ");
		System.out.println(a.getCurrentTime());
		
		a.elapsed(100);
		System.out.print("Time after 100 units elapsed: ");
		System.out.println(a.getCurrentTime());
		
		a.resetTime();
		System.out.print("Time after reset: ");
		System.out.println(a.getCurrentTime());
	}
	
}
