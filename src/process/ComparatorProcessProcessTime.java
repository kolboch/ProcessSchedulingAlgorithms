package process;

import java.util.Comparator;
/**
 * 
 * @author Karol
 * Compare two Processes by their ProcessTime
 */
public class ComparatorProcessProcessTime implements Comparator<Process>{
	
	@Override
	/**
	 * @return 1 if p1.processTime > p2.processTime , -1 if p1 < p2 , else 0
	 */
	public int compare(Process p1, Process p2) {
		if(p1.getProcessTime() > p2.getProcessTime()){
			return 1;
		}
		else if(p1.getProcessTime() < p2.getProcessTime())
			return -1;
		else
			return 0;
	}
	
	
}
