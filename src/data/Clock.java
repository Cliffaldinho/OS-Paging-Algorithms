package data;

import java.util.ArrayList;

//class to store clock algorithm details
public class Clock {

	//store frame numbers that were added to the page table
	//to enable Clock's FIFO rule
	private static ArrayList<Integer> clockFrameHistory;
	
	//store processes that run with Clock algorithm
	private static ArrayList<Process> clockProcesses;
	
	//initialize arraylists
	static {
		clockFrameHistory = new ArrayList<>();
		clockProcesses = new ArrayList<>();
	}
	
	public void Clock() {
		
	}
	
	//setters and getters
	public static ArrayList<Process> getClockProcesses() {
		return clockProcesses;
	}

	public static void setClockProcesses(ArrayList<Process> clock) {
		clockProcesses = clock;
	}
	
	public static ArrayList<Integer> getClockFrameHistory() {
		return clockFrameHistory;
	}

	public static void setClockFrameHistory(ArrayList<Integer> history) {
		clockFrameHistory = history;
	}


	//add a process to the clock algorithm
	public static void addClockProcesses(Process process) {
		clockProcesses.add(process);
	}

	//adds a number to the clock frame number history
	public static void addNumberToClockFrameHistory(int number) {
		
		//indicator to show if number is present in history
		boolean frameExistsInHistory=false;
		
		//trawls through clock frame number history
		for(int i=0;i<clockFrameHistory.size();i++) {
			
			Integer frameNumber=clockFrameHistory.get(i);
			
			//if the number inputted is in the clock frame history
			if(frameNumber==number) {
				
				//indicator sets to true
				frameExistsInHistory=true;
			}
		}
		
		//if indicator is still false, that means the number inputted is not in history
		if(frameExistsInHistory==false) {
		
		//so add the number to the clock frame number history
		clockFrameHistory.add(number);
		}
	}
	
	//resets clock frame history to empty
	public static void resetClockFrameHistoryToEmpty() {
		ArrayList<Integer> newFrameHistory = new ArrayList<>();
		clockFrameHistory = newFrameHistory;
	}
	

}
