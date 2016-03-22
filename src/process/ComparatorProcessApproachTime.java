package process;

import java.util.Comparator;
/**
 * Compare two processes by their approachTime
 * @author Karol
 *
 */
public class ComparatorProcessApproachTime implements Comparator<Process>{

	@Override
	/**
	 * @return 1 if p1 > p2, -1 if p1 < p2, else 0
	 */
	public int compare(Process p1, Process p2) {
		if(p1.getApproachTime() > p2.getApproachTime()){
			return 1;
		}
		else if(p1.getApproachTime() < p2.getApproachTime())
			return -1;
		else
			return 0;
	}
	
	

}
