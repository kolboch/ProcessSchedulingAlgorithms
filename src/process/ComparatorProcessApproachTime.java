package process;

import java.util.Comparator;

public class ComparatorProcessApproachTime implements Comparator<Process>{

	@Override
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
