package process;

import java.util.Comparator;

public class ComparatorProcessProcessTime implements Comparator<Process>{

	@Override
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
